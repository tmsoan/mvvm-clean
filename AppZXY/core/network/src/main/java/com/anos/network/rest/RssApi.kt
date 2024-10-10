package com.anos.network.rest

import com.anos.model.Feed
import retrofit2.http.GET
import retrofit2.http.Path

interface RssApi {
    @GET("/rss/{channel}.rss")
    suspend fun getRssContent(@Path("channel") channel: String): Feed
}