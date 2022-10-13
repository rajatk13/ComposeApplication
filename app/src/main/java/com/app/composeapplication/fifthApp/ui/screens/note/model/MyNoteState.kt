package com.app.composeapplication.fifthApp.ui.screens.note.model

import com.app.composeapplication.firstApp.ui.roomDB.Note
import java.util.*

data class MyNoteState(
    val isToggled :Boolean =false,
    val myNoteOrderType: MyNoteOrderType= MyNoteOrderType.Date(MyOrderType.Descending),
    val note:List<Note> = emptyList()

){

}
