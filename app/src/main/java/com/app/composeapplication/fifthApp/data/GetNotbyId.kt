package com.app.composeapplication.fifthApp.data

import com.app.composeapplication.firstApp.ui.feture_note.domain.repository.NoteRepositroy
import com.app.composeapplication.firstApp.ui.roomDB.Note

data class GetNotbyId(
    private val repositroy: NoteRepositroy
){
    suspend operator fun invoke(id:Int):Note?{
        return repositroy.getNoteById(id)
    }
}
