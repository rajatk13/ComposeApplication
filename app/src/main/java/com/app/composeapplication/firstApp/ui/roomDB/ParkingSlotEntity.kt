package com.app.composeapplication.firstApp.ui.roomDB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ParkingSlotEntity(
    val lat : Double,
    val longgt : Double,
    @PrimaryKey val id:Int?=null
) {

}