package com.anos.domain.news

import com.anos.domain.entity.NewsItem

interface FetchNewsDetailsInteractor {
    suspend operator fun invoke(id: String): NewsItem?
}