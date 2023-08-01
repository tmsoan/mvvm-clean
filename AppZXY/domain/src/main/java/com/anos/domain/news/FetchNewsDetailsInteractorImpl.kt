package com.anos.domain.news

import com.anos.data.model.NewsItem
import com.anos.domain.repository.NewsRepository

class FetchNewsDetailsInteractorImpl(
    private val newsRepository: NewsRepository,
) : FetchNewsDetailsInteractor {
    override suspend fun invoke(id: String): NewsItem? {
        return runCatching {
            newsRepository.fetchNewsDetails(id)
        }.onFailure {
            it.printStackTrace()
        }.getOrNull()
    }
}