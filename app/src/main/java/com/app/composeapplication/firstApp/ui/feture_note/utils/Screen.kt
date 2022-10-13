package com.app.composeapplication.firstApp.ui.feture_note.utils

const val NOTE_Id ="id"
const val NOTE_Color ="noteColor"

sealed class Screen(val route:String){
    object NoteScreen:Screen("notes_screen")
    object AddEditNoteScreen:Screen("add_edit_note_screen")
    object MyAddEditNoteScreen:Screen("my_add_edit_note_screen/{$NOTE_Id}/{$NOTE_Color}"){
        fun passId_Color(id:Int,color:Int):String{
            return "my_add_edit_note_screen/$id/$color"
        }
    }
    object MyNoteScreen:Screen("my_note_screen")
    object MyMapScreen:Screen("my_map_screen")
}
