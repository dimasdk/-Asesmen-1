package org.d3if0041.mopro1.assessment_1.ui.screen.Sembako

import SettingsDataStore
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import coil.compose.rememberImagePainter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if0041.mopro1.assessment_1.R
import org.d3if0041.mopro1.assessment_1.database.CatatanDb
import org.d3if0041.mopro1.assessment_1.halaman.Screen
import org.d3if0041.mopro1.assessment_1.model.Catatan
import org.d3if0041.mopro1.assessment_1.ui.theme.Assessment_1Theme
import org.d3if0041.mopro1.assessment_1.util.SembakoModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SembakoScreen(navController: NavHostController) {
    val context = LocalContext.current
    val db = CatatanDb.getInstance(context)
    val factory = SembakoModelFactory(db.dao)
    val viewModel: SembakoViewModel = viewModel(factory = factory)
    var showBottomSheet by remember { mutableStateOf(false) }
    var selectedSembako by remember { mutableStateOf<Catatan?>(null) }

    val dataStore = SettingsDataStore(LocalContext.current)
    val showList by dataStore.layoutFlow("sembako_layout").collectAsState(initial = true)

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
                title = { Text(text = stringResource(id = R.string.sembako)) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                actions = {
                    IconButton(onClick = {
                        CoroutineScope(Dispatchers.IO).launch {
                            dataStore.saveLayout(!showList, "sembako_layout")
                        }
                    }){
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
                onClick = { navController.navigate(Screen.Sbako.route) }
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = stringResource(id = R.string.tambah_barang))
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            val searchText by viewModel.searchText.collectAsState()
            SembakoSearchBar(searchText, viewModel::setSearchText)
            ScreenContent( showList, modifier = Modifier.fillMaxSize(), navController, viewModel) { catatan ->
                selectedSembako= catatan
                showBottomSheet = true
            }
        }
    }

    if (showBottomSheet && selectedSembako != null) {
        BottomSheetScaffold(
            sheetContent = {
                BottomSheetContents(selectedSembako!!) {
                    selectedSembako = null
                    showBottomSheet = false
                }
            },
            content = { },
            sheetContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Composable
fun BottomSheetContents(catatan: Catatan, onClose: () -> Unit) {
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

        ItemSembako(label = "Berat", value = "${catatan.berat}")
        Spacer(modifier = Modifier.height(20.dp))

        ItemSembako(label = "Panjang", value = "${catatan.panjang}")
        Spacer(modifier = Modifier.height(20.dp))

        ItemSembako(label = "Lebar", value = "${catatan.lebar}")
        Spacer(modifier = Modifier.height(20.dp))

        ItemSembako(label = "Tinggi", value = "${catatan.tinggi}")
        Spacer(modifier = Modifier.height(20.dp))

        Spacer(modifier = Modifier.height(4.dp))
        Divider(modifier = Modifier.padding(vertical = 10.dp))

        ItemSembako(label = "Deskripsi Produk", value = catatan.deskripsi, isDescription = true)
    }
}

@Composable
fun ItemSembako(label: String, value: String, isDescription: Boolean = false) {
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
fun ItemSembako1(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = if (label == "Nama") value else "$label: $value",
            style = if (label == "Nama") MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold) else MaterialTheme.typography.bodyLarge,
        )
    }
}

@Composable
fun ScreenContent(
    showList:Boolean,
    modifier: Modifier,
    navController: NavHostController,
    viewModel: SembakoViewModel,
    onViewDetails: (Catatan) -> Unit
) {
    val data by viewModel.data.collectAsState()
    val context = LocalContext.current
    val db = CatatanDb.getInstance(context)
    val factory = SembakoModelFactory(db.dao)
    val viewModel: SembakoViewModel = viewModel(factory = factory)

    if (data.isEmpty()) {
        SembakoListMessage(modifier)
    } else {
        if (showList) {
            LazyColumn(
                modifier = modifier.fillMaxSize().padding(16.dp),
                contentPadding = PaddingValues(bottom = 84.dp)
            ) {
                items(data) { catatan ->
                    ListItemSembako(catatan = catatan, onClick = {
                        navController.navigate(Screen.FormUbahSembako.withNama(catatan.nama))
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
                items(data) { catatan ->
                    GridItemSembako(catatan = catatan, onClick = {
                        navController.navigate(Screen.FormUbahSembako.withNama(catatan.nama))
                    }, onViewDetails = onViewDetails)
                }
            }
        }
    }
}

@Composable
fun ListItemSembako(catatan: Catatan, onClick: (Catatan) -> Unit, onViewDetails: (Catatan) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(catatan) }
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Row(verticalAlignment = Alignment.Top) {
            catatan.gambarResId?.let { uri ->
                Image(
                    painter = rememberImagePainter(uri),
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .padding(end = 8.dp)
                )
            }
            Column {
                ItemSembako1(label = "Nama", value = catatan.nama)
                ItemSembako1(label = "Stok", value = catatan.stock)
                ItemSembako1(label = "Harga", value = catatan.harga)
            }
        }
        Button(
            onClick = { onViewDetails(catatan) },
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
fun SembakoSearchBar(searchText: String, onSearchTextChange: (String) -> Unit) {
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
fun GridItemSembako(
    catatan: Catatan,
    onClick: () -> Unit,
    onViewDetails: (Catatan) -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val spaceBetweenItems = 8.dp
    val padding = 16.dp

    val cardWidth = ((screenWidth - padding * 2) - spaceBetweenItems * 2) / 3 // Adjusted card width
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            catatan.gambarResId?.let { uri ->
                Image(
                    painter = rememberImagePainter(uri),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                )
            }
            Text(
                text = catatan.nama,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center,
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Stok:",
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.End
                    )
                }
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "${catatan.stock}",
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Start
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Harga:",
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.End
                    )
                }
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "${catatan.harga}",
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Start
                    )
                }
            }

            Button(
                onClick = { onViewDetails(catatan) },
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
fun SembakoListMessage(modifier: Modifier) {
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
fun SembakoScreenPreview() {
    Assessment_1Theme {
        SembakoScreen(NavHostController(LocalContext.current))
    }
}
