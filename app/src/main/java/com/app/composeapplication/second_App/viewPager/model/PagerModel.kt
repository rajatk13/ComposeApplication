package com.app.composeapplication.second_App.viewPager.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.app.composeapplication.R

data class PagerModel(
    @DrawableRes val image:Int,
    val description:String
)

val dataList = listOf(
    PagerModel(R.drawable.welcome, "PAGE ONE"),
    PagerModel(R.drawable.welcomefive, "PAGE TWO"),
    PagerModel(R.drawable.welcomethree, "PAGE THREE")
   )