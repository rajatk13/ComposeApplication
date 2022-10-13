package com.app.composeapplication.firstApp.ui.feture_note.domain.notesUseCase

import com.app.composeapplication.firstApp.ui.feture_note.domain.repository.NoteRepositroy
import com.app.composeapplication.firstApp.ui.roomDB.InvalidNoteException
import com.app.composeapplication.firstApp.ui.roomDB.Note

class AddNote(val repositroy: NoteRepositroy) {
   @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note){
        if(note.title.isBlank())
            throw InvalidNoteException("The title of the note cant be empty..")
          if(note.content.isBlank())
            throw InvalidNoteException("The content of the note cant be empty..")
        repositroy.insertNote(note)
    }


}