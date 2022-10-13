package com.app.composeapplication.second_App.viewPager.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class ModelToParse(
    val firstName:String,
    val lastName:String
): Parcelable {
}