package org.d3if0041.mopro1.assessment_1.ui.screen.Clean

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.d3if0041.mopro1.assessment_1.database.CleanDao
import org.d3if0041.mopro1.assessment_1.model.Clean

class BersihViewModel(private val dao: CleanDao) : ViewModel() {
    val data = MutableStateFlow<List<Clean>>(emptyList())

    init {
        viewModelScope.launch {
            dao.getClean().collect {
                data.value = it
            }
        }
    }
    fun insert(nama: String, stock: String, harga: String, berat: String, panjang: String, lebar: String, tinggi: String, deskripsi: String, gambarResId: String?) {
        val clean = Clean(
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
            dao.insert(clean)
        }
    }
    suspend fun getClean(nama: String): Clean? {
        return dao.getCleanById(nama)
    }
    fun update(nama: String, stock: String, harga: String, berat: String, panjang: String, lebar: String, tinggi: String, deskripsi: String, gambarResId: String?) {
        val  clean = Clean(
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
            dao.update(clean)
        }
    }

    fun delete(nim: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteCleanById(nim)
        }
    }
}