package org.example.pajak

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            App()
            App()
        }
    }
}


//@Preview
//@Composable
//fun AppAndroidPreview() {
//    App()
//}