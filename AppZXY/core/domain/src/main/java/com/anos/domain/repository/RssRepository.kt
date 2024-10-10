package com.anos.domain.repository

import com.anos.model.Feed

interface RssRepository {
    suspend fun getRssByChannel(channel: String): Feed
}
