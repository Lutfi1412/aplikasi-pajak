package org.example.pajak.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.example.pajak.data.PemilikData
import org.example.pajak.services.GetById
import org.example.pajak.ui.layout.DetailContent

@Composable
fun DetailPemilik(id: Int, navigateBack: () -> Unit) {


    val pemilikData = remember { mutableStateOf<PemilikData?>(null) }
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(id) {
        coroutineScope.launch {
            val data = GetById(id)
            pemilikData.value = data
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF2C3E50))
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navigateBack() }, modifier = Modifier.padding(end = 10.dp)) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
            Text(text = "Detail Pemilik", color = Color.White, fontSize = 20.sp)
            Spacer(modifier = Modifier.weight(1f))
        }
        if (pemilikData.value == null) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }else{
            val data = pemilikData.value!!
            val tenggatThn = remember { mutableStateOf(data.tenggat_thn.toString()) }
            val tenggatBln = remember { mutableStateOf(data.tenggat_bln.toString()) }
            DetailContent(data,tenggatThn,tenggatBln, navigateBack)
        }
    }
}






