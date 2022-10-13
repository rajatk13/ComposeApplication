package com.app.composeapplication.second_App.viewPager.model

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.app.composeapplication.second_App.presentation.home.MyApp_NScreen
import com.app.composeapplication.second_App.presentation.home.ProfileScreen
import com.app.composeapplication.second_App.viewPager.ui.OnBoardingScreen
import com.app.composeapplication.second_App.viewPager.viewModel.ShareViewModel
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
fun NavGraphBuilder.homeNavGraph(
    navController: NavController,
    sharedViewModel: ShareViewModel
) {

    navigation(startDestination = Screen_S.MyApp_NScreen.route, route = HOME_ROUTE)
    {

        composable(route = Screen_S.OnBoardingScreen.route, arguments = listOf(
            navArgument(MyArgss) { type = NavType.IntType
                defaultValue=0},
            navArgument(MyArgssName) { type = NavType.StringType }
        )) {
          /*  var  result = navController.previousBackStackEntry?.savedStateHandle?.get<ModelToParse>("modelToParse")!!
           LaunchedEffect(key1 = it ){
               Log.e( "SetupnavGraph:0 ", result?.firstName.toString()+ result?.lastName.toString() )
               Log.e( "SetupnavGraph: ", (it.arguments?.getInt("noteColor")?:-1).toString() )
               Log.e( "SetupnavGraph:2 ", it.arguments?.getString(MyArgssName).toString() )
           }*/
            OnBoardingScreen(navController,/* result,*/ sharedViewModel)
        }
        composable( route = Screen_S.MyApp_NScreen.route){
            MyApp_NScreen(navController,sharedViewModel)
        }
        composable( route = Screen_S.ProfileScreen.route){
            ProfileScreen(navController)
        }
    }

}