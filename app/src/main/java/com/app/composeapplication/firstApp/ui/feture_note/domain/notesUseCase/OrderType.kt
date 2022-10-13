package com.app.composeapplication.firstApp.ui.feture_note.domain.notesUseCase

sealed class OrderType{
    object Ascending:OrderType()
    object Descending:OrderType()
}
