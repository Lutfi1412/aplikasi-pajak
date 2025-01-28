package org.example.pajak.ui.components.sidebar

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SidebarText(text: String) {
    if (text.isNotEmpty()) {
        Text(
            text = text,
            modifier = Modifier.padding(start = 5.dp),
            color = Color.White
        )
    }
}