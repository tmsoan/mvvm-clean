package com.anos.common.event

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GlobalEventManager @Inject constructor(
    private val globalEventBus: GlobalEventBus
) {
    fun observeEvents(
        scope: CoroutineScope,
        onSessionExpired: () -> Unit,
        onLogout: () -> Unit,
        onNoNetwork: () -> Unit,
        onNetworkRestored: (Boolean) -> Unit,
        showToast: (String) -> Unit
    ) {
        scope.launch {
            globalEventBus.events.collectLatest { event ->
                when (event) {
                    is GlobalEvent.SessionExpired -> onSessionExpired()
                    is GlobalEvent.Logout -> onLogout()
                    is GlobalEvent.NoNetwork -> onNoNetwork()
                    is GlobalEvent.NetworkRestored -> onNetworkRestored(event.isConnected)
                    is GlobalEvent.ShowToast -> showToast(event.message)
                }
            }
        }
    }
}
