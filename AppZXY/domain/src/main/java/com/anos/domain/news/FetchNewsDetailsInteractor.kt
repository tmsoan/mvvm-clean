package com.anos.domain.news

import com.anos.data.model.NewsItem

interface FetchNewsDetailsInteractor {
    suspend operator fun invoke(id: String): NewsItem?
}