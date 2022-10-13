package com.app.composeapplication.firstApp.ui.feture_note.AddEditNote

import androidx.compose.ui.focus.FocusState

sealed class AddEditNoteEvent{
    data class changeTitle(val value:String):AddEditNoteEvent()
    data class changeTitleFocus(val focusState:FocusState):AddEditNoteEvent()
    data class changeContent(val value:String):AddEditNoteEvent()
    data class changeContentFocus(val focusState:FocusState):AddEditNoteEvent()
    data class changeColor(val color:Int):AddEditNoteEvent()
    object SaveNote : AddEditNoteEvent()
}
