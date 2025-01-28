package org.example.pajak.ui.components.sidebar

import androidx.compose.ui.graphics.vector.ImageVector

data class MenuItem(
    val text: String,
    val icon: ImageVector,
    val onClick: () -> Unit,
    val isSelected: Boolean,
    val onHoverChange: (Boolean) -> Unit = {}
)
