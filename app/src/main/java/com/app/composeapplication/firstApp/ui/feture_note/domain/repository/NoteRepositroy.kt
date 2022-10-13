package com.app.composeapplication.firstApp.ui.feture_note.domain.repository

import com.app.composeapplication.firstApp.ui.roomDB.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepositroy {

    fun getNotes():Flow<List<Note>>

    suspend fun getNoteById(id:Int):Note?

    suspend fun insertNote(note: Note)

    suspend fun deleteNote(note: Note)
}