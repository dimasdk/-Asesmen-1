package org.d3if0041.assessment.inventorytoko.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if0041.assessment.inventorytoko.database.DapurDao
import org.d3if0041.assessment.inventorytoko.screen.fork.DapurViewModel
import org.d3if0041.assessment.inventorytoko.screen.fork.ForkViewModel

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