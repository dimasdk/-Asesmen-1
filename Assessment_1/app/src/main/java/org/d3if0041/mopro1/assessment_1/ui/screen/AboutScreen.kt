package org.d3if0041.mopro1.assessment_1.ui.screen

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if0041.mopro1.assessment_1.R
import org.d3if0041.mopro1.assessment_1.ui.theme.Assessment_1Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(navController: NavHostController) {
    val context = LocalContext.current
    var judul by remember { mutableStateOf("") }
    var stok by remember { mutableStateOf("") }
    var harga by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {navController.popBackStack()}) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack ,
                            contentDescription = stringResource(R.string.kembali),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                title = {
                    Text(text = stringResource(id = R.string.tambah_barang))
                },
                actions = {
                    IconButton(
                        onClick = {
                            val message = "Judul: $judul\nStok: $stok\nHarga: $harga"
                            shareData(context, message)
                        }
                    ) { Icon(
                        imageVector = Icons.Filled.Share,
                        contentDescription = stringResource(R.string.bagikan),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        }
    ) { padding ->
        ShareComponent(
            modifier = Modifier.padding(padding),
            onTambahClick = { inputJudul, inputStok, inputHarga ->
                judul = inputJudul
                stok = inputStok
                harga = inputHarga
                if (judul.isNotEmpty() && stok.isNotEmpty() && harga.isNotEmpty()) {
                    val message = "Berhasil!\n"
//                            "Judul: $judul\n" +
//                            "Stok: $stok\n" +
//                            "Harga: $harga"
                    Toast.makeText(context, "Berhasil", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Tidak Boleh Kosong", Toast.LENGTH_SHORT).show()
                }
            }
        )
    }
}

private fun shareData(context: Context, message: String) {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
    }
    if (shareIntent.resolveActivity(context.packageManager) != null){
        context.startActivity(shareIntent)
    }
}
@Composable
fun ShareComponent(
    modifier: Modifier = Modifier,
    onTambahClick: (judul: String, stok: String, harga: String) -> Unit
) {
    var judul by remember { mutableStateOf("") }
    var stok by remember { mutableStateOf("") }
    var harga by remember { mutableStateOf("") }
    var hasilInput by remember { mutableStateOf("") }
    var isJudulEmpty by remember { mutableStateOf(false) }
    var isStokEmpty by remember { mutableStateOf(false) }
    var isHargaEmpty by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        OutlinedTextField(
            value = judul,
            onValueChange = {
                judul = it
                isJudulEmpty = it.isEmpty() // Update the state based on input
            },
            label = { Text("Nama Barang") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        if (isJudulEmpty && judul.isEmpty()) { // Hide the text when input is not empty
            Text(
                text = "Tidak Boleh Kosong",
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        OutlinedTextField(
            value = stok,
            onValueChange = {
                stok = it
                isStokEmpty = it.isEmpty() // Update the state based on input
            },
            label = { Text("Stok") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        if (isStokEmpty && stok.isEmpty()) { // Hide the text when input is not empty
            Text(
                text = "Tidak Boleh Kosong",
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        OutlinedTextField(
            value = harga,
            onValueChange = {
                harga = it
                isHargaEmpty = it.isEmpty() // Update the state based on input
            },
            label = { Text("Harga") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        if (isHargaEmpty && harga.isEmpty()) { // Hide the text when input is not empty
            Text(
                text = "Tidak Boleh Kosong",
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        Button(
            onClick = {
                if (judul.isEmpty()) {
                    isJudulEmpty = true
                }
                if (stok.isEmpty()) {
                    isStokEmpty = true
                }
                if (harga.isEmpty()) {
                    isHargaEmpty = true
                }
                if (judul.isNotEmpty() && stok.isNotEmpty() && harga.isNotEmpty()) {
                    hasilInput = "Judul: $judul\nStok: $stok\nHarga: $harga"
                    onTambahClick(judul, stok, harga)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(text = "Tambah")
        }
        if (hasilInput.isNotEmpty()) {
            Text(
                text = hasilInput,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun AboutScreenPreview() {
    Assessment_1Theme {
        AboutScreen(rememberNavController())
    }
}