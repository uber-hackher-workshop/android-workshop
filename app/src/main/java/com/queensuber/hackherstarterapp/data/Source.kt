package com.queensuber.hackherstarterapp.data

import com.squareup.moshi.Json

data class Source(
    @field:Json(name = "id") val id: String?,
    @field:Json(name = "name") val name: String?
)