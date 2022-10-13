package com.app.composeapplication.second_App.viewPager.model

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

import com.app.composeapplication.second_App.viewPager.viewModel.ShareViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
//@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun SetupnavGraph(
    navController: NavHostController

) {
    val sharedViewModel : ShareViewModel =  viewModel()
    NavHost(navController = navController,
        startDestination =  HOME_ROUTE,
        route = ROOT_ROUTE
    ){

       homeNavGraph(navController, sharedViewModel)
       authNavGraph(navController)
        }


    
}