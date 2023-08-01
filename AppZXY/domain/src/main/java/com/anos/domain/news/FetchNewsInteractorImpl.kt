package com.anos.domain.news

import com.anos.data.model.NewsItem
import com.anos.domain.repository.NewsRepository

class FetchNewsInteractorImpl(
    private val newsRepository: NewsRepository,
) : FetchNewsInteractor {
    override suspend fun invoke(): List<NewsItem>? {
        return runCatching {
            newsRepository.fetchNews()
        }.onFailure {
            it.printStackTrace()
        }.getOrNull()
    }
}