package com.app.composeapplication.firstApp.ui.feture_note.domain.notesUseCase

sealed class NotrOrderType(val orderType:OrderType){

    class Title(orderType: OrderType):NotrOrderType(orderType)
    class Date(orderType: OrderType):NotrOrderType(orderType)
    class Color(orderType: OrderType):NotrOrderType(orderType)

    fun copy(orderType: OrderType):NotrOrderType{
        return when(this){
            is Title -> Title(orderType)
            is Date -> Date(orderType)
            is Color -> Color(orderType)
        }
    }
}
