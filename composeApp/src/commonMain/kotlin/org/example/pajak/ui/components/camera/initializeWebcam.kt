package org.example.pajak.ui.components.camera

import com.github.sarxos.webcam.Webcam

fun initializeWebcam(): Webcam {
    val webcam = Webcam.getWebcams().find { it.name.contains("FHD Camera 1") } ?: Webcam.getDefault()
    webcam?.open()
    return webcam ?: throw IllegalStateException("No webcam detected")
}