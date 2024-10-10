package com.anos.data.mapper

import com.anos.domain.entity.NewsItem
import com.anos.data.model.NewsItemData

class NewsItemMapper : BaseMapper<NewsItemData, NewsItem> {
    override fun mapToEntity(data: NewsItemData): NewsItem {
        return NewsItem(
            id = data.id,
            title = data.title,
        )
    }
}