package com.anos.domain.rss

import com.anos.domain.repository.RssRepository
import com.anos.model.Feed
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRssByChannelUseCase @Inject constructor(
    private val rssRepository: RssRepository
) {
    suspend operator fun invoke(channel: String): Feed {
        return rssRepository.getRssByChannel(channel)
    }

    suspend fun invokeWithFlow(channel: String): Flow<Feed> {
        return rssRepository.getRssByChannelFlow(channel)
    }
}
