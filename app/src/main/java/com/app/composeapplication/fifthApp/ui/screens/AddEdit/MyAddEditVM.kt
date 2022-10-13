package com.app.composeapplication.fifthApp.ui.screens.AddEdit

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.composeapplication.fifthApp.data.MyNoteUseCase
import com.app.composeapplication.firstApp.ui.feture_note.utils.NOTE_Id
import com.app.composeapplication.firstApp.ui.roomDB.InvalidNoteException
import com.app.composeapplication.firstApp.ui.roomDB.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyAddEditVM @Inject constructor(private val noteUseCase: MyNoteUseCase,
savedStateHandle: SavedStateHandle):ViewModel() {


    private val _noteTitle  = mutableStateOf(MyNoteTextFieldstate(
        hint = "Enter title..."
    ))
    val notTitle : State<MyNoteTextFieldstate> =_noteTitle

    private val _noteContent  = mutableStateOf(MyNoteTextFieldstate(
        hint = "Enter some content..."
    ))
    val noteContent : State<MyNoteTextFieldstate> =_noteContent

     private val _noteColor = mutableStateOf<Int>(Note.notColors.random())
     val noteColor : State<Int> =_noteColor
    private var currentNoteId:Int?=null

    private val _eventFlow= MutableSharedFlow<UiEvent>()
     val eventFlow = _eventFlow.asSharedFlow()

    init {
        savedStateHandle.get<Int>(NOTE_Id)?.let { noteId->
            if(noteId!=-1){
                viewModelScope.launch {
                    noteUseCase.getNotbyId(noteId)?.also { note->
                        currentNoteId  = note.id
                        _noteTitle.value = notTitle.value.copy(
                            text = note.title
                            , isHintVisible = false
                        )
                        _noteContent.value = noteContent.value.copy(
                            text = note.content
                            , isHintVisible = false
                        )
                        _noteColor.value = note.color
                    }
                }
            }
        }
    }



     fun onEvent(event: MyAddEditNoteEvent){
         when(event){
             is MyAddEditNoteEvent.changeTitle->{
                 _noteTitle.value = notTitle.value.copy(
                     text = event.value
                 )
             }
             is MyAddEditNoteEvent.changeTitleFocus->{
                 _noteTitle.value= notTitle.value.copy(
                     isHintVisible = !event.focusState.isFocused && notTitle.value.text.isBlank()
                 )
             }
             is MyAddEditNoteEvent.changeContent->{
                 _noteContent.value = noteContent.value.copy(
                     text = event.value
                 )
             }
             is MyAddEditNoteEvent.changeContentFocus->{
                 _noteContent.value= noteContent.value.copy(
                     isHintVisible = !event.focusState.isFocused && noteContent.value.text.isBlank()
                 )
             }
             is MyAddEditNoteEvent.changeColor->{
                 _noteColor.value = event.color
             }
             is MyAddEditNoteEvent.SaveNote->{

                 viewModelScope.launch {
                   try {
                       noteUseCase.addMyNotes(Note(
                           title = notTitle.value.text,
                           content = noteContent.value.text,
                           timeStyamp = System.currentTimeMillis(),
                           color = noteColor.value,
                           id = currentNoteId
                       ))
                       _eventFlow.emit(UiEvent.SavedNote)
                   }catch (e:InvalidNoteException){
                       _eventFlow.emit(UiEvent.ShowSnackbar(message = e.message?:"unknown error "))
                   }

                 }
             }
         }
     }

    sealed class UiEvent{
        data class  ShowSnackbar(val message:String):UiEvent()
        object SavedNote: UiEvent()

    }
}

