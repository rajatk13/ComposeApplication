package com.app.composeapplication.fifthApp.ui.screens.mapsScreen

import com.google.android.gms.maps.model.LatLng

sealed class MapEvent {
    object ToggleFallout:MapEvent()
    object refreshFallout:MapEvent()
    data class OnMapLongClick(val latLng: LatLng):MapEvent()
    data class OnInfoWindowLongClick(val spot: ParkingSpot):MapEvent()
}