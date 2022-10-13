package com.app.composeapplication.fifthApp.ui.screens.note

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.composeapplication.fifthApp.ui.screens.note.model.MyNoteEvents
import com.app.composeapplication.fifthApp.ui.theme.ComposeApplicationTheme
import com.app.composeapplication.firstApp.ui.feture_note.Main_Sample2Activity
import com.app.composeapplication.firstApp.ui.feture_note.utils.Screen
import com.app.composeapplication.second_App.presentation.Main_Sample3Activity
import com.app.composeapplication.thirdApp.worker.WorkManagerSampleActivity
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.selects.select
//@ExperimentalAnimationApi
//@ExperimentalPagerApi
@Composable
fun MyNoteScreen(
    viewModel: MyNoteViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.state.value
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

   val context= LocalContext.current
      Scaffold(floatingActionButton = {
          FloatingActionButton(onClick = { navController.navigate(Screen.MyAddEditNoteScreen.passId_Color(-1,-1)) }) {
              Icon(imageVector = Icons.Default.Add, contentDescription ="Add note" )
          }

      }, scaffoldState = scaffoldState
      ) { it
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)) {
                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End) {
                    Text(text = "Your Note", style = MaterialTheme.typography.h4)
                    IconButton(onClick = {
                        val s = viewModel.event(MyNoteEvents.ToggleOrderSection)
                        //Toast.makeText(context,"$s", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(imageVector = Icons.Default.Sort, contentDescription = "Sort")

                    }

                    IconButton(onClick = {
                        context.startActivity(Intent(context, WorkManagerSampleActivity::class.java))
                    //Toast.makeText(context,"$s", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(imageVector = Icons.Default.NextPlan, contentDescription = "Nextplan")
                    }
                    IconButton(onClick = {
                       // context.startActivity(Intent(context, Main_Sample3Activity::class.java))
                        navController.navigate(Screen.MyMapScreen.route)
                    //Toast.makeText(context,"$s", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(imageVector = Icons.Default.Map, contentDescription = "NavigateNext")
                    }
                }
                Toast.makeText(context,"${state.isToggled}", Toast.LENGTH_SHORT).show()
                 AnimatedVisibility(visible = state.isToggled,
                     enter = fadeIn() + slideInVertically(),
                     exit = fadeOut() + slideOutVertically()) {
                     RadioOrderButtons(modifier = Modifier,
                        noteOrder = state.myNoteOrderType,
                         orderChange = {
                             viewModel.event(MyNoteEvents.Order(it))}
                     )
                 }
                  LazyColumn(modifier = Modifier.fillMaxSize()){

                      items(state.note){ note->
                          Spacer(modifier = Modifier.height(10.dp))
                          MyNoteitems(note = note,
                              modifier = Modifier
                                  .fillMaxSize()
                                  .clickable {
                                      val id = note.id
                                      val color = note.color
                                      Log.e( "SetupnavGraph: ", "noteColor---$id..$color..${note.color}" )
                                       /*navController.navigate(Screen.MyAddEditNoteScreen.route +
                                               "?noteId={$id}&noteColor={$color}"
                                       )*/
                                      navController.navigate(Screen.MyAddEditNoteScreen.passId_Color(id!!,color)

                                       )
                                  },
                          onDeleteClick = {
                              viewModel.event(MyNoteEvents.DeleteNote(note))
                              scope.launch {
                                  val result = scaffoldState.snackbarHostState.showSnackbar(message = "note deleted", actionLabel = "undo")
                                  if(result==SnackbarResult.ActionPerformed)
                                      viewModel.event(MyNoteEvents.RestoreNote)
                              }

                          })
                      }
                  }

            }
      }

}
@ExperimentalAnimationApi
@ExperimentalPagerApi
@Preview(showBackground = true)
@Composable
fun NotePriview() {
    ComposeApplicationTheme {
        MyNoteScreen(navController = rememberNavController())
      //  RadioOrderButtons("",true,{})
    }
}