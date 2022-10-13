package com.app.composeapplication.firstApp.ui.feture_note.Note

import com.app.composeapplication.firstApp.ui.feture_note.domain.repository.NoteRepositroy
import com.app.composeapplication.firstApp.ui.roomDB.Note

class GetNote(
    private val  repositroy: NoteRepositroy
) {
    suspend operator fun invoke(id:Int): Note?{
        return repositroy.getNoteById(id)
    }
}