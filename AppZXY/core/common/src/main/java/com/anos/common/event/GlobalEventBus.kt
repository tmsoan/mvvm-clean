package com.anos.common.event

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GlobalEventBus @Inject constructor() {

    private val _events = MutableSharedFlow<GlobalEvent>(
        replay = 0,
        extraBufferCapacity = 1
    )
    val events: SharedFlow<GlobalEvent> = _events.asSharedFlow()

    fun emit(event: GlobalEvent) {
        _events.tryEmit(event)
    }
}
