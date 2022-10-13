package com.app.composeapplication.firstApp.ui.feture_note.domain.notesUseCase

import com.app.composeapplication.firstApp.ui.feture_note.domain.repository.NoteRepositroy
import com.app.composeapplication.firstApp.ui.roomDB.Note

class DeleteNote(
    private val repositroy: NoteRepositroy
) {
    suspend operator fun invoke(note: Note){
        repositroy.deleteNote(note)
    }
}