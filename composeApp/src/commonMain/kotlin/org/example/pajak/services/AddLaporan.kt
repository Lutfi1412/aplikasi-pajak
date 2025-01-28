package org.example.pajak.services

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import java.io.File

suspend fun AddLaporan(imageFile: File) {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }

    try {
        val response: HttpResponse = client.post("https://api-pajak-detect.vercel.app/detect") {
            contentType(ContentType.MultiPart.FormData)
            setBody(MultiPartFormDataContent(formData {
                append("image", imageFile.readBytes(), Headers.build {
                    append(HttpHeaders.ContentDisposition, "form-data; name=\"image\"; filename=\"${imageFile.name}\"")
                })
            }))
        }

        if (response.status.isSuccess()) {
            println("Data berhasil dikirim: ${response.bodyAsText()}")
        } else {
            println("Gagal mengirim data: ${response.status}")
        }
    } catch (e: Exception) {
        println("Error saat mengirim data: ${e.message}")
    } finally {
        client.close()
    }
}