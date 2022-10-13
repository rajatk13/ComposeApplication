package com.app.composeapplication.fifthApp.ui.screens.note

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.room.Index
import com.app.composeapplication.fifthApp.ui.screens.note.model.MyNoteOrderType
import com.app.composeapplication.fifthApp.ui.screens.note.model.MyOrderType
import com.app.composeapplication.fifthApp.ui.theme.ComposeApplicationTheme
import com.app.composeapplication.firstApp.ui.feture_note.domain.notesUseCase.NotrOrderType
import com.app.composeapplication.firstApp.ui.feture_note.domain.notesUseCase.OrderType

@Composable
fun RadioOrderButtons(
    modifier: Modifier = Modifier,
    noteOrder: MyNoteOrderType=MyNoteOrderType.Date(MyOrderType.Descending),
    orderChange: (MyNoteOrderType) -> Unit,

    ) {

    Column {
        Row() {
            MyRadioButton(text = "Title" , selected = noteOrder is MyNoteOrderType.Title, onSelected = {orderChange(MyNoteOrderType.Title(noteOrder.orderType))})
            Spacer(modifier = Modifier.width(8.dp))
            MyRadioButton(text = "Date", selected = noteOrder is MyNoteOrderType.Date, onSelected = {orderChange(MyNoteOrderType.Date(noteOrder.orderType))})
            Spacer(modifier = Modifier.width(8.dp))
            MyRadioButton(text = "Color", selected = noteOrder is MyNoteOrderType.ColorM, onSelected = {orderChange(MyNoteOrderType.ColorM(noteOrder.orderType))})

        }
        Row() {
            MyRadioButton(text = "Ascending", selected = noteOrder.orderType is MyOrderType.Ascending, onSelected = {orderChange(noteOrder.copy(MyOrderType.Ascending))})
            Spacer(modifier = Modifier.width(8.dp))
            MyRadioButton(text = "Descending", selected = noteOrder.orderType is MyOrderType.Descending, onSelected = {orderChange(noteOrder.copy(MyOrderType.Descending))})

        }

    }

}

@Preview(showBackground = true)
@Composable
fun priview() {
    ComposeApplicationTheme {
        RadioOrderButtons() {}
    }
}