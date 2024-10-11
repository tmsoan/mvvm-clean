package com.anos.domain.rss

import com.anos.domain.repository.RssRepository
import com.anos.model.Feed
import javax.inject.Inject

class GetRssByChannelInteractor @Inject constructor(
    private val rssRepository: RssRepository
) {
    suspend operator fun invoke(channel: String): Feed {
        return runCatching {
            rssRepository.getRssByChannel(channel)
        }.onFailure {
            it.printStackTrace()
        }.getOrNull() ?: Feed(
            channelTitle = "${channel.uppercase()} - Empty page"
        )
    }
}
