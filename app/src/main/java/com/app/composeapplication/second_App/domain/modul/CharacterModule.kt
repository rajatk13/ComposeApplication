package com.app.composeapplication.second_App.domain.modul

import com.app.composeapplication.second_App.domain.di.CharacterApi
import com.app.composeapplication.second_App.domain.repository.CharaterRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object CharacterModule {



    @Provides
    fun providesCharacterApi(retrofit: Retrofit): CharacterApi {
        return retrofit.create(CharacterApi::class.java)
    }

    @Provides
    fun providesCharacterRepo(apiService: CharacterApi): CharaterRepo {
        return CharaterRepo(apiService)
    }
}