package org.example.pajak.ui.components.Date

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.pajak.ui.components.icon.CalendarDays

@Composable
fun DatePickerButton(onClick: () -> Unit, modifier: Modifier, selectedDate: String) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = selectedDate,
            onValueChange = {},
            modifier = Modifier.padding(end = 8.dp),
            textStyle = MaterialTheme.typography.body1,
            readOnly = true,
            label = {
                if (selectedDate != "Pilih Tanggal :") {
                    Text("Jangka Tanggal :")
                }
            },
            leadingIcon = {
                IconButton(onClick = { onClick() }) {
                    Icon(
                        imageVector = CalendarDays,
                        contentDescription = "Pilih Tanggal"
                    )
                }
            }
        )
    }
}
