package org.d3if0041.mopro1.assessment_1.util

import org.d3if0041.mopro1.assessment_1.ui.screen.Drink.MinumanViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if0041.mopro1.assessment_1.database.MinumanDao
import org.d3if0041.mopro1.assessment_1.ui.screen.Drink.DrinkViewModel

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