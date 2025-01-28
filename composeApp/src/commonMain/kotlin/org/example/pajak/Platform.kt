package org.example.pajak

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform