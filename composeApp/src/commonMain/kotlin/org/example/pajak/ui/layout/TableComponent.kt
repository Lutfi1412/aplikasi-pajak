package org.example.pajak.ui.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.pajak.ui.components.table.TableHeader
import org.example.pajak.ui.components.table.TableRows
import org.example.pajak.ui.components.table.getSortedRows


@Composable
fun TableComponent(
    headers: List<String>,
    rows: List<List<String>>,
    rowCheckStates: MutableList<Boolean>,
    onClick: (Int) -> Unit = {}
) {
    val scrollState = rememberScrollState()
    var sortColumn by remember { mutableStateOf(-1) } // -1 berarti tidak ada kolom yang disortir
    var sortAscending by remember { mutableStateOf(true) } // true untuk ascending, false untuk descending

    var isAllChecked by remember { mutableStateOf(false) } // Status untuk checkbox Select All

    val sortedRows = getSortedRows(
        rows = rows,
        sortColumn = if (sortColumn >= 0) sortColumn else -1, // Pastikan sortColumn valid
        sortAscending = sortAscending
    )


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
            .background(Color.White)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header
        TableHeader(
            headers = headers,
            isAllChecked = isAllChecked,
            onAllCheckedChange = {
                isAllChecked = it
                rowCheckStates.fill(it) // Mengatur status semua baris sesuai dengan status header
            },
            sortColumn = sortColumn,
            sortAscending = sortAscending,
            onSortChange = { index, ascending ->
                sortColumn = index
                sortAscending = ascending
            }
        )

        // Rows
        TableRows(
            headers = headers, // Tambahkan headers ke parameter
            sortedRows = sortedRows,
            rowCheckStates = rowCheckStates,
            onRowCheckedChange = { index, isChecked ->
                rowCheckStates[index] = isChecked
                isAllChecked = rowCheckStates.all { it }
            },
            onClick = { rowIndex ->
                onClick(rowIndex) // Mengirimkan indeks baris yang diklik
            }
        )
    }
}