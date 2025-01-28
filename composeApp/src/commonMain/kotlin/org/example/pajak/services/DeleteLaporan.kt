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
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

suspend fun deleteLaporan(ids: List<Int>) {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }

    try {
        val response = client.post("https://api-pajak.vercel.app/api/deletelaporan") {
            contentType(ContentType.Application.Json)
            setBody(JsonObject(mapOf("id" to JsonArray(ids.map { JsonPrimitive(it) }))))
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