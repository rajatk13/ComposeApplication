package com.app.composeapplication.second_App.domain.di

import com.app.composeapplication.second_App.domain.model.CharacterPojo
import retrofit2.http.GET

interface CharacterApi {

    @GET("characters")
    suspend fun getCharacters(): CharacterPojo
}