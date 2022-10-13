package com.app.composeapplication.firstApp.ui.feture_note.AddEditNote

data class NoteTextFieldState(
    val text:String="",
    val hint:String="",
    val isHintVisible:Boolean=true
) {
}