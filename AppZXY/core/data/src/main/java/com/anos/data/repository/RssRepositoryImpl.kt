package com.anos.data.repository

import com.anos.domain.repository.RssRepository
import com.anos.model.Feed
import com.anos.network.rest.RssApi
import javax.inject.Inject

internal class RssRepositoryImpl @Inject constructor(
    private val rssApi: RssApi
) : RssRepository {
    override suspend fun getRssByChannel(channel: String): Feed {
        return rssApi.getRssContent(channel)
    }
}