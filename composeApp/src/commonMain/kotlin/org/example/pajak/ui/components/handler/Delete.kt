package org.example.pajak.ui.components.handler

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@Composable
fun TrashIconButton(onClick: () -> Unit) {
    IconButton(
        onClick = {onClick()}
    ) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = "Hapus",
            tint = Color.Red
        )
    }
}

