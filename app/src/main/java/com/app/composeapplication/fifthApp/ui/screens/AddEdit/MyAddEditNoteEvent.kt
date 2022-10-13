package com.app.composeapplication.fifthApp.ui.screens.AddEdit

import androidx.compose.ui.focus.FocusState


sealed class MyAddEditNoteEvent {
    data class changeTitle(val value:String):MyAddEditNoteEvent()
    data class changeTitleFocus(val focusState: FocusState):MyAddEditNoteEvent()
    data class changeContent(val value:String):MyAddEditNoteEvent()
    data class changeContentFocus(val focusState: FocusState):MyAddEditNoteEvent()
    data class changeColor(val color:Int): MyAddEditNoteEvent()
    object SaveNote : MyAddEditNoteEvent()
}
