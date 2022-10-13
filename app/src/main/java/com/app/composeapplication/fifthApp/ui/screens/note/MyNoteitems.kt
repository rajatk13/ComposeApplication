package com.app.composeapplication.fifthApp.ui.screens.note

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import com.app.composeapplication.firstApp.ui.roomDB.Note
import com.app.composeapplication.second_App.presentation.ui.theme.ComposeApplicationTheme

@Composable
fun MyNoteitems(
    note: Note,
    modifier: Modifier=Modifier,
    cornerRadius: Dp = 10.dp,
    cutCornerSize:Dp = 30.dp,
    onDeleteClick:()->Unit
) {
 Box(modifier = modifier, ) {
     Canvas(modifier = Modifier
         .matchParentSize()
         )
     {
         val path = Path().apply {
             lineTo(size.width-cutCornerSize.toPx(),0f )
             lineTo(size.width, cutCornerSize.toPx())
             lineTo(size.width, size.height)
             lineTo(0f, size.height)
             close()
         }
         clipPath(path){
             drawRoundRect(color = Color(note.color)
                 , size = size, cornerRadius = CornerRadius(cornerRadius.toPx()))
             drawRoundRect(color = Color(ColorUtils.blendARGB(note.color, 0x000, 0.3f)),
                 topLeft = Offset(size.width - cutCornerSize.toPx(), -100f),
                 size = Size(cutCornerSize.toPx() + 100f, cutCornerSize.toPx() + 100f),
                 cornerRadius = CornerRadius(cornerRadius.toPx()))
         }
     }




     Column(modifier = Modifier
         .fillMaxSize()
         .padding(8.dp)
         .padding(end = 16.dp)) {
         Text(
             text = note.title,
             maxLines = 1,
             color = MaterialTheme.colors.onSurface,
             style = MaterialTheme.typography.h4,
             overflow = TextOverflow.Ellipsis
         )
         Spacer(modifier = Modifier.height(8.dp))
         Text(
             text = note.content,
             maxLines = 10,
             color = MaterialTheme.colors.onSurface,
             style = MaterialTheme.typography.body1,
             overflow = TextOverflow.Ellipsis
         )

     }
     IconButton(onClick = onDeleteClick, modifier = Modifier.align(Alignment.BottomEnd)) {
         Icon(imageVector = Icons.Default.Delete, contentDescription ="Delete note" )

     }

 }
}
@Preview(showBackground = true)
@Composable
fun priviewN() {
    ComposeApplicationTheme {
         MyNoteitems( note= Note(title = "rajat", content = "gfhfhfh", timeStyamp = 0L, color = 1233333),modifier =Modifier,10.dp,30.dp) {}
    }
}