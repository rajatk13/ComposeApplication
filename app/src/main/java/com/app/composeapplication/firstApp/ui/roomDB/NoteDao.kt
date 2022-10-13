package com.app.composeapplication.firstApp.ui.roomDB

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    fun getAllNotes(): Flow<List<Note>>

    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun getNoteById(id:Int):Note?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    //Maps screen
   @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertParkingSpot(spot:ParkingSlotEntity)

    @Delete
    suspend fun deleteParkingSpot(note: ParkingSlotEntity)

    @Query("SELECT * FROM parkingslotentity" )
    fun getParkingSpots(): Flow<List<ParkingSlotEntity>>
}