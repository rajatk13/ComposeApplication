package com.app.composeapplication.fifthApp.ui.screens.mapsScreen.mapper

import com.app.composeapplication.fifthApp.ui.screens.mapsScreen.ParkingSpot
import com.app.composeapplication.firstApp.ui.roomDB.ParkingSlotEntity

fun ParkingSlotEntity.toParkingSpot (): ParkingSpot{
    return ParkingSpot(
         id =id!!,
        lat = lat,
        longgt = longgt
    )
}
fun ParkingSpot.toParkingSpotEntity (): ParkingSlotEntity{
    return ParkingSlotEntity(
        id =id,
        lat = lat,
        longgt = longgt
    )
}