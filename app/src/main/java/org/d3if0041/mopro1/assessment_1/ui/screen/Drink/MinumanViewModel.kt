package org.d3if0041.mopro1.assessment_1.ui.screen.Drink

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import org.d3if0041.mopro1.assessment_1.database.MinumanDao
import org.d3if0041.mopro1.assessment_1.model.Minuman

class MinumanViewModel(private val dao: MinumanDao) : ViewModel() {
    private val _searchText = MutableStateFlow("")
    private val _showList = MutableStateFlow(false)

    val searchText: StateFlow<String> = _searchText.asStateFlow()
    val showList: StateFlow<Boolean> = _showList.asStateFlow()



    val data: StateFlow<List<Minuman>> = _searchText
        .flatMapLatest { search ->
            dao.getMinuman().map { list ->
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