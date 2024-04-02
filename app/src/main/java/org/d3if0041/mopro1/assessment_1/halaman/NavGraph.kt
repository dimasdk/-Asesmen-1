package org.d3if0041.mopro1.assessment_1.halaman

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.d3if0041.mopro1.assessment_1.ui.screen.AboutScreen
import org.d3if0041.mopro1.assessment_1.ui.screen.InfooScreen
import org.d3if0041.mopro1.assessment_1.ui.screen.MainScreen
import org.d3if0041.mopro1.assessment_1.ui.screen.MakananScreen
import org.d3if0041.mopro1.assessment_1.ui.screen.MinumanScreen
import org.d3if0041.mopro1.assessment_1.ui.screen.SembakoScreen

@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            MainScreen(navController)
        }
        composable(route = Screen.About.route) {
            AboutScreen(navController)
        }
        composable(route = Screen.Sembako.route) {
            SembakoScreen(navController)
        }
        composable(route = Screen.Makanan.route) {
            MakananScreen(navController)
        }
        composable(route = Screen.Infoo.route) {
            InfooScreen(navController)
        }
        composable(route = Screen.Minuman.route) {
            MinumanScreen(navController)
        }
    }
}
