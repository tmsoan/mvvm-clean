package com.anos.network.di

import android.content.Context
import com.anos.network.BuildConfig
import com.anos.network.interceptor.CacheInterceptor
import com.anos.network.interceptor.RssLogInterceptor
import com.anos.network.rest.RssApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun provideCacheInterceptor(): CacheInterceptor {
        return CacheInterceptor()
    }

    @Singleton
    @Provides
    fun provideRssLogInterceptor(): RssLogInterceptor {
        return RssLogInterceptor()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
    }

    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideOkHttp(
        applicationContext: Context,
        builder: OkHttpClient.Builder,
        rssLogInterceptor: RssLogInterceptor,
        cacheInterceptor: CacheInterceptor
    ): OkHttpClient {
        return builder.apply {
            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                addInterceptor(logging)
            }
            connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            readTimeout(TIME_OUT, TimeUnit.SECONDS)
            addInterceptor(rssLogInterceptor)

            // Add cache interceptor to handle caching of responses
//            cache(Cache(File(applicationContext.cacheDir, "http_cache"), 10 * 1024 * 1024)) // 10 MB cache
//            addInterceptor(cacheInterceptor)
        }.build()
    }

    @Provides
    @Singleton
    @Named("json")
    fun provideRetrofitJson(
        networkJson: Json,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(networkJson.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Provides
    @Singleton
    @Named("xml")
    fun provideRetrofitXml(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideRssApi(@Named("xml") retrofit: Retrofit): RssApi {
        return retrofit.create(RssApi::class.java)
    }

    private companion object {
        const val TIME_OUT = 10L
    }
}
