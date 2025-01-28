package org.example.pajak.ui.components.sidebar

import androidx.compose.ui.graphics.Color

fun getBackgroundColor(isSelected: Boolean, isHovered: Boolean): Color {
    return when {
        isSelected -> Color(0xFF696B6E)
        isHovered -> Color(0xFF696B6E)
        else -> Color(0xFF91959C)
    }
}