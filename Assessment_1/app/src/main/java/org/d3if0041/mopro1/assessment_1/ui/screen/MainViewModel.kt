package org.d3if0041.mopro1.assessment_1.ui.screen

import androidx.lifecycle.ViewModel
import org.d3if0041.mopro1.assessment_1.R
import org.d3if0041.mopro1.assessment_1.model.Catatan

class MainViewModel : ViewModel() {

    val data = getDataDummy()

    private fun getDataDummy(): List<Catatan> {
        return listOf(
            Catatan("Beras Solok", "12 Karung", "Rp 180000", R.drawable.beras_solok),
            Catatan("Minyak Bimoli 1L", "25", "Rp 32000", R.drawable.minyak_bimoli),
            Catatan("Gula", "60", "Rp 15000", R.drawable.gula),
            Catatan("Garam", "35", "Rp 6000", R.drawable.garam),
            Catatan("Masako", "20", "Rp 2500", R.drawable.masako),
            Catatan("Segitiga Biru", "27", "Rp 18000", R.drawable.segitiga_biru),
            Catatan("Mie Goreng ", "64", "Rp 4000", R.drawable.mie_goreng)
        )
    }
}