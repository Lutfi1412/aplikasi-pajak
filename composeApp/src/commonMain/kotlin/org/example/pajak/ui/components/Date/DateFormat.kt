package org.example.pajak.ui.components.Date

import androidx.compose.runtime.Composable
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun getFormattedDateRange(startDate: Long?, endDate: Long?, dateFormatter: SimpleDateFormat): String {
    return when {
        startDate != null && endDate != null ->
            "${dateFormatter.format(Date(startDate))} - ${dateFormatter.format(Date(endDate))}"
        else -> "Pilih Tanggal :"
    }
}