package com.queensuber.hackherstarterapp.di

import com.queensuber.hackherstarterapp.api.NewsService
import com.queensuber.hackherstarterapp.api.NewsServiceHelper
import com.queensuber.hackherstarterapp.api.NewsServiceHelperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {
    companion object {
        private const val BASE_URL = "http://newsapi.org/v2/"
        private const val API_KEY = "apiKey"
    }

    @Provides
    fun provideBaseUrl(): String = BASE_URL

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .addInterceptor{ chain ->
                val request = chain.request()
                val httpUrl = request.url.newBuilder()
                    .addQueryParameter(API_KEY, "2443088b36f44f82a1826b7afe31f227")
                    .build()
                chain.proceed(request.newBuilder().url(httpUrl).build())
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, BaseURL: String): Retrofit = Retrofit.Builder()
        .baseUrl(BaseURL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideNewsService(retrofit: Retrofit): NewsService =
        retrofit.create(NewsService::class.java)

    @Provides
    @Singleton
    fun provideNewsServiceHelper(newsServiceHelper: NewsServiceHelperImpl): NewsServiceHelper =
        newsServiceHelper
}