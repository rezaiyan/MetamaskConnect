package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.myapplication.data.UiEvent
import com.example.myapplication.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val uiState by viewModel.uiState.collectAsState()

            LaunchedEffect(key1 = uiState.isConnecting) {
                viewModel.updateBalance()
            }

            OnEvent(events = viewModel.uiEvent) { event ->
                when (event) {
                    is UiEvent.Message -> {
                        Toast.makeText(
                            this@MainActivity,
                            event.error,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

            MyApplicationTheme {
                WalletConnectScreen(
                    isConnecting = uiState.isConnecting,
                    balance = uiState.balance,
                    eventSink = viewModel::eventSink,
                )
            }
        }
    }
}