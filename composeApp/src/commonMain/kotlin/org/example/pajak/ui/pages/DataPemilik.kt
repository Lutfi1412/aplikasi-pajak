package org.example.pajak.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
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
import org.example.pajak.data.PemilikItem
import org.example.pajak.services.deleteData
import org.example.pajak.services.fetchDataPemilik
import org.example.pajak.ui.components.handler.getFilteredRows
import org.example.pajak.ui.components.icon.PersonAdd
import org.example.pajak.ui.layout.DataFeature
import org.example.pajak.ui.layout.TableComponent

@ExperimentalMaterial3Api
@Composable
fun DataPemilik(navigateToDetail: (Int) -> Unit, onClick: () -> Unit) {
    var searchText by remember { mutableStateOf("") }
    var rows by remember { mutableStateOf<List<PemilikItem>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    val filteredRows = getFilteredRows(
        rows = rows,
        searchText = searchText,
        dateExtractor = null,
        filterLogic = { row, search ->
            row.jenis.contains(search, ignoreCase = true) ||
                    row.tenggat_thn.toString().contains(search, ignoreCase = true) ||
                    row.tenggat_bln.toString().contains(search, ignoreCase = true) ||
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
            title = { Text("Data Pemilik") },
            backgroundColor = Color(0xFF2C3E50),
            contentColor = Color.White,
            actions = {
                IconButton(onClick = { onClick() }) {
                    Icon(
                        imageVector = PersonAdd,
                        contentDescription = "More options",
                        tint = Color.White
                    )
                }
            }
        )

        DataFeature(
            searchText = searchText,
            onValueChange = { searchText = it },
            onClickDelete = {
                val selectedIds = filteredRows
                    .filterIndexed { index, _ -> rowCheckStates.getOrElse(index) { false } }
                    .map { it.id }
                coroutineScope.launch {
                    deleteData(selectedIds)
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
                    val data = fetchDataPemilik()
                    rows = data
                    isLoading = false
                    searchText = ""
                }
            } else {
                if (filteredRows.isNotEmpty()) {
                    TableComponent(
                        headers = listOf("No", "Tenggat Tahun", "Tenggat Bulan", "Jenis", "Keterangan", "Detail"),
                        rows = filteredRows.mapIndexed { index, item ->
                            listOf(
                                (index + 1).toString(),
                                item.tenggat_thn.toString(),
                                item.tenggat_bln.toString(),
                                item.jenis,
                                item.keterangan,
                            )
                        },
                        rowCheckStates = rowCheckStates,
                        onClick = { index ->
                            val selectedId = filteredRows[index].id
                            navigateToDetail(selectedId)
                        }
                    )
                } else {
                    Text("Data tidak ditemukan", color = Color.Gray)
                }
            }
        }
    }
}