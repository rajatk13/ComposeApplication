package com.app.composeapplication.fifthApp.ui.screens.AddEdit

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.app.composeapplication.fourthApp.launchedEffect.ui.theme.ComposeApplicationTheme


@Composable
fun  MyTransparentTextField(
    text:String,
    hint:String,
    modifier: Modifier = Modifier,
    isHintVisible:Boolean = true,
    textStyle: TextStyle = TextStyle(),
    onValueChange:(String)->Unit,
    onFocusChange:(FocusState)->Unit,
    singleLine:Boolean = false,
    keyboardOptions: KeyboardOptions= KeyboardOptions(),
    keyboardActions: KeyboardActions= KeyboardActions ()
) {
  /*  keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
    keyboardActions = KeyboardActions(
        onNext = { focusRequester.requestFocus() }
    )*/
    Box(modifier = modifier) {
        BasicTextField(value =text , onValueChange = onValueChange, singleLine=singleLine,
            keyboardOptions = keyboardOptions, keyboardActions=keyboardActions,textStyle=textStyle,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    onFocusChange(it)
                })

        if(isHintVisible){
            Text(text = hint, style = textStyle,  color = Color.DarkGray)
        }
    }


}
@Preview(showBackground = true)
@Composable
fun MyTranspTextField() {
    ComposeApplicationTheme {
      MyTransparentTextField(text = "rajat", hint ="hhhhhhint...", onValueChange = {}, onFocusChange = {}, keyboardOptions = KeyboardOptions())
    }

}