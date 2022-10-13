package com.app.composeapplication.second_App.viewPager.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState

@ExperimentalPagerApi
@Composable
fun HorizontalPractive(
    pageState : PagerState
) {
    HorizontalPagerIndicator(pagerState = pageState, modifier = Modifier
        .padding(16.dp)
        .wrapContentSize(align = Alignment.BottomCenter), activeColor = Color.Yellow)
}