package org.example.pajak.ui.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.example.pajak.data.PemilikData
import org.example.pajak.services.AddData
import org.example.pajak.services.UpdateData

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun TambahDataContent(onClick: () -> Unit) {

    val coroutineScope = rememberCoroutineScope()

    var nama by remember { mutableStateOf("") }
    var kelamin by remember { mutableStateOf("") }
    var plat by remember { mutableStateOf("") }
    var jenis by remember { mutableStateOf("") }
    var alamat by remember { mutableStateOf("") }
    var tenggatBln by remember { mutableStateOf("") }
    var tenggatThn by remember { mutableStateOf("") }

    val kelaminOptions = listOf("Laki-Laki", "Perempuan")
    var kelaminExpanded by remember { mutableStateOf(false) }

    val jenisOptions = listOf("Motor", "Mobil")
    var jenisExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource("drawable/polri.png"),
                contentDescription = "Profile Image",
                modifier = Modifier.size(100.dp)
            )
        }
        Spacer(modifier = Modifier.height(30.dp))

        Column(horizontalAlignment = Alignment.Start) {

            Text("Nama", modifier = Modifier.padding(horizontal = 20.dp, vertical = 5.dp))
            OutlinedTextField(
                value = nama,
                onValueChange = { nama = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
            )
            Spacer(modifier = Modifier.height(15.dp))

            Text("Kelamin", modifier = Modifier.padding(horizontal = 20.dp, vertical = 5.dp))
            ExposedDropdownMenuBox(
                expanded = kelaminExpanded,
                onExpandedChange = { kelaminExpanded = it }
            ) {
                OutlinedTextField(
                    value = kelamin,
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor()
                        .padding(horizontal = 20.dp),
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = kelaminExpanded)
                    },
                    placeholder = { Text("Pilih Kelamin") }
                )
                ExposedDropdownMenu(
                    expanded = kelaminExpanded,
                    onDismissRequest = { kelaminExpanded = false },
                    containerColor = Color.White
                ) {
                    kelaminOptions.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = {
                                kelamin = option
                                kelaminExpanded = false
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(15.dp))

            Text("Plat", modifier = Modifier.padding(horizontal = 20.dp, vertical = 5.dp))
            OutlinedTextField(
                value = plat,
                onValueChange = { plat = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
            )
            Spacer(modifier = Modifier.height(15.dp))

            Text("Jenis", modifier = Modifier.padding(horizontal = 20.dp, vertical = 5.dp))
            ExposedDropdownMenuBox(
                expanded = jenisExpanded,
                onExpandedChange = { jenisExpanded = it }
            ) {
                OutlinedTextField(
                    value = jenis,
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor()
                        .padding(horizontal = 20.dp),
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = jenisExpanded)
                    },
                    placeholder = { Text("Pilih Jenis Kendaraan") }
                )
                ExposedDropdownMenu(
                    expanded = jenisExpanded,
                    onDismissRequest = { jenisExpanded = false },
                    containerColor = Color.White
                ) {
                    jenisOptions.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = {
                                jenis = option
                                jenisExpanded = false
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(15.dp))

            Text("Alamat", modifier = Modifier.padding(horizontal = 20.dp, vertical = 5.dp))
            OutlinedTextField(
                value = alamat,
                onValueChange = { alamat = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text("Tenggat Tahun", modifier = Modifier.padding(horizontal = 20.dp, vertical = 5.dp))
            OutlinedTextField(
                value = tenggatThn,
                onValueChange = { tenggatThn = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text("Tenggat Bulan", modifier = Modifier.padding(horizontal = 20.dp, vertical = 5.dp))
            OutlinedTextField(
                value = tenggatBln,
                onValueChange = { tenggatBln = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
            )
            Spacer(modifier = Modifier.height(15.dp))

            Button(
                onClick = {
                    coroutineScope.launch {
                        AddData(nama, kelamin, alamat, jenis, plat, tenggatThn.toInt(), tenggatBln.toInt())
                        onClick()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .size(50.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF2C3E50))
            ) {
                Text(text = "Tambah Data", color = Color.White)
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
