package com.anos.domain.news

import com.anos.domain.entity.NewsItem
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