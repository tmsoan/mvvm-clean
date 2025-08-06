package com.anos.domain.rss

import com.anos.domain.repository.RssRepository
import com.anos.model.Feed
import javax.inject.Inject

class GetRssByChannelUseCase @Inject constructor(
    private val rssRepository: RssRepository
) {
    suspend operator fun invoke(channel: String): Feed {
        return rssRepository.getRssByChannel(channel)
    }
}
