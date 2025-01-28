package org.example.pajak.ui.components.table

fun getSortedRows(
    rows: List<List<String>>,
    sortColumn: Int,
    sortAscending: Boolean
): List<List<String>> {
    return if (sortColumn >= 0) {
        rows.sortedWith { row1, row2 ->
            val value1 = row1.getOrElse(sortColumn) { "" }
            val value2 = row2.getOrElse(sortColumn) { "" }
            when {
                sortAscending -> value1.compareTo(value2)
                else -> value2.compareTo(value1)
            }
        }
    } else {
        rows // Jika sortColumn -1, kembalikan data tanpa disortir
    }
}

