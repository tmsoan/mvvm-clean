package com.anos.data.repository

import com.anos.common.AppDispatchers
import com.anos.common.Dispatcher
import com.anos.domain.repository.RssRepository
import com.anos.model.Feed
import com.anos.network.rest.RssApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class RssRepositoryImpl @Inject constructor(
    private val rssApi: RssApi,
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) : RssRepository {
    override suspend fun getRssByChannel(channel: String): Feed {
        return withContext(ioDispatcher) {
            rssApi.getRssContent(channel)
        }
    }

    override suspend fun getRssByChannelFlow(channel: String) = flow {
        emit(
            rssApi.getRssContent(channel)
        )
    }.flowOn(ioDispatcher)
}