package org.d3if0041.assessment.inventorytoko.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if0041.assessment.inventorytoko.database.CatatanDao
import org.d3if0041.assessment.inventorytoko.screen.sembako.SbakoViewModel
import org.d3if0041.assessment.inventorytoko.screen.sembako.SembakoViewModel

class SembakoModelFactory(
    private val dao: CatatanDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SembakoViewModel::class.java)) {
            return SembakoViewModel(dao) as T
        } else if (modelClass.isAssignableFrom(SbakoViewModel::class.java)) {
            return  SbakoViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}