package org.example.pajak.ui.components.sidebar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun SidebarHeader(isSidebarOpen: Boolean, toggleSidebar: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isSidebarOpen) {
            Image(
                painter = painterResource("drawable/polri.png"),
                contentDescription = "Logo",
                modifier = Modifier.size(60.dp).weight(1f),
                contentScale = ContentScale.Fit
            )
        }
        IconButton(onClick = { toggleSidebar(!isSidebarOpen) }) {
            Icon(
                imageVector = if (isSidebarOpen) Icons.Filled.Close else Icons.Filled.Menu,
                contentDescription = if (isSidebarOpen) "Close Sidebar" else "Open Sidebar",
                tint = Color.White
            )
        }
    }
}