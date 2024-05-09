package org.d3if0041.mopro1.assessment_1.util

import org.d3if0041.mopro1.assessment_1.ui.screen.Fork.DapurViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if0041.mopro1.assessment_1.database.DapurDao
import org.d3if0041.mopro1.assessment_1.ui.screen.Fork.ForkViewModel

class DapurModelFactory(
    private val dao: DapurDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DapurViewModel::class.java)) {
            return DapurViewModel(dao) as T
        } else if (modelClass.isAssignableFrom(ForkViewModel::class.java)) {
            return ForkViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}