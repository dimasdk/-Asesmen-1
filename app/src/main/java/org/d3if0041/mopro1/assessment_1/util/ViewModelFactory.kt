package org.d3if0041.mopro1.assessment_1.util

import org.d3if0041.mopro1.assessment_1.ui.screen.Mandi.MandiViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if0041.mopro1.assessment_1.database.MandiDao
import org.d3if0041.mopro1.assessment_1.ui.screen.Mandi.AboutViewModel

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