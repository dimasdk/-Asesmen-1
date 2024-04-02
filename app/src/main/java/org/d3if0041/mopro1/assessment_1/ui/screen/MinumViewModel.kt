package org.d3if0041.mopro1.assessment_1.ui.screen

import androidx.lifecycle.ViewModel
import org.d3if0041.mopro1.assessment_1.R
import org.d3if0041.mopro1.assessment_1.model.Makanan
import org.d3if0041.mopro1.assessment_1.model.Minuman

class MinumViewModel : ViewModel() {

    val data = getDataDummy()

    private fun getDataDummy(): List<Minuman> {
        return listOf(
            Minuman("Freshtea", "30", "Rp 8.500", R.drawable.freahtea),
            Minuman("CocaCola", "25", "Rp 13000", R.drawable.cocacola),
            Minuman("Le Minerale", "60", "Rp 3.500", R.drawable.leminerale),
            Minuman("Fanta", "35", "Rp 10.500", R.drawable.fanta),
            Minuman("Mizone", "20", "Rp 9000", R.drawable.mizone),
            Minuman("Pocari Sweat", "27", "Rp 12000", R.drawable.pocari),
            Minuman("Teh Botol Sosro", "64", "Rp 7000", R.drawable.tehbotol)
        )
    }
}