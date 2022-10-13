package com.app.composeapplication.firstApp.ui.roomDB

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Note::class,ParkingSlotEntity::class], version = 1, exportSchema = false)
abstract class NoteDataBaseClass : RoomDatabase() {

    abstract val noteDao:NoteDao
    companion object{
        val dataBasename = "note_db"
    }
}