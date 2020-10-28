package com.queensuber.hackherstarterapp.api

import com.queensuber.hackherstarterapp.data.NewsResponse
import retrofit2.Response
import javax.inject.Inject

class NewsServiceHelperImpl @Inject constructor(private val newsService: NewsService) :
    NewsServiceHelper {

    override suspend fun getTopHeadlines(): Response<NewsResponse> = newsService.getTopHeadlines()
}