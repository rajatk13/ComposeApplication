package com.app.composeapplication.fifthApp.data

data class MyNoteUseCase(
    val getMyNotUseCase: GetMyNotUseCase,
    val deleteNotUseCase: MyDeleteNotUseCase,
    val addMyNotes: AddMyNotes,
    val getNotbyId: GetNotbyId
)
