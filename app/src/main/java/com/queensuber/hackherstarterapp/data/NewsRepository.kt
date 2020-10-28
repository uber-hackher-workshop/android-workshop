package com.queensuber.hackherstarterapp.data

import com.queensuber.hackherstarterapp.api.NewsServiceHelper
import javax.inject.Inject

class NewsRepository @Inject constructor(private val newsServiceHelper: NewsServiceHelper) {
    suspend fun getTopHeadlines() = newsServiceHelper.getTopHeadlines()
}