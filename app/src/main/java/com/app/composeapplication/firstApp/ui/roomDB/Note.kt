package com.app.composeapplication.firstApp.ui.roomDB

import androidx.room.Entity
import androidx.room.PrimaryKey
import android.graphics.Color
import android.graphics.Color.BLACK
import android.graphics.Color.GREEN

@Entity
data class Note(
    val title:String,
    val content:String,
    val timeStyamp:Long,
    val color: Int,
    @PrimaryKey val id :Int?=null
){
    companion object{
        val notColors = listOf(BLACK,GREEN,Color.RED,Color.YELLOW,Color.MAGENTA,Color.CYAN)
    }

}
class InvalidNoteException(message:String):Exception(message)
