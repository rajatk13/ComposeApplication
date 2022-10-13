package com.app.composeapplication.firstApp.ui.feture_note.domain.model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.composeapplication.firstApp.ui.feture_note.domain.notesUseCase.*
import com.app.composeapplication.firstApp.ui.roomDB.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val noteUseCase: NoteUseCase) :ViewModel(){

    private val _state= mutableStateOf(NoteState())
    val state: State<NoteState> = _state
    var recentlyDeletedNote: Note? = null

    private var getNoteJob: Job?=null

    init {
        getNotes(NotrOrderType.Date(OrderType.Descending))
    }

    fun onEvent(event: NoteEvent){
        when(event){
            is NoteEvent.Order->{
                if (state.value.noteorder::class == event.notrOrderType::class &&
                    state.value.noteorder.orderType == event.notrOrderType.orderType){
                    return
                }
                getNotes(event.notrOrderType)
            }

            is NoteEvent.DeleteNote->{
                viewModelScope.launch {
                    noteUseCase.deleteNote(event.note)
                    recentlyDeletedNote = event.note
                }
            }
            is NoteEvent.RestoreNote->{
               viewModelScope.launch {
               noteUseCase.addNote(recentlyDeletedNote?:return@launch)
               }
            }
            is NoteEvent.ToggleOrderSection->{
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getNotes(notrOrderType: NotrOrderType) {
       getNoteJob?.cancel()
        getNoteJob=   noteUseCase.getNotesUseCase(notrOrderType).
                onEach { notes->
                    _state.value = state.value.copy(
                        note = notes,
                        noteorder =notrOrderType
                    )
                }.launchIn(viewModelScope)
    }
}