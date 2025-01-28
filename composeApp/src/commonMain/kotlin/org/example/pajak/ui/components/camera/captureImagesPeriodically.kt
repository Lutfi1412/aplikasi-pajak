package org.example.pajak.ui.components.camera

import com.github.sarxos.webcam.Webcam
import kotlinx.coroutines.delay
import org.example.pajak.services.AddLaporan
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO

suspend fun captureImagesPeriodically(webcam: Webcam) {
    val saveDirectory = File("C:\\Users\\sawal fitah\\Pictures\\Camera Roll")
    if (!saveDirectory.exists()) {
        saveDirectory.mkdirs()
    }

    var imageCount = 1
    while (true) {
        delay(15000)
        try {
            val image = webcam.image
            val file = File(saveDirectory, "capture_$imageCount.jpg")
            ImageIO.write(image, "JPG", file)
            println("Image saved: ${file.absolutePath}")
            AddLaporan(file)
            imageCount++
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: Exception) {
            println("Error capturing or sending image: ${e.message}")
        }
    }
}