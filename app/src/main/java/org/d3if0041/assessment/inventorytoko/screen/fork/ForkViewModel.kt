package org.d3if0041.assessment.inventorytoko.screen.fork

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.d3if0041.assessment.inventorytoko.database.DapurDao
import org.d3if0041.assessment.inventorytoko.model.Dapur

class ForkViewModel(private val dao: DapurDao) : ViewModel() {
    val data = MutableStateFlow<List<Dapur>>(emptyList())

    init {
        viewModelScope.launch {
            dao.getDapur().collect {
                data.value = it
            }
        }
    }
    fun insert(nama: String, stock: String, harga: String, berat: String, panjang: String, lebar: String, tinggi: String, deskripsi: String, gambarResId: String?) {
        val dapur = Dapur(
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
            dao.insert(dapur)
        }
    }
    suspend fun getDapur(nama: String): Dapur? {
        return dao.getDapurById(nama)
    }
    fun update(nama: String, stock: String, harga: String, berat: String, panjang: String, lebar: String, tinggi: String, deskripsi: String, gambarResId: String?) {
        val dapur = Dapur(
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
            dao.update(dapur)
        }
    }

    fun delete(nim: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteDapurById(nim)
        }
    }
}