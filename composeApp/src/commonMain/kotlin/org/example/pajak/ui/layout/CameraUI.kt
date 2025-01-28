package org.example.pajak.ui.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.SwingPanel
import com.github.sarxos.webcam.WebcamPanel
import javax.swing.JPanel

@Composable
fun CameraUI(webcamPanel: WebcamPanel) {
    Box(modifier = Modifier.fillMaxSize()) {
        SwingPanel(
            factory = {
                val panel = JPanel()
                panel.add(webcamPanel) // Menambahkan WebcamPanel ke JPanel
                panel
            },
            modifier = Modifier.fillMaxSize() // Mengatur ukuran panel untuk menyesuaikan layar
        )
    }
}
