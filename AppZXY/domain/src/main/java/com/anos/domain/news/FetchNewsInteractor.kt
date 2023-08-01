package com.anos.domain.news

import com.anos.data.model.NewsItem

interface FetchNewsInteractor {
    suspend operator fun invoke(): List<NewsItem>?
}