package com.app.composeapplication.fifthApp.ui.screens.AddEdit


import android.graphics.Color.*
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalSavedStateRegistryOwner
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.app.composeapplication.firstApp.ui.feture_note.AddEditNote.AddEditNoteEvent
import com.app.composeapplication.firstApp.ui.feture_note.AddEditNote.AddEditNoteViewModel
import com.app.composeapplication.firstApp.ui.feture_note.AddEditNote.component.TransparentHintTextField
import com.app.composeapplication.firstApp.ui.roomDB.Note
import com.app.composeapplication.fourthApp.launchedEffect.ui.theme.ComposeApplicationTheme
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MyAddEditNoteScreem(
    navHostController: NavHostController,
    noteColor: Int,
    viewModel: MyAddEditVM = hiltViewModel(),
) {
     val titleState= viewModel.notTitle.value
     val keyboardController= LocalSoftwareKeyboardController?.current
     val contentState= viewModel.noteContent.value
    val scope = rememberCoroutineScope()
     val noteBackGroundAnimatable= remember {
       Animatable(Color(if(noteColor!=-1) noteColor else viewModel.noteColor.value))
     }
    val scafFoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true ){
        viewModel.eventFlow.collectLatest { event->
            when(event){
                is MyAddEditVM.UiEvent.ShowSnackbar->{
                    scafFoldState.snackbarHostState.showSnackbar( message = event.message
                    )
                }
                is MyAddEditVM.UiEvent.SavedNote -> {
                    navHostController.navigateUp()
                }
            }

        }
    }

    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = {  viewModel.onEvent(MyAddEditNoteEvent.SaveNote) },
            backgroundColor = MaterialTheme.colors.primary) {
            Icon(imageVector = Icons.Default.Save, contentDescription ="Save note" )
        }
    }, scaffoldState = scafFoldState,
        topBar = {
            TopAppBar(
                title = { Text( "My Colors")},
           navigationIcon = {
               IconButton(onClick = { /* doSomething() */ }) {
                   Icon(Icons.Filled.Menu, contentDescription = "Localized description")
               }
           },
                actions = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(Icons.Filled.Favorite, contentDescription = "Localized description")
                    }
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(Icons.Filled.Favorite, contentDescription = "Localized description")
                    }
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(Icons.Filled.Notifications, contentDescription = "Localized description")
                    }
                },
            backgroundColor = MaterialTheme.colors.onPrimary,
            contentColor = contentColorFor(backgroundColor),
        modifier = Modifier.fillMaxWidth()) }
    ){ it
        Column(modifier = Modifier
            .fillMaxSize()
            .background(noteBackGroundAnimatable.value)
            .padding(16.dp)) {
             Row(modifier = Modifier
                 .fillMaxWidth()
                 .padding(8.dp),
              horizontalArrangement = Arrangement.SpaceBetween)
             {
                Note.notColors.forEach { color->
                     Box(modifier = Modifier
                         .size(50.dp)
                         .shadow(15.dp, CircleShape)
                         .background(Color(color))
                         .border(
                             width = 3.dp,
                             shape = CircleShape,
                             color = if (viewModel.noteColor.value == color) {
                                 Color.Black
                             } else Color.White

                         )
                         .clickable {
                             scope.launch {
                                 noteBackGroundAnimatable.animateTo(
                                     targetValue = Color(color),
                                     animationSpec = tween(
                                         durationMillis = 500
                                     )
                                 )
                             }
                             viewModel.onEvent(MyAddEditNoteEvent.changeColor(color))
                         }
                     )
                     
                 }
             }
            Spacer(modifier = Modifier.height(16.dp))

            MyTransparentTextField(text = titleState.text, hint = titleState.hint, onValueChange = {
                  viewModel.onEvent(MyAddEditNoteEvent.changeTitle(it))
            }, onFocusChange = {
                viewModel.onEvent(MyAddEditNoteEvent.changeTitleFocus(it))
            }

               , textStyle = MaterialTheme.typography.h4, isHintVisible = titleState.isHintVisible,
                singleLine = true, keyboardOptions =  KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onDone = { keyboardController?.show()}
                ))
            Spacer(modifier = Modifier.height(16.dp))


            MyTransparentTextField(text = contentState.text, hint = contentState.hint, onValueChange = {
                  viewModel.onEvent(MyAddEditNoteEvent.changeContent(it))
            }, onFocusChange = {
                viewModel.onEvent(MyAddEditNoteEvent.changeContentFocus(it))
            }
               , textStyle = MaterialTheme.typography.h5, isHintVisible = contentState.isHintVisible,
                singleLine = false,keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = { keyboardController?.hide()}
                ))
            Spacer(modifier = Modifier.height(16.dp))

            MyTransparentTextField(text = contentState.text, hint = contentState.hint, onValueChange = {
                  viewModel.onEvent(MyAddEditNoteEvent.changeContent(it))
            }, onFocusChange = {
                viewModel.onEvent(MyAddEditNoteEvent.changeContentFocus(it))
            }
               , textStyle = MaterialTheme.typography.body1, isHintVisible = contentState.isHintVisible,
                singleLine = false, keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = { keyboardController?.hide()}
                ))

        }
    }
}
@Preview(showBackground = true, name = "MyAddEdit")
@Composable
fun privieww() {
    ComposeApplicationTheme {
        MyAddEditNoteScreem(navHostController = rememberNavController(),0)
    }

}