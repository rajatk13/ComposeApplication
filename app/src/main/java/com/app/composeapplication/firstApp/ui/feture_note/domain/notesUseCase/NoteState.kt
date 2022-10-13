package com.app.composeapplication.firstApp.ui.feture_note.domain.notesUseCase

import com.app.composeapplication.firstApp.ui.roomDB.Note

data class NoteState(
    val note :List<Note> = emptyList(),
    val noteorder:NotrOrderType = NotrOrderType.Date(OrderType.Descending),
    val isOrderSectionVisible :Boolean= false
)
