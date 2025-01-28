package org.example.pajak.ui.components.table

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.pajak.ui.components.icon.MoveDown
import org.example.pajak.ui.components.icon.MoveUp

@Composable
fun SortIcons(isSorted: Boolean, isAscending: Boolean, onSortChange: (Boolean) -> Unit) {
    Row {
        IconButton(
            onClick = { onSortChange(true) },
            modifier = Modifier.size(16.dp)
        ) {
            Icon(
                imageVector = MoveUp,
                contentDescription = "Sort Ascending",
                tint = if (isSorted && isAscending) Color.Black else Color.White
            )
        }
        IconButton(
            onClick = { onSortChange(false) },
            modifier = Modifier.size(16.dp)
        ) {
            Icon(
                imageVector = MoveDown,
                contentDescription = "Sort Descending",
                tint = if (isSorted && !isAscending) Color.Black else Color.White
            )
        }
    }
}