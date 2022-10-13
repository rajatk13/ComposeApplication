package com.app.composeapplication.fourthApp.launchedEffect

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.collect

@Composable
fun LaunchedEffectFlowDemo(
    viewModel: LaunchedViewModel
) {
    LaunchedEffect(key1 = true){
        viewModel.sharedFlow.collect{
            when(it){
                is LaunchedViewModel.ScreenEvents.ShowSnakbar->{}
                is LaunchedViewModel.ScreenEvents.Navigate->{}
            }
        }
    }
    
}