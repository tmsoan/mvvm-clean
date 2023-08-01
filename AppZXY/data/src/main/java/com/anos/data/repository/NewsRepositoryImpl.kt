package com.anos.data.repository

import com.anos.data.mapper.NewsItemEntityToNewsItemMapper
import com.anos.data.model.NewsItem
import com.anos.data.model.NewsItemEntity
import com.anos.domain.repository.NewsRepository

class NewsRepositoryImpl : NewsRepository {
    override suspend fun fetchNews(): List<NewsItem>? {
        val newsEntities = listOf(
            NewsItemEntity("0", "Asian Stocks Echo US Rally on Soft Landing Hopes: Markets Wrap - Yahoo Finance"),
            NewsItemEntity("1", "US trucking company Yellow shuts down operations, Wall Street Journal reports - Reuters"),
            NewsItemEntity("2", "San Francisco’s complaint against Twitter says flashing ‘X’ sign put up without a permit - CNN")
        )
        return NewsItemEntityToNewsItemMapper().map(newsEntities)
    }

    override suspend fun fetchNewsDetails(id: String): NewsItem? {
        val entity = NewsItemEntity("2", "San Francisco’s complaint against Twitter says flashing ‘X’ sign put up without a permit - CNN")
        return NewsItemEntityToNewsItemMapper().map(entity)
    }
}
