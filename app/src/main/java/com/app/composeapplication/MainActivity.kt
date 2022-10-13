package com.app.composeapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.composeapplication.firstApp.ui.theme.ComposeApplicationTheme


class MainActivity : ComponentActivity() {
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
fun MyApp(content:@Composable ()-> Unit){
    ComposeApplicationTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
             content()
        }
    }
}
@Composable
//fun MyContent(names:List<String> = listOf("Android","kotlin")){
fun MyContent(names:List<String> = List(1000){"Android $it"}){
    var counterState  by remember{
        mutableStateOf(0)
    }

  Column(modifier = Modifier.fillMaxHeight()) {
          NameList(names = names, Modifier.weight(1f))
       Counter(counter =counterState, updateCounter ={ newState -> counterState = newState} )

      if(counterState>5) {
          Text(text = "i love to count!..")
      }

  }
}
@Composable
fun NameList(names: List<String>, modifier: Modifier=Modifier){
    LazyColumn(modifier = modifier) {
        items ( items = names){
            Greeting(name = "$it")
            Divider(modifier.padding(1.dp))
        }
//        for (name in names) {
//
//        }
    }
}
@Composable
fun Counter(counter:Int, updateCounter:(Int)->Unit){

    Button(onClick = { updateCounter(counter+1)}) {
        Text(text = "I've been clicked counter $counter times")
        
    }
}
@Composable
fun Greeting(name: String, modifier: Modifier= Modifier) {
     Surface(color = Color.Magenta) {
        Text(text = "Hello $name!" ,
            modifier
                .padding(10.dp)
                .background(color = Color.Yellow))
    }

}


//@Preview(fontScale = 1.1f)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeApplicationTheme {
        MyContent()
    }
}