package org.d3if0041.mopro1.assessment_1.halaman

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.d3if0041.mopro1.assessment_1.ui.screen.Mandi.AboutScreen
import org.d3if0041.mopro1.assessment_1.ui.screen.Mandi.AlatMandiScreen
import org.d3if0041.mopro1.assessment_1.ui.screen.Clean.BersihScreen
import org.d3if0041.mopro1.assessment_1.ui.screen.Fork.DapurScreen
import org.d3if0041.mopro1.assessment_1.ui.screen.Drink.DrinkScreen
import org.d3if0041.mopro1.assessment_1.ui.screen.Food.FoodScreen
import org.d3if0041.mopro1.assessment_1.ui.screen.Fork.ForkScreen
import org.d3if0041.mopro1.assessment_1.ui.screen.InfooScreen
import org.d3if0041.mopro1.assessment_1.ui.screen.Mandi.KEY_NAMA
import org.d3if0041.mopro1.assessment_1.ui.screen.Clean.KEY_NAMA_CLEAN
import org.d3if0041.mopro1.assessment_1.ui.screen.Fork.KEY_NAMA_DAPUR
import org.d3if0041.mopro1.assessment_1.ui.screen.Food.KEY_NAMA_MAKANAN
import org.d3if0041.mopro1.assessment_1.ui.screen.Drink.KEY_NAMA_MINUMAN
import org.d3if0041.mopro1.assessment_1.ui.screen.Sembako.KEY_NAMA_SEMBAKO
import org.d3if0041.mopro1.assessment_1.ui.screen.LoginScreen
import org.d3if0041.mopro1.assessment_1.ui.screen.MainScreen
import org.d3if0041.mopro1.assessment_1.ui.screen.Food.MakananScreen
import org.d3if0041.mopro1.assessment_1.ui.screen.Drink.MinumanScreen
import org.d3if0041.mopro1.assessment_1.ui.screen.Food.CleaningScreen
import org.d3if0041.mopro1.assessment_1.ui.screen.Sembako.SbakoScreen
import org.d3if0041.mopro1.assessment_1.ui.screen.Sembako.SembakoScreen
import org.d3if0041.mopro1.assessment_1.ui.screen.TvScreen

@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Login.route) {
            LoginScreen(navController)
        }
        composable(route = Screen.Home.route) {
            MainScreen(navController)
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
        composable(route = Screen.Mandi.route) {
            AlatMandiScreen(navController)
        }
        composable(route = Screen.Cleaning.route) {
            CleaningScreen(navController)
        }
        composable(route = Screen.Dapur.route) {
            DapurScreen(navController)
        }
        composable(route = Screen.Sbako.route) {
            SbakoScreen(navController)
        }

        composable(route = Screen.FormBaru.route){
            AboutScreen(navController)
        }
        composable(route = Screen.Food.route){
            FoodScreen(navController)
        }
        composable(route = Screen.Drink.route){
            DrinkScreen(navController)
        }
        composable(route = Screen.Fork.route){
            ForkScreen(navController)
        }
        composable(route = Screen.Bersih.route){
            BersihScreen(navController)
        }

        composable(
            route = Screen.FormUbah.route,
            arguments = listOf(
                navArgument(KEY_NAMA) {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->
            val nama = navBackStackEntry.arguments?.getString(KEY_NAMA)
            AboutScreen(navController, nama)
        }

        composable(
            route = Screen.FormUbahSembako.route,
            arguments = listOf(
                navArgument(KEY_NAMA_SEMBAKO) {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->
            val nama = navBackStackEntry.arguments?.getString(KEY_NAMA_SEMBAKO)
            SbakoScreen(navController, nama)
        }

        composable(
            route = Screen.FormUbahMakanan.route,
            arguments = listOf(
                navArgument(KEY_NAMA_MAKANAN) {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->
            val nama = navBackStackEntry.arguments?.getString(KEY_NAMA_MAKANAN)
            FoodScreen(navController, nama)
        }

        composable(
            route = Screen.FormUbahMinuman.route,
            arguments = listOf(
                navArgument(KEY_NAMA_MINUMAN) {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->
            val nama = navBackStackEntry.arguments?.getString(KEY_NAMA_MINUMAN)
            DrinkScreen(navController, nama)
        }

        composable(
            route = Screen.FormUbahDapur.route,
            arguments = listOf(
                navArgument(KEY_NAMA_DAPUR) {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->
            val nama = navBackStackEntry.arguments?.getString(KEY_NAMA_DAPUR)
            ForkScreen(navController, nama)
        }

        composable(
            route = Screen.FormUbahClean.route,
            arguments = listOf(
                navArgument(KEY_NAMA_CLEAN) {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->
            val nama = navBackStackEntry.arguments?.getString(KEY_NAMA_CLEAN)
            BersihScreen(navController, nama)
        }
    }
}
