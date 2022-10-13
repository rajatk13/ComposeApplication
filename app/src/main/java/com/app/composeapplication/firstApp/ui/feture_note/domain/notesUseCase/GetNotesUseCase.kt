package com.app.composeapplication.firstApp.ui.feture_note.domain.notesUseCase

import com.app.composeapplication.firstApp.ui.feture_note.domain.repository.NoteRepositroy
import com.app.composeapplication.firstApp.ui.roomDB.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotesUseCase(
    private val repositroy: NoteRepositroy
) {
operator fun invoke(
    notrOrderType: NotrOrderType = NotrOrderType.Date(orderType = OrderType.Descending)
  ): Flow<List<Note>> {
    //https://www.youtube.com/watch?v=8YPXv7xKh2w
          return repositroy.getNotes().map { notes->
              when(notrOrderType.orderType){
                  is OrderType.Ascending->{
                        when(notrOrderType){
                            is NotrOrderType.Title-> notes.sortedBy { it.title.lowercase() }
                            is NotrOrderType.Date-> notes.sortedBy { it.timeStyamp }
                            is NotrOrderType.Color-> notes.sortedBy { it.color }
                        }
                  }
                  is OrderType.Descending->{
                      when(notrOrderType){
                          is NotrOrderType.Title-> notes.sortedByDescending { it.title.lowercase() }
                          is NotrOrderType.Date-> notes.sortedByDescending { it.timeStyamp }
                          is NotrOrderType.Color-> notes.sortedByDescending { it.color }
                      }  //42.38
                  }
              }
          }
   }
}