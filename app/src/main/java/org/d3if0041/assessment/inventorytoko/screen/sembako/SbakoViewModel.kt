package org.d3if0041.assessment.inventorytoko.screen.sembako

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.d3if0041.assessment.inventorytoko.database.CatatanDao
import org.d3if0041.assessment.inventorytoko.model.Catatan

class SbakoViewModel(private val dao: CatatanDao) : ViewModel() {
    val data = MutableStateFlow<List<Catatan>>(emptyList())

    init {
        viewModelScope.launch {
            dao.getCatatan().collect {
                data.value = it
            }
        }
    }
    fun insert(nama: String, stock: String, harga: String, berat: String, panjang: String, lebar: String, tinggi: String, deskripsi: String, gambarResId: String?) {
        val catatan = Catatan(
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
            dao.insert(catatan)
        }
    }
    suspend fun getCatatan(nama: String): Catatan? {
        return dao.getCatatanById(nama)
    }
    fun update(nama: String, stock: String, harga: String, berat: String, panjang: String, lebar: String, tinggi: String, deskripsi: String, gambarResId: String?) {
        val catatan = Catatan(
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
            dao.update(catatan)
        }
    }

    fun delete(nim: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteCatatanById(nim)
        }
    }
}