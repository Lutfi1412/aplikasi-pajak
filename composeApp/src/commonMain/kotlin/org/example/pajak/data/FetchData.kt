package org.example.pajak.data

import kotlinx.serialization.Serializable

@Serializable
data class PemilikItem(
    val id: Int,
    val jenis: String,
    val tenggat_thn: Int,
    val tenggat_bln: Int,
    val keterangan: String
)
@Serializable
data class ApiResponsePemilik(
    val data: List<PemilikItem>
)