package com.app.composeapplication.second_App.domain.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Wand(
    //val core: String
    @Json(name = "core")
    val core: String,
    @Json(name = "length")
    val length: String,
    @Json(name = "wood")
    val wood: String
)