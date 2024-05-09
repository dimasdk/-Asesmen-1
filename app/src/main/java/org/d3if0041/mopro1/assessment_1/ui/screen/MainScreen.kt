package org.d3if0041.mopro1.assessment_1.ui.screen

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import org.d3if0041.mopro1.assessment_1.R
import org.d3if0041.mopro1.assessment_1.halaman.Screen
import org.d3if0041.mopro1.assessment_1.ui.theme.Assessment_1Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    var expanded by remember { mutableStateOf(false) }
    var expandedSettings by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }
    var showBottomSheet by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.height(60.dp),
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxHeight()
                    ) {
                        Text(text = stringResource(id = R.string.app_name))
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                actions = {
                    IconButton(onClick = { expanded = true }) {
                        Icon(
                            imageVector = Icons.Outlined.Menu,
                            contentDescription = stringResource(R.string.tentang_aplikasi),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.AddCircle,
                                    contentDescription = stringResource(R.string.tambah_barang),
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            },
                            text = {
                                Text(stringResource(R.string.tambah_barang))
                            },
                            onClick = {
                                expanded = false
                                navController.navigate(Screen.Mandi.route)
                            }
                        )
                        DropdownMenuItem(
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.Info,
                                    contentDescription = stringResource(R.string.tentang_aplikasi),
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            },
                            text = {
                                Text(stringResource(R.string.tentang_aplikasi))
                            },
                            onClick = {
                                expanded = false
                                navController.navigate(Screen.Infoo.route)
                            }
                        )
                        DropdownMenuItem(
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.Settings,
                                    contentDescription = stringResource(R.string.pengaturan),
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            },
                            text = {
                                Text(stringResource(R.string.pengaturan))
                            },
                            onClick = {
                                expanded = false
                                expandedSettings = true
                            }
                        )
                    }
                }
            )
        }
    ) { padding ->
            ScreenContent(
                padding = padding,
                navController = navController,
                showBottomSheet = showBottomSheet

            )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenContent(padding: PaddingValues, navController: NavController, showBottomSheet: Boolean) {
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        item {
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .background(color = Color.White, shape = RoundedCornerShape(12.dp))
                    .padding(horizontal = 20.dp)
                    .height(250.dp)
            ) {
                Carousel(
                    modifier = Modifier.fillMaxSize(),
                    initialPage = 0,
                    pageCount = 3,
                    onPageChanged = { /* Do something on page change */ }
                ) { page ->
                    when (page) {
                        0 -> ImageOnly(
                            painter = painterResource(id = R.drawable.iklan1),
                            navController = navController
                        )
                        1 -> ImageOnly(
                            painter = painterResource(id = R.drawable.iklan2),
                            navController = navController
                        )
                        2 -> ImageOnly(
                            painter = painterResource(id = R.drawable.iklan3),
                            navController = navController
                        )
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(20.dp))
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.kategori),
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp),
                    color = Color.Black
                )
//                Text(
//                    text = stringResource(id = R.string.lihat_semua),
//                    style = MaterialTheme.typography.titleLarge.copy(fontSize = 14.sp),
//                    color = MaterialTheme.colorScheme.primary,
//                    modifier = Modifier.clickable { /* Implement action */ }
//                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(20.dp))
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth().padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ImageWithText(
                    painter = painterResource(id = R.drawable.rice),
                    text = stringResource(R.string.sembako),
                    navController = navController,
                    context = context,
                    destination = Screen.Sembako.route,
                    textSize = 10.sp,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                ImageWithText(
                    painter = painterResource(id = R.drawable.snack),
                    text = stringResource(R.string.makanan),
                    navController = navController,
                    context = context,
                    destination = Screen.Makanan.route,
                    textSize = 14.sp,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                ImageWithText(
                    painter = painterResource(id = R.drawable.drinki),
                    text = stringResource(R.string.minuman),
                    navController = navController,
                    context = context,
                    destination = Screen.Minuman.route,
                    textSize = 14.sp,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(20.dp))
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ImageWithText(
                    painter = painterResource(id = R.drawable.mandi),
                    text = stringResource(R.string.peralatan_mandi),
                    navController = navController,
                    context = context,
                    destination = Screen.Mandi.route,
                    textSize = 10.sp,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                ImageWithText(
                    painter = painterResource(id = R.drawable.clean),
                    text = stringResource(R.string.peralatan_kebersihan),
                    navController = navController,
                    context = context,
                    destination = Screen.Cleaning.route,
                    textSize = 14.sp,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                ImageWithText(
                    painter = painterResource(id = R.drawable.cook),
                    text = stringResource(R.string.peralatan_dapur),
                    navController = navController,
                    context = context,
                    destination = Screen.Dapur.route,
                    textSize = 14.sp,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
        item {
            Spacer(modifier = Modifier.height(20.dp))
        }

        if (showBottomSheet) {
            item {
                BottomSheetScaffold(
                    sheetContent = {
                        Text("Bottom Sheet Content", modifier = Modifier.padding(16.dp))
                    },
                    sheetPeekHeight = 100.dp,
                    content = {}
                )
            }
        }
    }
}

@Composable
fun ImageOnly(
    painter: Painter,
    navController: NavController
) {
    Image(
        painter = painter,
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
    )
}

@Composable
fun ImageWithText(
    painter: Painter,
    text: String,
    navController: NavController,
    context: Context,
    destination: String,
    textSize: TextUnit,
    modifier: Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .size(30.dp)
                .clickable {
                    println("Navigating to $destination")
                    navController.navigate(destination)
                }
        )
        Text(
            text = text,
            textAlign = TextAlign.Center,
            fontSize = 12.sp,
            modifier = Modifier.clickable {
                println("Navigating to $destination")
                navController.navigate(destination)
            }
        )
    }
}


@Composable
fun Carousel(
    modifier: Modifier = Modifier,
    initialPage: Int = 0,
    pageCount: Int,
    onPageChanged: (Int) -> Unit,
    content: @Composable (Int) -> Unit
) {
    var currentPage by remember { mutableStateOf(initialPage) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            currentPage = (currentPage + 1) % pageCount
        }
    }

    Column(
        modifier = modifier.fillMaxWidth().padding(horizontal = 2.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)  // Menyesuaikan ukuran dengan konten
        ) {
            content(currentPage)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            repeat(pageCount) { index ->
                CarouselIndicator(
                    isSelected = index == currentPage,
                    onClick = { currentPage = index }
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

@Composable
fun CarouselIndicator(
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(8.dp)
            .clip(CircleShape)
            .background(
                color = if (isSelected) Color.Gray else Color.LightGray
            )
            .clickable(onClick = onClick)
    )
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Assessment_1Theme {
        MainScreen(rememberNavController())
    }
}
