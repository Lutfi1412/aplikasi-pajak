package org.example.pajak.data

import kotlinx.serialization.Serializable

@Serializable
data class LaporanItem(
    val id: Int,
    val tanggal: String,
    val jam: String,
    val jenis: String,
    val plat: String,
    val keterangan: String
)

@Serializable
data class ApiResponse(
    val data: List<LaporanItem>
)
