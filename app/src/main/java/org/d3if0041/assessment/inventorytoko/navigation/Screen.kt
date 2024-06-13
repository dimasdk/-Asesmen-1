package org.d3if0041.assessment.inventorytoko.navigation

import org.d3if0041.assessment.inventorytoko.screen.clean.KEY_NAMA_CLEAN
import org.d3if0041.assessment.inventorytoko.screen.drink.KEY_NAMA_MINUMAN
import org.d3if0041.assessment.inventorytoko.screen.food.KEY_NAMA_MAKANAN
import org.d3if0041.assessment.inventorytoko.screen.fork.KEY_NAMA_DAPUR
import org.d3if0041.assessment.inventorytoko.screen.mandi.KEY_NAMA_MANDI
import org.d3if0041.assessment.inventorytoko.screen.sembako.KEY_NAMA_SEMBAKO

sealed class Screen (val route:String) {
    data object Home2: Screen("mainScreen2")
    data object Home: Screen("mainScreen")
    data object Infoo: Screen("infooScreen")
    data object Sembako: Screen("sembakoScreen")
    data object Makanan: Screen("makananScreen")
    data object Minuman: Screen("minumanScreen")
    data object Mandi: Screen("mandiScreen")
    data object Cleaning: Screen("cleaningScreen")
    data object Dapur: Screen("dapurScreen")
    data object Sbako: Screen("sbakoScreen")
    data object FormUbahSembako: Screen("sbakoScreen/{$KEY_NAMA_SEMBAKO}") {
        fun withNama(nama: String) = "sbakoScreen/$nama"
    }
    data object FormBaru: Screen("aboutScreen")
    data object FormUbah: Screen("aboutScreen/{$KEY_NAMA_MANDI}") {
        fun withNama(nama: String) = "aboutScreen/$nama"
    }

    data object Food: Screen("foodScreen")
    data object FormUbahMakanan: Screen("foodScreen/{$KEY_NAMA_MAKANAN}") {
        fun withNama(nama: String) = "foodScreen/$nama"
    }

    data object Drink: Screen("drinkScreen")
    data object FormUbahMinuman: Screen("drinkScreen/{$KEY_NAMA_MINUMAN}") {
        fun withNama(nama: String) = "drinkScreen/$nama"
    }

    data object Fork: Screen("forkScreen")
    data object FormUbahDapur: Screen("forkScreen/{$KEY_NAMA_DAPUR}") {
        fun withNama(nama: String) = "forkScreen/$nama"
    }

    data object Bersih: Screen("bersihScreen")
    data object FormUbahClean: Screen("bersihScreen/{$KEY_NAMA_CLEAN}") {
        fun withNama(nama: String) = "bersihScreen/$nama"
    }
}