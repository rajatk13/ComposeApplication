package com.app.composeapplication.fifthApp.ui.screens.note.model

import com.app.composeapplication.firstApp.ui.feture_note.domain.notesUseCase.NotrOrderType

sealed class MyNoteOrderType(val orderType: MyOrderType){
    class Title(orderType:MyOrderType):MyNoteOrderType(orderType = orderType)
    class Date(orderType:MyOrderType):MyNoteOrderType(orderType = orderType)
    class ColorM(orderType:MyOrderType):MyNoteOrderType(orderType = orderType)

    fun copy(orderType: MyOrderType):MyNoteOrderType{
        return when(this){
            is Title -> Title(orderType)
            is Date -> Date(orderType)
            is ColorM -> ColorM(orderType)
        }
    }
}
