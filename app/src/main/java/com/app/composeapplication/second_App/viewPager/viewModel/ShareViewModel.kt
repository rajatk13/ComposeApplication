package com.app.composeapplication.second_App.viewPager.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.app.composeapplication.second_App.viewPager.model.ModelToParse

class ShareViewModel :ViewModel() {

    var person by mutableStateOf<ModelToParse?>(null)
    private set

    fun addModelToParse(newModelToParse:ModelToParse){
        person =newModelToParse
    }
}