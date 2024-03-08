package com.example.myapplication.data


sealed class UiEvent {
    data class Message(val error: String) : UiEvent()
}