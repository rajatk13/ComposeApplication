package com.app.composeapplication.firstApp.ui.feture_note.AddEditNote


import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save


import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.composeapplication.firstApp.ui.feture_note.AddEditNote.component.TransparentHintTextField
import com.app.composeapplication.firstApp.ui.feture_note.Note.NoteScreen
import com.app.composeapplication.firstApp.ui.roomDB.Note
import com.app.composeapplication.second_App.presentation.ui.theme.ComposeApplicationTheme
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun AddEditNoteScreen(
    navController: NavController,
    noteColor: Int,
    viewModel: AddEditNoteViewModel = hiltViewModel()
) {
    val titleState = viewModel.noteTitle.value
    val contentState = viewModel.noteContent.value

    val scafFoldState = rememberScaffoldState()

    val noteBackGroundAnimatable = remember {
        Animatable(Color(if (noteColor != -1) noteColor else viewModel.noteColor.value))

    }
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true){
        viewModel.eventFlow.collectLatest { event->
            when(event){
                is AddEditNoteViewModel.UiEvent.ShowSnackbar->{
              scafFoldState.snackbarHostState.showSnackbar( message = event.message
              )
                }
                is AddEditNoteViewModel.UiEvent.SavedNote -> {
                        navController.navigateUp()
                }
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick =
                {
                    viewModel.onEvent(AddEditNoteEvent.SaveNote)
                }, backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(imageVector = Icons.Default.Save, contentDescription = "Save note")
            }

        },
        scaffoldState = scafFoldState
    ) { paddind-> paddind
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(noteBackGroundAnimatable.value)
                .padding(16.dp)
        ) {
            Row(
                modifier =  Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                   horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Note.notColors.forEach { color ->
                    val colorInt = color
                    Box(modifier = Modifier
                        .size(50.dp)
                        .shadow(15.dp, CircleShape)
                        .clip(CircleShape)

                         .background(Color(color))
                        .border(
                            width = 3.dp,
                            color = if (viewModel.noteColor.value == colorInt) {
                                Color.Black
                            } else Color.Transparent,
                            shape = CircleShape
                        )
                        .clickable {
                            scope.launch {
                                noteBackGroundAnimatable.animateTo(
                                    targetValue = Color(colorInt),
                                    animationSpec = tween(
                                        durationMillis = 500
                                    )
                                )
                            }
                            viewModel.onEvent(AddEditNoteEvent.changeColor(colorInt))
                        }
                    )

                }
            }

           Spacer(modifier = Modifier.height(16.dp))

            TransparentHintTextField(text = titleState.text, hint = titleState.hint,
                onVlaueChange ={
                                 viewModel.onEvent(AddEditNoteEvent.changeTitle(it))
            } , onFocusChange = {
                    viewModel.onEvent(AddEditNoteEvent.changeTitleFocus(it))
                }, isHintVisible = titleState.isHintVisible,
            singleLine = true,
            textStyle = MaterialTheme.typography.h5)

            Spacer(modifier = Modifier.height(16.dp))
            TransparentHintTextField(text = contentState .text, hint = contentState.hint,
                onVlaueChange ={
                    viewModel.onEvent(AddEditNoteEvent.changeContent(it))
                } , onFocusChange = {
                    viewModel.onEvent(AddEditNoteEvent.changeContentFocus(it))
                }, isHintVisible = contentState.isHintVisible,
                singleLine = true,
                textStyle = MaterialTheme.typography.h5)

            Spacer(modifier = Modifier.height(16.dp))
            TransparentHintTextField(text = contentState .text, hint = contentState.hint,
                onVlaueChange ={
                    viewModel.onEvent(AddEditNoteEvent.changeContent(it))
                } , onFocusChange = {
                    viewModel.onEvent(AddEditNoteEvent.changeContentFocus(it))
                }, isHintVisible = contentState.isHintVisible,
                textStyle = MaterialTheme.typography.body1,
            modifier = Modifier.fillMaxHeight())
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    ComposeApplicationTheme {
        //  Greeting3("Android")
        AddEditNoteScreen( navController = rememberNavController(),0x3ffff  )
    }
}