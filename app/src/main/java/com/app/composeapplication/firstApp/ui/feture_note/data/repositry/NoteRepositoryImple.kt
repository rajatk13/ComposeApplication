package com.app.composeapplication.firstApp.ui.feture_note.data.repositry

import com.app.composeapplication.firstApp.ui.feture_note.domain.repository.NoteRepositroy
import com.app.composeapplication.firstApp.ui.roomDB.Note
import com.app.composeapplication.firstApp.ui.roomDB.NoteDao
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImple(
    private val dao: NoteDao
) :NoteRepositroy {
     override fun getNotes(): Flow<List<Note>> {
         return  dao.getAllNotes()
    }

    override suspend fun getNoteById(id: Int): Note? {
        return dao.getNoteById(id)
    }

    override suspend fun insertNote(note: Note) {
          dao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
          dao.deleteNote(note)
    }


}