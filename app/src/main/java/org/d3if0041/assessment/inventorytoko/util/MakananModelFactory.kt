package org.d3if0041.assessment.inventorytoko.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if0041.assessment.inventorytoko.database.MakanDao
import org.d3if0041.assessment.inventorytoko.screen.food.FoodViewModel
import org.d3if0041.assessment.inventorytoko.screen.food.MakananViewModel

class MakananModelFactory(
    private val dao: MakanDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MakananViewModel::class.java)) {
            return MakananViewModel(dao) as T
        } else if (modelClass.isAssignableFrom(FoodViewModel::class.java)) {
            return FoodViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}