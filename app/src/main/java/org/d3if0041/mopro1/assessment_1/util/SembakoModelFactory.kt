package org.d3if0041.mopro1.assessment_1.util

import org.d3if0041.mopro1.assessment_1.ui.screen.Sembako.SembakoViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if0041.mopro1.assessment_1.database.CatatanDao
import org.d3if0041.mopro1.assessment_1.ui.screen.Sembako.SbakoViewModel

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