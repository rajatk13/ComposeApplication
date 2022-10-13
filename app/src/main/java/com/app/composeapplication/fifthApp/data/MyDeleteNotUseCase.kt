package com.app.composeapplication.fifthApp.data

import com.app.composeapplication.fifthApp.ui.screens.note.model.MyNoteOrderType
import com.app.composeapplication.fifthApp.ui.screens.noteViewModel.MyNoteRepositoy
import com.app.composeapplication.firstApp.ui.feture_note.domain.repository.NoteRepositroy
import com.app.composeapplication.firstApp.ui.roomDB.Note

class MyDeleteNotUseCase(
    private val myNoteRepositoy: NoteRepositroy
) {
   suspend operator fun invoke(note: Note){
        myNoteRepositoy.deleteNote(note)
    }
}