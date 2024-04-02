package org.d3if0041.mopro1.assessment_1.ui.screen

import androidx.lifecycle.ViewModel
import org.d3if0041.mopro1.assessment_1.R
import org.d3if0041.mopro1.assessment_1.model.Makanan

class MakanViewModel : ViewModel() {

    val data = getDataDummy()

    private fun getDataDummy(): List<Makanan> {
        return listOf(
            Makanan("Beng-beng", "45", "Rp 3000", R.drawable.bengbeng),
            Makanan("Taro", "25", "Rp 9000", R.drawable.taro),
            Makanan("Chitato", "60", "Rp 16000", R.drawable.chitato),
            Makanan("Oreo", "35", "Rp 9000", R.drawable.oreo),
            Makanan("SilverQueen", "20", "Rp 16000", R.drawable.silver),
            Makanan("Kacang Garuda", "27", "Rp 12000", R.drawable.kacang_garuda),
            Makanan("Kinder Joy", "64", "Rp 11000", R.drawable.kinder_joy)
        )
    }
}