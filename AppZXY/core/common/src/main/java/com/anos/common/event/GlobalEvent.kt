package com.anos.common.event

sealed class GlobalEvent {
    data object SessionExpired : GlobalEvent()
    data object Logout : GlobalEvent()
    data object NoNetwork : GlobalEvent()
    data class NetworkRestored(val isConnected: Boolean) : GlobalEvent()
    data class ShowToast(val message: String) : GlobalEvent()
}
