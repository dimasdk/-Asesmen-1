package org.d3if0041.assessment.inventorytoko.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.d3if0041.assessment.inventorytoko.screen.InfooScreen
import org.d3if0041.assessment.inventorytoko.screen.clean.BersihScreen
import org.d3if0041.assessment.inventorytoko.screen.clean.CleaningScreen
import org.d3if0041.assessment.inventorytoko.screen.clean.KEY_NAMA_CLEAN
import org.d3if0041.assessment.inventorytoko.screen.drink.DrinkScreen
import org.d3if0041.assessment.inventorytoko.screen.drink.KEY_NAMA_MINUMAN
import org.d3if0041.assessment.inventorytoko.screen.drink.MinumanScreen
import org.d3if0041.assessment.inventorytoko.screen.food.FoodScreen
import org.d3if0041.assessment.inventorytoko.screen.food.KEY_NAMA_MAKANAN
import org.d3if0041.assessment.inventorytoko.screen.food.MakananScreen
import org.d3if0041.assessment.inventorytoko.screen.fork.DapurScreen
import org.d3if0041.assessment.inventorytoko.screen.fork.ForkScreen
import org.d3if0041.assessment.inventorytoko.screen.fork.KEY_NAMA_DAPUR
import org.d3if0041.assessment.inventorytoko.screen.main.MainScreen
import org.d3if0041.assessment.inventorytoko.screen.main2.MainScreen2
import org.d3if0041.assessment.inventorytoko.screen.mandi.AboutScreen
import org.d3if0041.assessment.inventorytoko.screen.mandi.AlatMandiScreen
import org.d3if0041.assessment.inventorytoko.screen.mandi.KEY_NAMA_MANDI
import org.d3if0041.assessment.inventorytoko.screen.sembako.KEY_NAMA_SEMBAKO
import org.d3if0041.assessment.inventorytoko.screen.sembako.SbakoScreen
import org.d3if0041.assessment.inventorytoko.screen.sembako.SembakoScreen

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home2.route
    ) {
        composable(route = Screen.Home.route) {
            MainScreen(navController)
        }
        composable(route = Screen.Home2.route) {
            MainScreen2(navController)
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
                navArgument(KEY_NAMA_MANDI) {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->
            val nama = navBackStackEntry.arguments?.getString(KEY_NAMA_MANDI)
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