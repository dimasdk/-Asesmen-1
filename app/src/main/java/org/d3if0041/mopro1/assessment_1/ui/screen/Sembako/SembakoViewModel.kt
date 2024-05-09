package org.d3if0041.mopro1.assessment_1.ui.screen.Sembako

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import org.d3if0041.mopro1.assessment_1.database.CatatanDao
import org.d3if0041.mopro1.assessment_1.database.MakanDao
import org.d3if0041.mopro1.assessment_1.model.Catatan
import org.d3if0041.mopro1.assessment_1.model.Makanan

class SembakoViewModel(private val dao: CatatanDao) : ViewModel() {
    private val _searchText = MutableStateFlow("")
    private val _showList = MutableStateFlow(false)

    val searchText: StateFlow<String> = _searchText.asStateFlow()
    val showList: StateFlow<Boolean> = _showList.asStateFlow()



    val data: StateFlow<List<Catatan>> = _searchText
        .flatMapLatest { search ->
            dao.getCatatan().map { list ->
                if (search.isEmpty()) list
                else list.filter { it.nama.contains(search, ignoreCase = true) }
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()
        )

    fun setSearchText(search: String) {
        _searchText.value = search
    }

    fun showList(value: Boolean) {
        _showList.value = value
    }

    init {
        _showList.value = false
    }
}