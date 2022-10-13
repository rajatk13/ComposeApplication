package com.app.composeapplication.fifthApp.ui.screens.note.model

import com.app.composeapplication.firstApp.ui.roomDB.Note

sealed class MyNoteEvents{
    data class Order(val myNoteOrderType: MyNoteOrderType):MyNoteEvents()
    data class DeleteNote(val note: Note):MyNoteEvents()
    object RestoreNote:MyNoteEvents()
    object ToggleOrderSection:MyNoteEvents()

}
