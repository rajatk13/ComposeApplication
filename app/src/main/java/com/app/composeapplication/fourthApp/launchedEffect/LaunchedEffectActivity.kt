package com.app.composeapplication.fourthApp.launchedEffect

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Doorbell
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.app.composeapplication.fourthApp.launchedEffect.ui.theme.ComposeApplicationTheme
import kotlinx.coroutines.delay

class LaunchedEffectActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                MyApp {
                    MyContent()
                }
        }
    }
}

@Composable
fun MyApp(content: @Composable ()->Unit){
    content()

}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MyContent() {
    //Greeting3("Android")
    var text by remember { mutableStateOf("") }
    ComposeApplicationTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background) {
            Scaffold(topBar = {  TopAppBar(title = { Text(text = "Rajat App") }, navigationIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Filled.Menu, contentDescription ="menu" )
                }

            },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Filled.Notifications, contentDescription ="noti" )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Filled.Search, contentDescription ="search" )
                    }
                }

            )

            }) {
                LaunchedEffect(key1 = text ){
                    delay(1000L)
                    println("The text is $text")
                }
            }
            


        }
    }

}

@Composable
fun Greeting3(name: String) {
    Text(text = "Hello $name!")


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview4() {
    ComposeApplicationTheme {
       // Greeting3("Android")
        MyContent()
    }
}