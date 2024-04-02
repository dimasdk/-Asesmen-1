package org.d3if0041.mopro1.assessment_1.ui.screen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Settings
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if0041.mopro1.assessment_1.R
import org.d3if0041.mopro1.assessment_1.halaman.Screen
import org.d3if0041.mopro1.assessment_1.ui.theme.Assessment_1Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    var expanded by remember { mutableStateOf(false) }
    var expandedSettings by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
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
                                navController.navigate(Screen.About.route)
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

        ScreenContent(Modifier.padding(padding), navController)
    }
}

@Composable
fun ScreenContent(padding: Modifier, navController: NavController) {
    val context = LocalContext.current
    //var searchText by remember { mutableStateOf("") }

    Column(
        modifier = padding
            .fillMaxWidth()
            .padding(16.dp)
    ) {
//        BasicTextField(
//            value = searchText,
//            onValueChange = { searchText = it },
//            keyboardOptions = KeyboardOptions(
//                keyboardType = KeyboardType.Text,
//                imeAction = ImeAction.Done,
//                capitalization = KeyboardCapitalization.None
//            ),
//            singleLine = true,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(vertical = 8.dp)
//                .height(48.dp)
//                .background(color = Color.LightGray)
//                .padding(start = 32.dp, end = 16.dp)
//        ) {
//            Row(
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Icon(
//                    imageVector = Icons.Default.Search,
//                    contentDescription = null,
//                    tint = Color.Gray,
//                    modifier = Modifier.size(24.dp)
//                )
//                Text(
//                    text = if (searchText.isEmpty()) "Cari..." else searchText,
//                    textAlign = TextAlign.Center,
//                    fontWeight = FontWeight.SemiBold,
//                    color = if (searchText.isEmpty()) Color.Gray else Color.Black,
//                    fontSize = 18.sp,
//                    modifier = Modifier.padding(start = 8.dp)
//                )
//            }
//        }
        Row(
            modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ImageOnly(
                painter = painterResource(id = R.drawable.sosro),
                navController = navController
            )
            ImageOnly(
                painter = painterResource(id = R.drawable.img),
                navController = navController
            )
            ImageOnly(
                painter = painterResource(id = R.drawable.clean),
                navController = navController
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ImageWithText(
                painter = painterResource(id = R.drawable.sembako),
                text = stringResource(R.string.sembako),
                navController = navController,
                context = context
            )
            ImageWithText(
                painter = painterResource(id = R.drawable.snack),
                text = stringResource(R.string.makanan),
                navController = navController,
                context = context
            )
            ImageWithText(
                painter = painterResource(id = R.drawable.minuman),
                text = stringResource(R.string.minuman),
                navController = navController,
                context = context
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ImageWithText(
                painter = painterResource(id = R.drawable.mandi),
                text = stringResource(R.string.peralatan_mandi),
                navController = navController,
                context = context
            )
            ImageWithText(
                painter = painterResource(id = R.drawable.cleaning),
                text = stringResource(R.string.peralatan_kebersihan),
                navController = navController,
                context = context
            )
            ImageWithText(
                painter = painterResource(id = R.drawable.fork),
                text = stringResource(R.string.peralatan_dapur),
                navController = navController,
                context = context
            )
        }
    }
}


@Composable
fun ImageOnly(
    painter: Painter,
    navController: NavController
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.size(220.dp) //
        )
    }
}

@Composable
fun ImageWithText(
    painter: Painter,
    text: String,
    navController: NavController,
    context: Context
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.size(32.dp)
        )
        Text(
            text = text,
            textAlign = TextAlign.Center,
            modifier = Modifier.clickable {
                when (text) {
                    "Sembako" -> navController.navigate(Screen.Sembako.route)
                    "Makanan" -> navController.navigate(Screen.Makanan.route)
                    "Minuman" -> navController.navigate(Screen.Minuman.route)
                    else -> {
                        Toast.makeText(
                            context,
                            "Belum ada data",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Assessment_1Theme {
        MainScreen(rememberNavController())
    }
}