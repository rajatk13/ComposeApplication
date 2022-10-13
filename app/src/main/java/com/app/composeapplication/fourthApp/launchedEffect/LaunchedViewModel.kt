package com.app.composeapplication.fourthApp.launchedEffect

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class LaunchedViewModel:ViewModel() {

    private val _sharedFlow = MutableSharedFlow<ScreenEvents>()
    val sharedFlow = _sharedFlow.asSharedFlow()

    init {
        viewModelScope.launch {
            _sharedFlow.emit(ScreenEvents.ShowSnakbar("Hello World!"))
        }
    }

sealed class ScreenEvents{
    data class ShowSnakbar( val message: String):ScreenEvents()
    data class Navigate(val route :String): ScreenEvents()
}

}