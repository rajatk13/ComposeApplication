package com.app.composeapplication.second_App.viewPager.model

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.app.composeapplication.second_App.presentation.home.LoginScreen
import com.app.composeapplication.second_App.presentation.home.SignupScreen

fun NavGraphBuilder.authNavGraph(
    navController:NavController
){
    navigation(startDestination =Screen_S.LoginScreen.route,
        route = AUTHENTICATION_ROUTE){
        composable( route = Screen_S.LoginScreen.route){
            LoginScreen(navController)
        }
        composable( route = Screen_S.SignUpScreen.route){
            SignupScreen(navController)
        }
    }
}