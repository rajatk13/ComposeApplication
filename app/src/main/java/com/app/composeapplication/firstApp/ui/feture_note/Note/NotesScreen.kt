package com.app.composeapplication.firstApp.ui.feture_note.Note

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.composeapplication.firstApp.ui.feture_note.domain.model.NoteViewModel
import com.app.composeapplication.firstApp.ui.feture_note.domain.notesUseCase.NoteEvent
import com.app.composeapplication.firstApp.ui.feture_note.utils.Screen

import com.app.composeapplication.second_App.presentation.ui.theme.ComposeApplicationTheme
import kotlinx.coroutines.launch
//@Preview(showBackground = true)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NoteScreen(
     navController: NavController,
     viewModel: NoteViewModel = hiltViewModel()
) {
     val state = viewModel.state.value
     val scaffoldState = rememberScaffoldState()
     val scope = rememberCoroutineScope()

     Scaffold(
          floatingActionButton = {
              FloatingActionButton(onClick = {
                  navController.navigate(Screen.AddEditNoteScreen.route)
              }, backgroundColor = MaterialTheme.colors.primary)
              {
                  Icon(imageVector = Icons.Default.Add, contentDescription = "Add note")
              }
     }, scaffoldState = scaffoldState
     ) {
            Column(modifier = Modifier

                .padding(16.dp)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween,
                 verticalAlignment = Alignment.Top) {
                  Text(text = "Your note", style = MaterialTheme.typography.h4 )
                     IconButton(onClick = { viewModel.onEvent(NoteEvent.ToggleOrderSection) }) {
                          Icon(imageVector = Icons.Default.Sort, contentDescription ="Sort" )
                     }
                }
                AnimatedVisibility(
                    visible = state.isOrderSectionVisible,
                    enter = fadeIn() + slideInVertically(),
                    exit = fadeOut() + slideOutVertically ()
                ) {
                      OrderSection(
                          modifier = Modifier
                              .fillMaxWidth()
                              .padding(vertical = 16.dp),
                          notrOrderType = state.noteorder,
                          onOrderChange = {
                              viewModel.onEvent(NoteEvent.Order(it))
                          }
                      )
                }
              //      Spacer(modifier = Modifier.height(16.dp))
                LazyColumn(modifier = Modifier.fillMaxSize()){

            items(state.note){ note->
                Spacer(modifier = Modifier.height(8.dp))
                NoteItem(note = note,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        navController.navigate(Screen.AddEditNoteScreen.route + "?noteId=${note.id}&noteColor=${note.color}")
                    },
                onDeleteClick = {
                    viewModel.onEvent(NoteEvent.DeleteNote(note))
                    scope.launch {
                      val result =   scaffoldState.snackbarHostState.showSnackbar( message = "Note deleted", actionLabel = "Undo")
                        if(result == SnackbarResult.ActionPerformed){
                            viewModel.onEvent(NoteEvent.RestoreNote )
                        }
                    }
                }
                ) 


            }
                }
            }
     }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    ComposeApplicationTheme {
        //  Greeting3("Android")
        NoteScreen( navController = rememberNavController() )
    }
}