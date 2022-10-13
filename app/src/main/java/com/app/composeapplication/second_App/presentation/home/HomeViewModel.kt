package com.app.composeapplication.second_App.presentation.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.composeapplication.second_App.domain.model.CharacterPojo
import com.app.composeapplication.second_App.domain.repository.CharaterRepo
import com.app.composeapplication.second_App.viewPager.model.Screen_S
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val charaterRepo: CharaterRepo
) :ViewModel(){

//    private val _state = MutableStateFlow(emptyList<CharacterPojo>())
    private val _state = MutableStateFlow(CharacterPojo())
    val state : StateFlow<CharacterPojo>
    get() = _state


    private val _isLoading:MutableState<Boolean> = mutableStateOf(true)
           val isLoading:State<Boolean> = _isLoading

    private val _isDestination:MutableState<String> = mutableStateOf(Screen_S.MyApp_NScreen.route)
            val isDestination:State<String> =_isDestination

    init {
        viewModelScope.launch {
            val characters = charaterRepo.getCharaters()
            _state.value = characters


        }
    }


}