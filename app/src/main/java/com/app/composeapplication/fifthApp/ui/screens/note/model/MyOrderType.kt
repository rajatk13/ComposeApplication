package com.app.composeapplication.fifthApp.ui.screens.note.model

import com.app.composeapplication.firstApp.ui.feture_note.domain.notesUseCase.OrderType

sealed class MyOrderType(){
    object Ascending:MyOrderType()
    object Descending:MyOrderType()
}
