package com.anos.data.mapper

import com.anos.data.model.NewsItem
import com.anos.data.model.NewsItemEntity

class NewsItemEntityToNewsItemMapper : BaseMapper<NewsItemEntity, NewsItem> {
    override fun map(entity: NewsItemEntity): NewsItem {
        return NewsItem(
            id = entity.id,
            title = entity.title,
        )
    }
}