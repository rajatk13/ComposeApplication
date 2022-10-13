package com.app.composeapplication.fifthApp.ui.screens.mapsScreen

import com.google.maps.android.compose.MapProperties

data class MapState(
    var properties: MapProperties= MapProperties(),
    val isTogglemap: Boolean  = false,
    val parkingSpot: List<ParkingSpot> = emptyList()
){

}