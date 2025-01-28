package org.example.pajak.ui.components.camera

import com.github.sarxos.webcam.Webcam
import com.github.sarxos.webcam.WebcamPanel
import java.awt.Dimension
import java.awt.Toolkit

fun createWebcamPanel(webcam: Webcam): WebcamPanel {
    val webcamPanel = WebcamPanel(webcam)
    webcamPanel.isMirrored = true
    webcamPanel.preferredSize = Dimension(Toolkit.getDefaultToolkit().screenSize.width, Toolkit.getDefaultToolkit().screenSize.height)
    return webcamPanel
}