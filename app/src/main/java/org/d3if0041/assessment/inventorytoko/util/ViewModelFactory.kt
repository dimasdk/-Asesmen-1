package org.d3if0041.assessment.inventorytoko.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if0041.assessment.inventorytoko.database.MandiDao
import org.d3if0041.assessment.inventorytoko.screen.mandi.AboutViewModel
import org.d3if0041.assessment.inventorytoko.screen.mandi.MandiViewModel

class ViewModelFactory(
    private val dao: MandiDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MandiViewModel::class.java)) {
            return MandiViewModel(dao) as T
        } else if (modelClass.isAssignableFrom(AboutViewModel::class.java)) {
            return  AboutViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}