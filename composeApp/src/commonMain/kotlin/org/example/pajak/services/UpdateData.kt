package org.example.pajak.services

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import org.example.pajak.data.PemilikData
import org.example.pajak.data.PemilikResponse

suspend fun UpdateData(id: Int, tahun: Int, bulan: Int) {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }

    try {
        val response = client.post("https://api-pajak.vercel.app/api/updatedata") {
            contentType(ContentType.Application.Json)
            setBody(buildJsonObject {
                put("id", id)
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