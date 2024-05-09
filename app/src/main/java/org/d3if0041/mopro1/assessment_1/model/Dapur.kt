package org.d3if0041.mopro1.assessment_1.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dapur")
data class Dapur(
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
