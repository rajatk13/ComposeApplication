package com.app.composeapplication.second_App.presentation.home


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
//import coil.compose.rememberAsyncImagePainter
//import coil.compose.rememberImagePainter
import com.app.composeapplication.second_App.domain.model.CharacterPojo
import com.app.composeapplication.second_App.domain.model.CharacterPojoItem

@Composable
fun HomeScreen( ) {
    val viewModel: HomeViewModel=  viewModel(modelClass = HomeViewModel::class.java)
    val state by viewModel.state.collectAsState()
    LazyColumn{
        if(state.isEmpty()){
            item {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .fillMaxHeight()
                        .wrapContentSize(align = Alignment.Center)
                )
            }
        }else
            items(state){character: CharacterPojoItem ->
         CharaterImageCard(characterPojoItem = character)
       }
    }
}

@Composable
fun CharaterImageCard(characterPojoItem: CharacterPojoItem) {
//     val imagePainter =     rememberAsyncImagePainter(model =characterPojoItem.image )
     val imagePainter =     rememberImagePainter(data =characterPojoItem.image )
     TopAppBar() {
         
     }
    Card(shape = MaterialTheme.shapes.medium, modifier = Modifier.padding(16.dp)) {
          Box {
            Image(painter = imagePainter, contentDescription = "new Image", modifier = Modifier
                .fillMaxWidth()
                .height(200.dp), contentScale = ContentScale.FillBounds)
              
               Surface(color = MaterialTheme.colors.onSurface.copy(alpha = 0.3f),
               modifier = Modifier.align(Alignment.BottomCenter), contentColor = MaterialTheme.colors.surface) {
                   Column(modifier = Modifier
                       .fillMaxWidth()
                       .padding(14.dp)) {

                       Text(text = "Real name: ${characterPojoItem.actor}")
                       Text(text = "Real name: ${characterPojoItem.name}")

                   }

              }
          }
    }
}