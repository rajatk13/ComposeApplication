package com.app.composeapplication.firstApp.ui.feture_note.AddEditNote.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.app.composeapplication.fifthApp.ui.screens.AddEdit.MyAddEditNoteScreem
import com.app.composeapplication.fourthApp.launchedEffect.ui.theme.ComposeApplicationTheme


@Composable
fun TransparentHintTextField(
    text:String,
    hint:String,
    modifier: Modifier= Modifier,
    isHintVisible:Boolean =true,
    onVlaueChange:(String) ->Unit,
    textStyle: TextStyle = TextStyle(),
    singleLine:Boolean = false,
    onFocusChange:(FocusState)->Unit
) {
    Box(modifier = modifier){
        BasicTextField(value = text, onValueChange = onVlaueChange, singleLine = singleLine,
                     textStyle=textStyle, modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged {
                onFocusChange(it)
            }
         )
        if(isHintVisible){
            Text(text = hint, style = textStyle, color = Color.DarkGray)
        }
    }
    
}
@Preview(showBackground = true)
@Composable
fun MyTransparentTextField() {
    ComposeApplicationTheme {
        TransparentHintTextField(text = "rajat",
            hint = "hhhhhh...", isHintVisible = false,
            onVlaueChange = {},
            onFocusChange = {})
    }

}