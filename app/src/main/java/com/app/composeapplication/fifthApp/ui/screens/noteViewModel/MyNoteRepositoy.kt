package com.app.composeapplication.fifthApp.ui.screens.noteViewModel

import com.app.composeapplication.firstApp.ui.feture_note.domain.repository.NoteRepositroy
import com.app.composeapplication.firstApp.ui.roomDB.Note
import com.app.composeapplication.firstApp.ui.roomDB.NoteDao
import kotlinx.coroutines.flow.Flow

class MyNoteRepositoy(val noteDao: NoteDao) :NoteRepositroy {
    override fun getNotes(): Flow<List<Note>> {
        return noteDao.getAllNotes()
    }

    override suspend fun getNoteById(id: Int): Note? {
       return noteDao.getNoteById(id)
    }

    override suspend fun insertNote(note: Note) {
        noteDao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }
}