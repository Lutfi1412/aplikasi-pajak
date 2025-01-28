package org.example.pajak.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.example.pajak.data.LaporanItem
import org.example.pajak.services.deleteLaporan
import org.example.pajak.services.fetchLaporanData
import org.example.pajak.ui.components.Date.DateRangePickerModal
import org.example.pajak.ui.components.Date.getFormattedDateRange
import org.example.pajak.ui.components.handler.getFilteredRows
import org.example.pajak.ui.components.icon.PersonAdd
import org.example.pajak.ui.layout.LaporanFeature
import org.example.pajak.ui.layout.TableComponent
import java.text.SimpleDateFormat
import java.util.Locale

@ExperimentalMaterial3Api
@Composable
fun Laporan() {

    val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    var searchText by remember { mutableStateOf("") }
    var startDate by remember { mutableStateOf<Long?>(null) }
    var endDate by remember { mutableStateOf<Long?>(null) }
    var isDateRangePickerVisible by remember { mutableStateOf(false) }
    var rows by remember { mutableStateOf<List<LaporanItem>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    val formattedDateRange = getFormattedDateRange(startDate, endDate, dateFormatter)

    val filteredRows = getFilteredRows(
        rows = rows,
        startDate = startDate,
        endDate = endDate,
        searchText = searchText,
        dateFormatter = dateFormatter,
        dateExtractor = { it.tanggal },
        filterLogic = { row, search ->
            row.jenis.contains(search, ignoreCase = true) ||
                    row.tanggal.contains(search, ignoreCase = true) ||
                    row.plat.contains(search, ignoreCase = true) ||
                    row.keterangan.contains(search, ignoreCase = true)
        }
    )
    val rowCheckStates = remember {
        mutableStateListOf(*Array(filteredRows.size) { false })
    }

    LaunchedEffect(filteredRows) {
        if (filteredRows.isNotEmpty()) {
            rowCheckStates.clear()
            rowCheckStates.addAll(Array(filteredRows.size) { false })
        }
    }
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("Laporan Pajak") },
            backgroundColor = Color(0xFF2C3E50),
            contentColor = Color.White,
            actions = {
                IconButton(onClick = {
                    isLoading = true
                }) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "More options",
                        tint = Color.White
                    )
                }
            }
        )

        LaporanFeature(
            searchText = searchText,
            onValueChange = { searchText = it },
            onDatePickerClick = { isDateRangePickerVisible = true },
            selectedDate = formattedDateRange,
            onClickDelete = {
                val selectedIds = filteredRows
                    .filterIndexed { index, _ -> rowCheckStates.getOrElse(index) { false } }
                    .map { it.id }
                coroutineScope.launch {
                    deleteLaporan(selectedIds)
                    isLoading = true
                }
            }
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF6F6F6))
                .padding(16.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            if (isLoading) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
                LaunchedEffect(Unit) {
                    val data = fetchLaporanData()
                    rows = data
                    isLoading = false
                    searchText = ""
                }
            } else {
                if (filteredRows.isNotEmpty()) {
                    TableComponent(
                        headers = listOf("No", "Tanggal", "Jam", "Jenis", "Plat", "Keterangan"),
                        rows = filteredRows.mapIndexed { index, item ->
                            listOf(
                                (index + 1).toString(),
                                item.tanggal,
                                item.jam,
                                item.jenis,
                                item.plat,
                                item.keterangan
                            )
                        },
                        rowCheckStates = rowCheckStates
                    )
                } else {
                    Text("Data tidak ditemukan", color = Color.Gray)
                }
            }
        }

        if (isDateRangePickerVisible) {
            DateRangePickerModal(
                onDateRangeSelected = { selectedRange ->
                    startDate = selectedRange.first
                    endDate = selectedRange.second
                    isDateRangePickerVisible = false
                },
                onDismiss = { isDateRangePickerVisible = false }
            )
        }
    }
}













