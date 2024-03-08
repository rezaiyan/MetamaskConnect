package com.example.myapplication

import androidx.lifecycle.ViewModel
import com.example.myapplication.data.EventSink
import com.example.myapplication.data.UiEvent
import com.example.myapplication.data.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.metamask.androidsdk.EthereumFlow
import io.metamask.androidsdk.EthereumMethod
import io.metamask.androidsdk.EthereumRequest
import io.metamask.androidsdk.Result
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.math.BigInteger
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val ethereum: EthereumFlow) : ViewModel() {

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    private fun showMessage(message: String) {
        launch {
            _uiEvent.emit(UiEvent.Message(message))
        }
    }

    fun eventSink(eventSink: EventSink) {
        launch {
            when (eventSink) {
                EventSink.Connect -> {
                    when (val result = ethereum.connect()) {
                        is Result.Error -> {
                            _uiState.update { it.copy(isConnecting = false) }
                            showMessage(result.error.message)
                        }

                        else -> {
                            _uiState.update { it.copy(isConnecting = true) }
                        }
                    }
                }

                EventSink.GetBalance -> {
                    val balanceResult = ethereum.sendRequest(
                        EthereumRequest(
                            method = EthereumMethod.ETH_GET_BALANCE.value,
                            params = listOf(ethereum.selectedAddress, "latest")
                        )
                    )
                    when (balanceResult) {
                        is Result.Error -> showMessage(balanceResult.error.message)
                        is Result.Success.Item -> {
                            val cleanHexString = if (balanceResult.value.startsWith("0x")) {
                                balanceResult.value.substring(2)
                            } else {
                                balanceResult.value
                            }
                            _uiState.update {
                                it.copy(balance = "${BigInteger(cleanHexString, 16)} ETH")
                            }
                        }

                        is Result.Success.ItemMap -> _uiState.update { it.copy(balance = "NA") }
                        is Result.Success.Items -> _uiState.update { it.copy(balance = "NA") }
                    }
                }

                EventSink.Disconnect -> {
                    _uiState.update { it.copy(isConnecting = false) }
                    ethereum.disconnect(true)
                    showMessage("Disconnected!")
                }
            }
        }
    }

    fun updateBalance() {
        if (ethereum.selectedAddress.isNotEmpty()) {
            eventSink(EventSink.GetBalance)
            showMessage("Fetching the wallet balance")
        } else {
            showMessage("The wallet is not connected!")
        }
    }


}