package com.queensuber.hackherstarterapp.api

import com.queensuber.hackherstarterapp.data.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("top-headlines")
    suspend fun getTopHeadlines(@Query("country") country: String): Response<NewsResponse>

    @GET("everything")
    suspend fun searchNews(@Query("q") query: String): Response<NewsResponse>
}

