package org.example.pajak.services

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

suspend fun AddData(nama: String, kelamin: String, alamat: String, jenis: String, plat: String, tahun: Int, bulan: Int) {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }

    try {
        val response = client.post("https://api-pajak.vercel.app/api/insertdata") {
            contentType(ContentType.Application.Json)
            setBody(buildJsonObject {
                put("nama", nama)
                put("kelamin", kelamin)
                put("alamat", alamat)
                put("jenis", jenis)
                put("plat", plat)
                put("tenggat_thn", tahun)
                put("tenggat_bln", bulan)
            })
        }
        if (response.status.isSuccess()) {
            println("Data berhasil dihapus")
        } else {
            println("Gagal menghapus data")
        }
    } catch (e: Exception) {
        println("Error saat menghapus data: ${e.message}")
    }
}