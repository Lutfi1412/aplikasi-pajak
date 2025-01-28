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
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.example.pajak.data.PemilikData
import org.example.pajak.services.UpdateData
import org.example.pajak.services.deleteData

@Composable
fun DetailContent(data: PemilikData, tenggatThn: MutableState<String>, tenggatBln:  MutableState<String>, onClick: () -> Unit){

    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()) // Aktifkan scroll
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
                painter = painterResource((if (data.kelamin == "Perempuan"){"drawable/perempuan.jpeg"} else {
                    "drawable/laki.jpeg"
                }).toString()), // Ganti dengan ID gambar Anda
                contentDescription = "Profile Image",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape) // Membuat gambar menjadi bulat
                    .border(1.dp, Color.Black, CircleShape) // Border di sekitar gambar
            )
        }
        Spacer(modifier = Modifier.height(30.dp))

        Column (
            horizontalAlignment = Alignment.Start
        ) {

            Text("Nama", modifier = Modifier.padding(horizontal = 20.dp).padding(vertical = 5.dp))
            OutlinedTextField(
                value = data.nama,
                onValueChange = {},
                readOnly = true,
                enabled = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text("Kelamin", modifier = Modifier.padding(horizontal = 20.dp).padding(vertical = 5.dp))
            OutlinedTextField(
                value = data.kelamin,
                onValueChange = {},
                readOnly = true,
                enabled = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text("Plat", modifier = Modifier.padding(horizontal = 20.dp).padding(vertical = 5.dp))
            OutlinedTextField(
                value = data.plat,
                onValueChange = {},
                readOnly = true,
                enabled = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text("Jenis", modifier = Modifier.padding(horizontal = 20.dp).padding(vertical = 5.dp))
            OutlinedTextField(
                value = data.jenis,
                onValueChange = {},
                readOnly = true,
                enabled = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text("Alamat", modifier = Modifier.padding(horizontal = 20.dp).padding(vertical = 5.dp))
            OutlinedTextField(
                value = data.alamat,
                onValueChange = {},
                readOnly = true,
                enabled = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text("Tenggat Tahun", modifier = Modifier.padding(horizontal = 20.dp).padding(vertical = 5.dp))
            OutlinedTextField(
                value = tenggatThn.value,
                onValueChange = {tenggatThn.value = it},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text("Tenggat Bulan", modifier = Modifier.padding(horizontal = 20.dp).padding(vertical = 5.dp))
            OutlinedTextField(
                value = tenggatBln.value,
                onValueChange = {tenggatBln.value = it},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text("Keterangan", modifier = Modifier.padding(horizontal = 20.dp).padding(vertical = 5.dp))
            OutlinedTextField(
                value = data.keterangan,
                onValueChange = {},
                readOnly = true,
                enabled = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                coroutineScope.launch {
                    UpdateData(data.id, tenggatThn.value.toInt(), tenggatBln.value.toInt())
                    onClick()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .size(50.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF2C3E50))
        ) {
            Text(text = "Update", color = Color.White)
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}