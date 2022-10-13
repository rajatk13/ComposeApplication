package com.app.composeapplication.firstApp.ui.feture_note.domain.notesUseCase

import com.app.composeapplication.firstApp.ui.feture_note.Note.GetNote

data class NoteUseCase(
    val getNotesUseCase: GetNotesUseCase,
    val deleteNote: DeleteNote,
    val addNote: AddNote,
    val getNote:GetNote
) {
}