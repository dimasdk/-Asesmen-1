package org.d3if0041.mopro1.assessment_1.ui.screen.Drink

import SettingsDataStore
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if0041.mopro1.assessment_1.R
import org.d3if0041.mopro1.assessment_1.database.MinumanDb
import org.d3if0041.mopro1.assessment_1.halaman.Screen
import org.d3if0041.mopro1.assessment_1.model.Mandi
import org.d3if0041.mopro1.assessment_1.model.Minuman
import org.d3if0041.mopro1.assessment_1.ui.theme.Assessment_1Theme
import org.d3if0041.mopro1.assessment_1.util.MinumanModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MinumanScreen(navController: NavHostController) {
    val context = LocalContext.current
    val db = MinumanDb.getInstance(context)
    val factory = MinumanModelFactory(db.dao)
    val viewModel: MinumanViewModel = viewModel(factory = factory)
    var showBottomSheet by remember { mutableStateOf(false) }
    var selectedMinuman by remember { mutableStateOf<Minuman?>(null) }

    val dataStore = SettingsDataStore(LocalContext.current)
    val showList by dataStore.layoutFlow("minuman_layout").collectAsState(initial = true)

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
                title = { Text(text = stringResource(id = R.string.minuman)) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                actions = {
                    IconButton(onClick = {
                        CoroutineScope(Dispatchers.IO).launch {
                            dataStore.saveLayout(!showList, "minuman_layout")
                        }
                    }) {
                        Icon(
                            painter = painterResource(
                                if (showList) R.drawable.baseline_grid_view_24
                                else R.drawable.baseline_view_list_24
                            ),
                            contentDescription = stringResource(
                                if (showList) R.string.list
                                else R.string.grid
                            ),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screen.Drink.route) }
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = stringResource(id = R.string.tambah_barang))
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            val searchText by viewModel.searchText.collectAsState()
            MinumanSearchBar(searchText, viewModel::setSearchText)
            ScreenContent(showList, modifier = Modifier.fillMaxSize(), navController, viewModel) { minuman ->
                selectedMinuman = minuman
                showBottomSheet = true
            }
        }
    }

    if (showBottomSheet && selectedMinuman!= null) {
        BottomSheetScaffold(
            sheetContent = {
                BottomSheetContentMinuman(selectedMinuman!!, onClose = {
                    selectedMinuman = null
                    showBottomSheet = false
                })
            },
            content = { },
            sheetContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Composable
fun BottomSheetContentMinuman(minuman: Minuman, onClose: () -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Informasi Produk",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.weight(1f)  // Takes up all available space pushing the icon to the end
            )
            IconButton(
                onClick = { onClose() }
            ) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Tutup",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        Divider(modifier = Modifier.padding(vertical = 8.dp))

        InfoItemMinuman(label = "Berat", value = "${minuman.berat}")
        Spacer(modifier = Modifier.height(20.dp))

        InfoItemMinuman(label = "Panjang", value = "${minuman.panjang}")
        Spacer(modifier = Modifier.height(20.dp))

        InfoItemMinuman(label = "Lebar", value = "${minuman.lebar}")
        Spacer(modifier = Modifier.height(20.dp))

        InfoItemMinuman(label = "Tinggi", value = "${minuman.tinggi}")
        Spacer(modifier = Modifier.height(20.dp))

        Spacer(modifier = Modifier.height(4.dp))
        Divider(modifier = Modifier.padding(vertical = 10.dp))

        InfoItemMinuman(label = "Deskripsi Produk", value = minuman.deskripsi, isDescription = true)
    }
}

@Composable
fun InfoItemMinuman(label: String, value: String, isDescription: Boolean = false) {
    if (isDescription) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = label,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Normal),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = value,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Normal),
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 1.dp, top = 4.dp)
            )
        }
    } else {
        // Regular items layout
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "$label: ",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Normal),
                fontSize = 16.sp,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = value,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Normal),
                textAlign = TextAlign.Right,
                fontSize = 16.sp,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun InfoMinuman1(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$label: $value",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
        )
    }
}
@Composable
fun ScreenContent(
    showList:Boolean,
    modifier: Modifier,
    navController: NavHostController,
    viewModel: MinumanViewModel,
    onViewDetails: (Minuman) -> Unit
) {
    val data by viewModel.data.collectAsState()
    val context = LocalContext.current
    val db = MinumanDb.getInstance(context)
    val factory = MinumanModelFactory(db.dao)
    val viewModel: MinumanViewModel = viewModel(factory = factory)

    if (data.isEmpty()) {
        MinumanListMessage(modifier)
    } else {
        if (showList) {
            LazyColumn(
                modifier = modifier.fillMaxSize().padding(16.dp),
                contentPadding = PaddingValues(bottom = 84.dp)
            ) {
                items(data) { minuman ->
                    ListItemMinuman(minuman = minuman, onClick = {
                        navController.navigate(Screen.FormUbahMinuman.withNama(minuman.nama))
                    }, onViewDetails = onViewDetails)
                    Divider()
                }
            }
        } else {
            LazyVerticalStaggeredGrid(
                modifier = modifier.fillMaxSize(),
                columns = StaggeredGridCells.Fixed(2),
                verticalItemSpacing = 8.dp,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(8.dp, 8.dp, 84.dp)
            ) {
                items(data) { minuman ->
                    GridItemMinuman(minuman = minuman, onClick = {
                        navController.navigate(Screen.FormUbahMinuman.withNama(minuman.nama))
                    }, onViewDetails = onViewDetails)
                }
            }
        }
    }
}

@Composable
fun ListItemMinuman(minuman: Minuman, onClick: (Minuman) -> Unit, onViewDetails: (Minuman) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(minuman) }
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Row(verticalAlignment = Alignment.Top) {
            minuman.gambarResId?.let { uri ->
                Image(
                    painter = rememberImagePainter(uri),
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .padding(end = 8.dp)
                )
            }
            // Kolom untuk menampilkan info di samping gambar
            Column {
                InfoMinuman1(label = "Nama", value = minuman.nama)
                InfoMinuman1(label = "Stok", value = minuman.stock)
                InfoMinuman1(label = "Harga", value = minuman.harga)
            }
        }
        Button(
            onClick = { onViewDetails(minuman) },
            modifier = Modifier
                .align(Alignment.End)
                .width(140.dp)
                .height(35.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = stringResource(id = R.string.lihat_detail))
        }
    }
}

@Composable
fun GridItemMinuman(minuman: Minuman, onClick: () -> Unit, onViewDetails: (Minuman) -> Unit) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val spaceBetweenItems = 8.dp  // Spacing yang Anda inginkan
    val padding = 16.dp  // Padding di kiri dan kanan

    val cardWidth = ((screenWidth - padding * 2) - spaceBetweenItems) / 2

    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(cardWidth)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(1.dp, Color.Gray)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally  // Tengah teks
        ) {
            minuman.gambarResId?.let { uri ->
                Image(
                    painter = rememberImagePainter(uri),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)  // Sesuaikan tinggi sesuai kebutuhan
                )
            }
            Text(
                text = minuman.nama,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),  // Teks tebal
                textAlign = TextAlign.Center  // Tengah teks
            )

            // Row to place items on the right
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Stok:",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = "${minuman.stock}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Column {
                    Text(
                        text = "Harga:",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = "${minuman.harga}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            Button(
                onClick = { onViewDetails(minuman) },
                modifier = Modifier
                    .align(Alignment.End)
                    .width(125.dp)
                    .height(30.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.lihat_detail),
                    style = TextStyle(fontSize = 8.sp),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}


@Composable
fun MinumanSearchBar(searchText: String, onSearchTextChange: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp)
            .border(width = 1.dp, color = MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(12.dp))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search Icon",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(32.dp).padding(start = 6.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            BasicTextField(
                value = searchText,
                onValueChange = onSearchTextChange,
                textStyle = TextStyle(color = if (searchText.isEmpty()) Color.Gray else Color.Black, fontSize = 18.sp),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .widthIn(max = 240.dp)
                    .padding(vertical = 10.dp)
            )
        }
        if (searchText.isEmpty()) {
            Text(
                text = "Cari...",
                color = Color.Gray,
                fontSize = 18.sp,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 46.dp)  // Adjust padding if necessary
            )
        }
    }
}

@Composable
fun MinumanListMessage(modifier: Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Tidak ada data ditemukan", style = MaterialTheme.typography.bodyLarge)
    }
}

@Preview(showBackground = true)
@Composable
fun MinumanScreenPreview() {
    Assessment_1Theme {
        MinumanScreen(rememberNavController())
    }
}
