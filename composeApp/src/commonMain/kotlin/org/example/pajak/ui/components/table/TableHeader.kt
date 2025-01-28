package org.example.pajak.ui.components.table

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TableHeader(
    headers: List<String>,
    isAllChecked: Boolean,
    onAllCheckedChange: (Boolean) -> Unit,
    sortColumn: Int,
    sortAscending: Boolean,
    onSortChange: (Int, Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF34495E))
            .padding(vertical = 12.dp, horizontal = 8.dp)
    ) {
        // Checkbox pada header
        Box(
            modifier = Modifier.weight(0.2f),
            contentAlignment = Alignment.Center
        ) {
            Checkbox(
                checked = isAllChecked,
                onCheckedChange = onAllCheckedChange,
                modifier = Modifier.size(25.dp)
            )
        }

        headers.forEachIndexed { index, header ->
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = header,
                        color = Color.White,
                        fontSize = 16.sp,
                        style = MaterialTheme.typography.body1,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.width(4.dp))

                    if (header != "Detail") {
                        SortIcons(
                            isSorted = sortColumn >= 0 && sortColumn == index, // Pastikan sortColumn valid
                            isAscending = sortAscending,
                            onSortChange = { ascending ->
                                onSortChange(index, ascending)
                            }
                        )
                    }

                }
            }
        }
    }
}