package org.example.pajak.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.sarxos.webcam.Webcam
import kotlinx.coroutines.launch
import org.example.pajak.ui.components.camera.captureImagesPeriodically
import org.example.pajak.ui.components.camera.createWebcamPanel
import org.example.pajak.ui.components.camera.initializeWebcam
import org.example.pajak.ui.layout.CameraUI

class CameraViewModel : ViewModel() {
    private val webcam = initializeWebcam()

    init {
        startCapture()
    }
    private fun startCapture() {
        viewModelScope.launch {
            captureImagesPeriodically(webcam)
        }
    }

    fun getWebcam(): Webcam = webcam
}

@Composable
fun Camera(viewModel: CameraViewModel = CameraViewModel()) {
    val webcam = remember { viewModel.getWebcam() }
    val webcamPanel = remember { createWebcamPanel(webcam) }

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("Camera") },
            backgroundColor = Color(0xFF2C3E50),
            contentColor = Color.White
        )
        CameraUI(webcamPanel)
    }
}

