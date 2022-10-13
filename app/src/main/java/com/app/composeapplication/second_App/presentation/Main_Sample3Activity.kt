package com.app.composeapplication.second_App.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Grass

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.app.composeapplication.R
import com.app.composeapplication.second_App.presentation.home.BottomNavigationScreen
import com.app.composeapplication.second_App.presentation.home.MyApp_NScreen
import com.app.composeapplication.second_App.presentation.ui.theme.ComposeApplicationTheme
import com.app.composeapplication.second_App.viewPager.model.HOME_ROUTE
import com.app.composeapplication.second_App.viewPager.model.Screen_S
import com.app.composeapplication.second_App.viewPager.model.SetupnavGraph
import com.app.composeapplication.second_App.viewPager.ui.OnBoardingScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint
@ExperimentalAnimationApi
@ExperimentalPagerApi
@AndroidEntryPoint
class Main_Sample3Activity : ComponentActivity() {
    lateinit var navController:NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeApplicationTheme {
                // A surface container using the 'background' color from the theme
                  navController = rememberNavController()

                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                        // HomeScreen()
                   Scaffold(bottomBar = { Bottombarr(navHostController = navController)}, modifier = Modifier.fillMaxSize()) {
                       Box(modifier = Modifier.padding(it))  {
                           SetupnavGraph(navController = navController)
                       }
                   }

                   // MyApp_NScreen(navController)
                }
            }
        }
    }
}

@Composable
fun Bottombarr(navHostController: NavHostController) {
    val screens= listOf(
        BottomNavigationScreen.Home,
        BottomNavigationScreen.Profile,
        BottomNavigationScreen.Settings
    )
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
  BottomNavigation() {
      screens.forEach{ screen->
          Additem(screen = screen, currentDestination = currentDestination, navHostController = navHostController)
      }
  }
}

@Composable
fun RowScope.Additem(
    screen:BottomNavigationScreen,
    currentDestination: NavDestination?,
    navHostController: NavHostController
) {
    BottomNavigationItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(imageVector = screen.icon, contentDescription = "navigation Icon")
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {
            navHostController.navigate(screen.route)
        }

    )
    
}
@ExperimentalPagerApi
@Composable
fun MyClickk() {
    //OnBoardingScreen(navController = navController)
}

@ExperimentalPagerApi
@Preview
@Composable
fun MyAppPreview() {
    ComposeApplicationTheme {
      //   MyApp_N()
    }
}