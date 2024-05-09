package org.d3if0041.mopro1.assessment_1.ui.screen.Mandi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.d3if0041.mopro1.assessment_1.database.MandiDao
import org.d3if0041.mopro1.assessment_1.halaman.Screen
import org.d3if0041.mopro1.assessment_1.model.Mandi

class AboutViewModel(private val dao: MandiDao) : ViewModel() {
    val data = MutableStateFlow<List<Mandi>>(emptyList())

    init {
        viewModelScope.launch {
            dao.getMandi().collect {
                data.value = it
            }
        }
    }
    fun insert(nama: String, stock: String, harga: String, berat: String, panjang: String, lebar: String, tinggi: String, deskripsi: String, gambarResId: String?) {
        val mandi = Mandi(
            gambarResId = gambarResId,
            stock = stock,
            nama = nama,
            harga = harga,
            berat = berat,
            panjang = panjang,
            lebar =  lebar,
            tinggi = tinggi,
            deskripsi = deskripsi
        )
        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(mandi)
        }
    }
    suspend fun getMandi(nama: String): Mandi? {
        return dao.getMandiById(nama)
    }
    fun update(nama: String, stock: String, harga: String, berat: String, panjang: String, lebar: String, tinggi: String, deskripsi: String, gambarResId: String?) {
        val mandi = Mandi(
            nama = nama,
            stock = stock,
            harga = harga,
            berat = berat,
            panjang = panjang,
            lebar = lebar,
            tinggi = tinggi,
            deskripsi = deskripsi,
            gambarResId = gambarResId
        )
        viewModelScope.launch(Dispatchers.IO) {
            dao.update(mandi)
        }
    }

    fun delete(nim: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteById(nim)
        }
    }
}