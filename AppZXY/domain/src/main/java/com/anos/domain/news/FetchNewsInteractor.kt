package com.anos.domain.news

import com.anos.domain.entity.NewsItem

interface FetchNewsInteractor {
    suspend operator fun invoke(): List<NewsItem>?
}