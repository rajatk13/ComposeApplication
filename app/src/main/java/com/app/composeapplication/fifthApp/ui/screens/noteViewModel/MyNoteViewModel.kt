package com.app.composeapplication.fifthApp.ui.screens.note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.composeapplication.fifthApp.data.MyNoteUseCase
import com.app.composeapplication.fifthApp.ui.screens.note.model.MyNoteEvents
import com.app.composeapplication.fifthApp.ui.screens.note.model.MyNoteOrderType
import com.app.composeapplication.fifthApp.ui.screens.note.model.MyNoteState
import com.app.composeapplication.fifthApp.ui.screens.note.model.MyOrderType
import com.app.composeapplication.firstApp.ui.feture_note.domain.notesUseCase.NotrOrderType
import com.app.composeapplication.firstApp.ui.feture_note.domain.notesUseCase.OrderType
import com.app.composeapplication.firstApp.ui.roomDB.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyNoteViewModel @Inject constructor(
val noteUseCase: MyNoteUseCase
):ViewModel() {

   private val _state = mutableStateOf(MyNoteState())
      var state : State<MyNoteState> = _state
    var recentlyDeletedNote: Note? = null

    private var getNoteJob:Job?=null

    init {
        getMyNotes(MyNoteOrderType.Date(MyOrderType.Descending))
    }

    fun event(events: MyNoteEvents){
           when(events){
               is MyNoteEvents.Order->{
                   if (state.value.myNoteOrderType::class == events.myNoteOrderType::class &&
                       state.value.myNoteOrderType.orderType == events.myNoteOrderType.orderType){
                       return
                   }

                    getMyNotes(events.myNoteOrderType)
               }
               is MyNoteEvents.DeleteNote->{
                     viewModelScope.launch {
                         noteUseCase.deleteNotUseCase(events.note)
                         recentlyDeletedNote = events.note
                     }
               }
               is MyNoteEvents.RestoreNote->{
                   viewModelScope.launch {
                       noteUseCase.addMyNotes(recentlyDeletedNote?:return@launch)
                   }
                   }
               is MyNoteEvents.ToggleOrderSection->{
                   _state.value = state.value.copy(
                       isToggled =  !state.value.isToggled
                   )
               }
           }



    }

    private fun getMyNotes(myNoteOrderType: MyNoteOrderType) {
        getNoteJob?.cancel()
        getNoteJob =
            noteUseCase.getMyNotUseCase(myNoteOrderType).onEach {
                _state.value =state.value.copy(
                    note = it,
                    myNoteOrderType = myNoteOrderType
                )
            }.launchIn(viewModelScope)
    }

}