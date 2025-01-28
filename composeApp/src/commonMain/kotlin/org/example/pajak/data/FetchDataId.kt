package org.example.pajak.data

import kotlinx.serialization.Serializable

@Serializable
data class PemilikData(
    val id: Int,
    val nama: String,
    val kelamin: String,
    val plat: String,
    val alamat: String,
    val jenis: String,
    val tenggat_thn: Int,
    val tenggat_bln: Int,
    val keterangan: String
)

@Serializable
data class PemilikResponse(
    val data: PemilikData,
    val status: String
)
