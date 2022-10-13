package com.app.composeapplication.fifthApp.data

import com.app.composeapplication.fifthApp.ui.screens.note.model.MyNoteOrderType
import com.app.composeapplication.fifthApp.ui.screens.note.model.MyOrderType
import com.app.composeapplication.firstApp.ui.feture_note.domain.repository.NoteRepositroy
import com.app.composeapplication.firstApp.ui.roomDB.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetMyNotUseCase(
      private val noteRepositroy: NoteRepositroy
  ){
operator fun invoke(
    noteOrderType: MyNoteOrderType = MyNoteOrderType.Date(orderType = MyOrderType.Descending)
):Flow<List<Note>>{
    return noteRepositroy.getNotes().map { notes->
         when(noteOrderType.orderType){
             is MyOrderType.Descending->{
                 when(noteOrderType){
                     is MyNoteOrderType.Title -> notes.sortedByDescending { it.title.lowercase() }
                     is MyNoteOrderType.Date -> notes.sortedByDescending { it.timeStyamp }
                     is MyNoteOrderType.ColorM -> notes.sortedByDescending { it.color }
                 }
             }
             is MyOrderType.Ascending->{
                 when(noteOrderType){
                     is MyNoteOrderType.Title -> notes.sortedBy { it.title.lowercase() }
                     is MyNoteOrderType.Date -> notes.sortedBy { it.timeStyamp }
                     is MyNoteOrderType.ColorM -> notes.sortedBy { it.color }
                 }
             }
         }
    }
}
  }
