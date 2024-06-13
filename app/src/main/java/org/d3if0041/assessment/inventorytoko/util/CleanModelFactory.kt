package org.d3if0041.assessment.inventorytoko.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if0041.assessment.inventorytoko.database.CleanDao
import org.d3if0041.assessment.inventorytoko.screen.clean.BersihViewModel
import org.d3if0041.assessment.inventorytoko.screen.clean.CleanViewModel

class CleanModelFactory(
    private val dao: CleanDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CleanViewModel::class.java)) {
            return CleanViewModel(dao) as T
        } else if (modelClass.isAssignableFrom(BersihViewModel::class.java)) {
            return BersihViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}