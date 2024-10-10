package com.anos.data.repository

import com.anos.data.mapper.NewsItemMapper
import com.anos.domain.entity.NewsItem
import com.anos.data.model.NewsItemData
import com.anos.domain.repository.NewsRepository

class NewsRepositoryImpl : NewsRepository {
    override suspend fun fetchNews(): List<NewsItem>? {
        val newsEntities = listOf(
            NewsItemData("0", "Asian Stocks Echo US Rally on Soft Landing Hopes: Markets Wrap - Yahoo Finance"),
            NewsItemData("1", "US trucking company Yellow shuts down operations, Wall Street Journal reports - Reuters"),
            NewsItemData("2", "San Francisco’s complaint against Twitter says flashing ‘X’ sign put up without a permit - CNN")
        )
        return NewsItemMapper().mapToEntities(newsEntities)
    }

    override suspend fun fetchNewsDetails(id: String): NewsItem? {
        val entity = NewsItemData("2", "San Francisco’s complaint against Twitter says flashing ‘X’ sign put up without a permit - CNN")
        return NewsItemMapper().mapToEntity(entity)
    }
}
