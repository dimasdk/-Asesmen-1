package org.d3if0041.assessment.inventorytoko.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if0041.assessment.inventorytoko.database.MinumanDao
import org.d3if0041.assessment.inventorytoko.screen.drink.DrinkViewModel
import org.d3if0041.assessment.inventorytoko.screen.drink.MinumanViewModel

class MinumanModelFactory(
    private val dao: MinumanDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MinumanViewModel::class.java)) {
            return MinumanViewModel(dao) as T
        } else if (modelClass.isAssignableFrom(DrinkViewModel::class.java)) {
            return DrinkViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}