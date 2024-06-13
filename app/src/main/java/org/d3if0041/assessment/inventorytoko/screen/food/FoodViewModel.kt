package org.d3if0041.assessment.inventorytoko.screen.food

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.d3if0041.assessment.inventorytoko.database.MakanDao
import org.d3if0041.assessment.inventorytoko.model.Makanan

class FoodViewModel(private val dao: MakanDao) : ViewModel() {
    val data = MutableStateFlow<List<Makanan>>(emptyList())

    init {
        viewModelScope.launch {
            dao.getMakanan().collect {
                data.value = it
            }
        }
    }
    fun insert(nama: String, stock: String, harga: String, berat: String, panjang: String, lebar: String, tinggi: String, deskripsi: String, gambarResId: String?) {
        val makanan = Makanan(
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
            dao.insert(makanan)
        }
    }
    suspend fun getMakanan(nama: String): Makanan? {
        return dao.getMakananById(nama)
    }
    fun update(nama: String, stock: String, harga: String, berat: String, panjang: String, lebar: String, tinggi: String, deskripsi: String, gambarResId: String?) {
        val  makanan = Makanan(
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
            dao.update(makanan)
        }
    }

    fun delete(nim: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteMakananById(nim)
        }
    }
}