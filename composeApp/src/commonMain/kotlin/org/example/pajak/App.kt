package org.example.pajak

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.github.sarxos.webcam.Webcam

@Composable
fun App() {
    val navigationManager = remember { NavigationManager() }
    Navigation(navigationManager)
}

fun listWebcams() {
    val webcams = Webcam.getWebcams()
    if (webcams.isEmpty()) {
        println("No webcams detected.")
    } else {
        println("Available webcams:")
        webcams.forEachIndexed { index, webcam ->
            println("${index + 1}. ${webcam.name}")
        }
    }
}

