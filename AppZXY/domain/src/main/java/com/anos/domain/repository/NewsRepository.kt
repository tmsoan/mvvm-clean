package com.anos.domain.repository

import com.anos.data.model.NewsItem

interface NewsRepository {
    suspend fun fetchNews(): List<NewsItem>?
    suspend fun fetchNewsDetails(id: String): NewsItem?
}