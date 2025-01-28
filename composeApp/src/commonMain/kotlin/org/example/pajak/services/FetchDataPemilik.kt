package org.example.pajak.services

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.example.pajak.data.ApiResponse
import org.example.pajak.data.ApiResponsePemilik
import org.example.pajak.data.LaporanItem
import org.example.pajak.data.PemilikItem

suspend fun fetchDataPemilik(): List<PemilikItem> {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }

    try {
        // Mengambil data dari API
        val response: ApiResponsePemilik = client.get("https://api-pajak.vercel.app/api/getdata").body()
        return response.data
    } catch (e: Exception) {
        // Menangani error
        println("Error fetching data: ${e.message}")
        return emptyList()
    }
}