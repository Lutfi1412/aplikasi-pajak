@file:Suppress("UNUSED_EXPRESSION")

package org.example.pajak.ui.components.table

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Checkbox
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TableRows(
    headers: List<String>,
    sortedRows: List<List<String>>,
    rowCheckStates: MutableList<Boolean>,
    onRowCheckedChange: (Int, Boolean) -> Unit,
    onClick: (Int) -> Unit = {}
) {
    val isActionColumn = headers.lastOrNull() == "Detail"

    sortedRows.forEachIndexed { rowIndex, row ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    if (rowIndex % 2 == 0) Color(0xFFECF0F1) else Color(0xFFD5DBDB)
                )
                .padding(vertical = 10.dp, horizontal = 8.dp)
        ) {
            // Checkbox untuk setiap baris
            Box(
                modifier = Modifier.weight(0.2f),
                contentAlignment = Alignment.Center
            ) {
                Checkbox(
                    checked = rowCheckStates.getOrElse(rowIndex) { false }, // Pastikan index tidak out of bounds
                    onCheckedChange = { isChecked ->
                        onRowCheckedChange(rowIndex, isChecked)
                    },
                    modifier = Modifier.size(25.dp)
                )
            }

            row.forEachIndexed { cellIndex, cell ->
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = cell,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        color = Color.Black
                    )
                }
            }
            if (isActionColumn) {
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(
                        onClick = { onClick(rowIndex) },
                        modifier = Modifier.size(25.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = "Detail",
                            tint = Color.Yellow
                        )
                    }
                }
            }
        }
    }
}
