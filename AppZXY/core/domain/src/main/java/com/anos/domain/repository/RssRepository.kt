package com.anos.domain.repository

import com.anos.model.Feed
import kotlinx.coroutines.flow.Flow

interface RssRepository {
    suspend fun getRssByChannel(channel: String): Feed

    suspend fun getRssByChannelFlow(channel: String): Flow<Feed>
}
