package com.app.composeapplication.fifthApp.ui.screens.mapsScreen.reppository

import com.app.composeapplication.fifthApp.ui.screens.mapsScreen.ParkingSpot
import kotlinx.coroutines.flow.Flow

interface MapRepository {

   suspend fun insertParkingSpot(spot:ParkingSpot)
    suspend fun deleteParkingSpot(spot:ParkingSpot)
    suspend fun getParkingSpot( ) :Flow<List<ParkingSpot>>
}