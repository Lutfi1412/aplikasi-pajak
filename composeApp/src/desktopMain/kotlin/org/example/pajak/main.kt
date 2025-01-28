package org.example.pajak

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Kotlin Multiplatform Navigation") {
        App()
    }
}

@Composable
@Preview
fun MyComposablePreview() {
    App()
}
