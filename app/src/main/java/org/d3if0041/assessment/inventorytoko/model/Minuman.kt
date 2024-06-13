package org.d3if0041.assessment.inventorytoko.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "minuman")
data class Minuman(
    @PrimaryKey(autoGenerate = false)
    val nama: String,
    val stock: String,
    val harga: String,
    val berat: String,
    val gambarResId: String?,
    val panjang: String,
    val lebar: String,
    val tinggi: String,
    val deskripsi: String
)
