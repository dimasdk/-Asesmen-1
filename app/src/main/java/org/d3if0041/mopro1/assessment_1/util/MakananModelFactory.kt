package org.d3if0041.mopro1.assessment_1.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if0041.mopro1.assessment_1.database.MakanDao
import org.d3if0041.mopro1.assessment_1.ui.screen.Food.FoodViewModel
import org.d3if0041.mopro1.assessment_1.ui.screen.Food.MakananViewModel

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