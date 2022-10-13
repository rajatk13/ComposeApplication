package com.app.composeapplication.fifthApp.ui.screens.mapsScreen.reppository

import com.app.composeapplication.fifthApp.ui.screens.mapsScreen.ParkingSpot
import com.app.composeapplication.fifthApp.ui.screens.mapsScreen.mapper.toParkingSpot
import com.app.composeapplication.fifthApp.ui.screens.mapsScreen.mapper.toParkingSpotEntity
import com.app.composeapplication.firstApp.ui.roomDB.NoteDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MapRepositoryImpl(
    private val dao: NoteDao
) : MapRepository {
    override suspend fun insertParkingSpot(spot: ParkingSpot) {
         dao.insertParkingSpot(spot.toParkingSpotEntity())
    }

    override suspend fun deleteParkingSpot(spot: ParkingSpot) {
        dao.deleteParkingSpot(spot.toParkingSpotEntity())
    }

    override suspend fun getParkingSpot(): Flow<List<ParkingSpot>> {
       return dao.getParkingSpots().map { spots->
           spots.map {
               it.toParkingSpot() }
       }
    }
}