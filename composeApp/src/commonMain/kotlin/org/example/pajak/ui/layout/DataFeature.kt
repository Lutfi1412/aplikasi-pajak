package org.example.pajak.ui.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.pajak.ui.components.Date.DatePickerButton
import org.example.pajak.ui.components.handler.SearchBar
import org.example.pajak.ui.components.handler.TrashIconButton

@Composable
fun DataFeature(
    searchText: String,
    onValueChange: (String) -> Unit,
    onClickDelete: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF6F6F6))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SearchBar(
            value = searchText,
            onValueChange = onValueChange, // Fungsi ini dipanggil dengan parameter baru
            modifier = Modifier
                .weight(1f)
                .padding(end = 15.dp)
        )
        TrashIconButton(onClick = onClickDelete)
    }
}