package com.app.composeapplication.fifthApp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.app.composeapplication.fifthApp.navGraph.MyNavGraph
import com.app.composeapplication.fifthApp.ui.screens.note.MyNoteScreen
import com.app.composeapplication.fifthApp.ui.theme.ComposeApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFifth5Activity : ComponentActivity() {
    lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeApplicationTheme {
                // A surface container using the 'background' color from the theme
                navController= rememberNavController()
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                   // MyNoteScreen(navController = rememberNavController())
                    MyNavGraph(navHostController = navController)
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview5() {
    ComposeApplicationTheme {
        MyNoteScreen(navController = rememberNavController())
    }
}