@file:Suppress("DEPRECATION")

package org.example.pajak.ui.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.pajak.Screen
import org.example.pajak.ui.components.icon.Linked_camera
import org.example.pajak.ui.components.icon.ListCheck
import org.example.pajak.ui.components.sidebar.MenuItem
import org.example.pajak.ui.components.sidebar.SidebarHeader
import org.example.pajak.ui.components.sidebar.SidebarItem

@Composable
fun Sidebar(
    isSidebarOpen: Boolean,
    toggleSidebar: (Boolean) -> Unit,
    navigateCamera: () -> Unit,
    navigateLaporan: () -> Unit,
    navigateDataPemilik: () -> Unit,
    currentScreen: Screen
) {
    val sidebarWidth = if (isSidebarOpen) 200.dp else 60.dp
    val menuItems = listOf(
        MenuItem("Camera", Linked_camera, navigateCamera, currentScreen is Screen.Camera),
        MenuItem("Laporan", ListCheck, navigateLaporan, currentScreen is Screen.Laporan),
        MenuItem("Data Pemilik", Icons.Filled.Person, navigateDataPemilik, currentScreen is Screen.DataPemilik)
    )

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(sidebarWidth)
            .background(Color(0xFF91959C))
            .padding(top = 20.dp),
        verticalArrangement = Arrangement.Top,
    ) {
        SidebarHeader(isSidebarOpen, toggleSidebar)

        Spacer(modifier = Modifier.height(20.dp))

        menuItems.forEach { item ->
            SidebarItem(
                text = if (isSidebarOpen) item.text else "",
                isHovered = remember { mutableStateOf(false) },
                onHoverChange = { isHovered -> item.onHoverChange(isHovered) },
                icon = item.icon,
                onClick = item.onClick,
                isSelected = item.isSelected
            )
        }
    }
}



