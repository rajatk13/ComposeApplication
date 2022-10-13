package com.app.composeapplication.firstApp.ui.feture_note.domain.notesUseCase

import com.app.composeapplication.firstApp.ui.roomDB.Note

sealed class NoteEvent{
    data class Order(val notrOrderType: NotrOrderType):NoteEvent()
    data class DeleteNote(val note: Note): NoteEvent()
    object RestoreNote: NoteEvent()
    object ToggleOrderSection: NoteEvent()
}
