package com.app.composeapplication.second_App.domain.repository

import com.app.composeapplication.second_App.domain.di.CharacterApi
import com.app.composeapplication.second_App.domain.model.CharacterPojo
import javax.inject.Inject

class CharaterRepo @Inject constructor(
    private val characterApi: CharacterApi
){
suspend fun  getCharaters():CharacterPojo{
    return characterApi.getCharacters()
}
}