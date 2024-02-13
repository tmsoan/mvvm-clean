package com.anos.domain.repository

import com.anos.domain.entity.NewsItem

interface NewsRepository {
    suspend fun fetchNews(): List<NewsItem>?
    suspend fun fetchNewsDetails(id: String): NewsItem?
}