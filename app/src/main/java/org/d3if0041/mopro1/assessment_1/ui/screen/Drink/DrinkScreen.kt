package org.d3if0041.mopro1.assessment_1.ui.screen.Drink

import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import org.d3if0041.mopro1.assessment_1.R
import org.d3if0041.mopro1.assessment_1.database.MinumanDb
import org.d3if0041.mopro1.assessment_1.ui.theme.Assessment_1Theme
import org.d3if0041.mopro1.assessment_1.util.MinumanModelFactory

const val KEY_NAMA_MINUMAN= "nama"
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrinkScreen(navController: NavHostController, id: String? = null) {
    val context = LocalContext.current
    val db = MinumanDb.getInstance(context)
    val factory = MinumanModelFactory(db.dao)
    val viewModel: DrinkViewModel = viewModel(factory = factory)
    var nama by remember { mutableStateOf("") }
    var stock by remember { mutableStateOf("") }
    var harga by remember { mutableStateOf("") }
    var berat by remember { mutableStateOf("") }
    var panjang by remember { mutableStateOf("") }
    var lebar by remember { mutableStateOf("") }
    var tinggi by remember { mutableStateOf("") }
    var deskripsi by remember { mutableStateOf("") }
    var gambarResId by remember { mutableStateOf("") }

    var showDialog by remember { mutableStateOf(false)}

    var selectedImageUri by rememberSaveable { mutableStateOf<Uri?>(null) }
    val getImage =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let { selectedImageUri = it }
        }
    LaunchedEffect(true) {
        if (id == null) return@LaunchedEffect
        val data = viewModel.getMinuman(id) ?: return@LaunchedEffect
        if (data != null) {
            nama = data.nama
        }
        if (data != null) {
            stock = data.stock
        }
        if (data != null) {
            harga = data.harga
        }
        if (data != null) {
            berat = data.berat
        }
        if (data != null) {
            panjang = data.panjang
        }
        if (data != null) {
            lebar = data.lebar
        }
        if (data != null) {
            tinggi = data.tinggi
        }
        if (data != null) {
            deskripsi = data.deskripsi
        }
        if (data != null) {
            selectedImageUri = data?.gambarResId?.toUri()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.kembali),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                title = {
                    if (id == null)
                        Text(text = stringResource(id = R.string.tambah_barang))
                    else
                        Text(text = stringResource(id = R.string.ubah_barang))

                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                actions = {
                    IconButton(onClick = {
                        if (nama == "") {
                            Toast.makeText(context, R.string.invalid_nama, Toast.LENGTH_LONG).show()
                            return@IconButton
                        }
                        if (stock == "") {
                            Toast.makeText(context, R.string.invalid_nim, Toast.LENGTH_LONG).show()
                            return@IconButton
                        }
                        if (harga == "") {
                            Toast.makeText(context, R.string.invalid_kelas, Toast.LENGTH_LONG)
                                .show()
                            return@IconButton
                        }
                        if (berat == "") {
                            Toast.makeText(context, R.string.invalid_kelas1, Toast.LENGTH_LONG)
                                .show()
                            return@IconButton
                        }
                        if (panjang == "") {
                            Toast.makeText(context, R.string.invalid_kelas3, Toast.LENGTH_LONG)
                                .show()
                            return@IconButton
                        }
                        if (lebar == "") {
                            Toast.makeText(context, R.string.invalid_kelas4, Toast.LENGTH_LONG)
                                .show()
                            return@IconButton
                        }
                        if (tinggi == "") {
                            Toast.makeText(context, R.string.invalid_kelas5, Toast.LENGTH_LONG)
                                .show()
                            return@IconButton
                        }
                        if (deskripsi == "") {
                            Toast.makeText(context, R.string.invalid_kelas6, Toast.LENGTH_LONG)
                                .show()
                            return@IconButton
                        }
                        if (selectedImageUri == null) {
                            Toast.makeText(context, R.string.invalid_kelas6, Toast.LENGTH_LONG)
                                .show()
                            return@IconButton
                        } else {
                            try {
                                val gambarUri = selectedImageUri.toString()
                                if (id == null) {
                                    viewModel.insert(
                                        nama,
                                        stock,
                                        harga,
                                        berat,
                                        panjang,
                                        lebar,
                                        tinggi,
                                        deskripsi,
                                        gambarUri
                                    )
                                } else {
                                    viewModel.update(
                                        nama,
                                        stock,
                                        harga,
                                        berat,
                                        panjang,
                                        lebar,
                                        tinggi,
                                        deskripsi,
                                        gambarUri
                                    )
                                }
                                navController.popBackStack()
                            } catch (e: Exception) {
                                Log.e("DrinkScreen", "Error updating or inserting", e)
                                Toast.makeText(
                                    context,
                                    "Error: ${e.localizedMessage}",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Check,
                            contentDescription = stringResource(R.string.simpan),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    IconButton(onClick = {
                        // Share data logic here
                        val message = "Nama: $nama\nStok: $stock\nHarga: $harga\nBerat: $berat\nPanjang: $panjang\nLebar: $lebar\nTinggi: $tinggi"
                        shareDataMinuman(context, message, selectedImageUri)
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Share,
                            contentDescription = stringResource(R.string.bagikan),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    if (id != null) {
                        DeleteActionMinuman { showDialog = true }
                        MinumanAlertDialog(
                            openDialog = showDialog,
                            onDismissRequest = { showDialog = false }) {
                            showDialog = false
                            viewModel.delete(id)
                            navController.popBackStack()
                        }
                    }
                }
            )
        }
    ) { padding ->
        FormMinuman(
            title = nama,
            onTitleChange = { nama = it },
            stock = stock,
            onStockChange = { stock = it },
            harga = harga,
            onHargaChange = { harga = it },
            berat = berat,
            onBeratChange = { berat = it },
            gambarResId = gambarResId,
            ongambarResIdChange = { gambarResId = it },
            panjang = panjang,
            onPanjangChange = { panjang = it },
            lebar = lebar,
            onLebarChange = { lebar = it },
            tinggi = tinggi,
            onTinggiChange = { tinggi = it },
            deskripsi = deskripsi,
            onDeskripsiChange = { deskripsi = it },
            selectedImageUri = selectedImageUri,
            onImageSelected = { getImage.launch("image/*") },
            modifier = Modifier.padding(padding)
        )
    }
}


@Composable
fun DeleteActionMinuman(delete: () -> Unit) {
    var expanded by remember { mutableStateOf(false)}
    IconButton(onClick = {expanded = true}) {
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = stringResource(R.string.lainya),
            tint = MaterialTheme.colorScheme.primary
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = {
                    Text(text = stringResource(id = R.string.tombol_hapus))
                },
                onClick = {
                    expanded = false
                    delete()
                })
        }
    }
}

@Composable
fun FormMinuman(
    title: String,
    onTitleChange: (String) -> Unit,
    stock: String,
    onStockChange: (String) -> Unit,
    harga: String,
    onHargaChange: (String) -> Unit,
    berat: String,
    onBeratChange: (String) -> Unit,
    gambarResId: String,
    ongambarResIdChange: (String) -> Unit,
    panjang: String,
    onPanjangChange: (String) -> Unit,
    lebar: String,
    onLebarChange: (String) -> Unit,
    tinggi: String,
    onTinggiChange: (String) -> Unit,
    deskripsi: String,
    onDeskripsiChange: (String) -> Unit,
    selectedImageUri: Uri?, onImageSelected: () -> Unit,
    modifier: Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            OutlinedTextField(
                value = title,
                onValueChange = { onTitleChange(it) },
                label = { Text(text = stringResource(R.string.nama)) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = stock,
                onValueChange = { onStockChange(it) },
                label = { Text(text = stringResource(R.string.stok)) },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                ),
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = harga,
                onValueChange = { onHargaChange(it) },
                label = { Text(text = stringResource(R.string.harga)) },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                ),
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = berat,
                onValueChange = { onBeratChange(it) },
                label = { Text(text = stringResource(R.string.berat)) },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                ),
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = panjang,
                onValueChange = { onPanjangChange(it) },
                label = { Text(text = stringResource(R.string.panjang)) },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                ),
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = lebar,
                onValueChange = { onLebarChange(it) },
                label = { Text(text = stringResource(R.string.lebar)) },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                ),
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = tinggi,
                onValueChange = { onTinggiChange(it) },
                label = { Text(text = stringResource(R.string.tinggi)) },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                ),
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = deskripsi,
                onValueChange = { onDeskripsiChange(it) },
                label = { Text(text = stringResource(R.string.deskripsi)) },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                ),
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedButton(
                onClick = onImageSelected,
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            ) {
                Text("Pilih Gambar")
            }
            selectedImageUri?.let { uri ->
                Image(
                    painter = rememberImagePainter(uri),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
            }
        }
    }
}

private fun shareDataMinuman(context: Context, message: String, imageUri: Uri?) {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
        imageUri?.let {
            putExtra(Intent.EXTRA_STREAM, it)
            clipData = ClipData.newUri(context.contentResolver, "Image", it)
            flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        }
    }
    if (shareIntent.resolveActivity(context.packageManager) != null){
        context.startActivity(Intent.createChooser(shareIntent, "Share via"))
    }
}


@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DrinkScreenPreview() {
    Assessment_1Theme {
        DrinkScreen(rememberNavController())
    }
}