package com.app.composeapplication.fifthApp.navGraph

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.app.composeapplication.fifthApp.ui.screens.AddEdit.MyAddEditNoteScreem
import com.app.composeapplication.fifthApp.ui.screens.mapsScreen.MapScreen
import com.app.composeapplication.fifthApp.ui.screens.note.MyNoteScreen
import com.app.composeapplication.firstApp.ui.feture_note.utils.NOTE_Color
import com.app.composeapplication.firstApp.ui.feture_note.utils.NOTE_Id
import com.app.composeapplication.firstApp.ui.feture_note.utils.Screen


@Composable
fun MyNavGraph(
    navHostController: NavHostController
) {
     NavHost(navController = navHostController,
     startDestination = Screen.MyNoteScreen.route,
     ){
         composable(route= Screen.MyNoteScreen.route){
             MyNoteScreen(navController = navHostController)
         }
         composable(route= Screen.MyMapScreen.route){
             MapScreen()
         }
         composable(route= Screen.MyAddEditNoteScreen.route,
             arguments = listOf(
                 navArgument( NOTE_Id){
                     type = NavType.IntType
                     defaultValue =-1
                 },
                 navArgument( NOTE_Color){
                     type = NavType.IntType
                     defaultValue =-1
                 }

             )){
             LaunchedEffect(key1 = it ){
                 Log.e( "SetupnavGraph: ", (it.arguments?.getInt("noteColor")?:-1).toString() )
                 Log.e( "SetupnavGraph: ", (it.arguments?.getInt("id")?:-1).toString() )
             }

             /* arguments = listOf(
                            navArgument(name = "noteId"){
                                type = NavType.IntType
                                defaultValue =-1
                            },
                            navArgument(name = "noteColor"){
                                type = NavType.IntType
                                defaultValue =-1
                            }

                        )*/
             MyAddEditNoteScreem(navHostController = navHostController,   it.arguments?.getInt("noteColor")?:-1)
         }

     }
}