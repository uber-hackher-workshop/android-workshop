package com.queensuber.hackherstarterapp.data

import com.squareup.moshi.Json

data class NewsResponse(
    @field:Json(name = "status") val status: String? = "",
    @field:Json(name = "totalResults") val totalResults: Int? = 0,
    @field:Json(name = "articles") val articles: List<Article>? = emptyList(),
)