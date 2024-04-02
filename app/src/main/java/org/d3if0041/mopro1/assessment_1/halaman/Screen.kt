package org.d3if0041.mopro1.assessment_1.halaman

sealed class Screen (val route:String) {
    data object Home: Screen("mainScreen")
    data object About: Screen("aboutScreen")
    data object Infoo: Screen("infooScreen")
    data object Sembako: Screen("sembakoScreen")
    data object Makanan: Screen("makananScreen")
    data object Minuman: Screen("minumanScreen")

}
