package org.example.pajak.ui.components.sidebar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerMoveFilter
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SidebarItem(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit,
    isHovered: MutableState<Boolean>,
    isSelected: Boolean,
    onHoverChange: (Boolean) -> Unit
) {
    val backgroundColor = getBackgroundColor(isSelected, isHovered.value)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .pointerMoveFilter(
                onEnter = { onHoverChange(true); false },
                onExit = { onHoverChange(false); false }
            )
            .clickable { onClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SidebarIcon(icon)
        Spacer(modifier = Modifier.width(5.dp))
        SidebarText(text)
    }
}

