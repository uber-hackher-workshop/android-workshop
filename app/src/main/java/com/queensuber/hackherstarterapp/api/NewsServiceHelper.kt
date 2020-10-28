package com.queensuber.hackherstarterapp.api

import com.queensuber.hackherstarterapp.data.NewsResponse
import retrofit2.Response

interface NewsServiceHelper {
    suspend fun getTopHeadlines(): Response<NewsResponse>

    suspend fun searchNews(query: String): Response<NewsResponse>
}