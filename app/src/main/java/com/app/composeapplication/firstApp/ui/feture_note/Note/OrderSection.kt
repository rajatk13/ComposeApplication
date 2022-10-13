package com.app.composeapplication.firstApp.ui.feture_note.Note
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.app.composeapplication.firstApp.ui.feture_note.domain.notesUseCase.NotrOrderType
import com.app.composeapplication.firstApp.ui.feture_note.domain.notesUseCase.OrderType

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    notrOrderType: NotrOrderType =  NotrOrderType.Date(OrderType.Descending),
    onOrderChange:(NotrOrderType)->Unit

) {
    Column(modifier = modifier) {
        Row(modifier = Modifier.fillMaxWidth()) {
            DefaultRadioButton(text = "Title", selected = notrOrderType is NotrOrderType.Title, onSelect = { onOrderChange(NotrOrderType.Title(notrOrderType.orderType))})
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(text = "Date", selected = notrOrderType is NotrOrderType.Date, onSelect = { onOrderChange(NotrOrderType.Date(notrOrderType.orderType))})
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(text = "Color", selected = notrOrderType is NotrOrderType.Color, onSelect = { onOrderChange(NotrOrderType.Color(notrOrderType.orderType))})
            Spacer(modifier = Modifier.width(8.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            DefaultRadioButton(text = "Ascending", selected = notrOrderType.orderType is OrderType.Ascending, onSelect = { onOrderChange(notrOrderType.copy(OrderType.Ascending))})
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(text = "Descending", selected = notrOrderType.orderType is OrderType.Descending, onSelect = { onOrderChange(notrOrderType.copy(OrderType.Descending))})
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}