package org.d3if0041.mopro1.assessment_1.util

import org.d3if0041.mopro1.assessment_1.ui.screen.Clean.CleanViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if0041.mopro1.assessment_1.database.CleanDao
import org.d3if0041.mopro1.assessment_1.ui.screen.Clean.BersihViewModel

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