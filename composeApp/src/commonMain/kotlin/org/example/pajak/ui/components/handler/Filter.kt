package org.example.pajak.ui.components.handler

import androidx.compose.runtime.Composable
import java.text.SimpleDateFormat

@Composable
fun <T> getFilteredRows(
    rows: List<T>,
    startDate: Long? = null,
    endDate: Long? = null,
    searchText: String,
    dateFormatter: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd"),
    dateExtractor: ((T) -> String)? = null, // Bisa null jika tanggal tidak digunakan
    filterLogic: (T, String) -> Boolean // Fungsi logika filter tambahan
): List<T> {
    return rows.filter { row ->
        // Filter tanggal, jika `dateExtractor` null, lewati logika ini
        val inDateRange = if (dateExtractor != null) {
            val rowDate = dateFormatter.parse(dateExtractor(row))?.time ?: 0L
            startDate == null || endDate == null || (rowDate in startDate..endDate)
        } else {
            true
        }

        // Filter berdasarkan search text
        val inSearch = searchText.isEmpty() || filterLogic(row, searchText)

        // Gabungkan kedua filter
        inDateRange && inSearch
    }
}


