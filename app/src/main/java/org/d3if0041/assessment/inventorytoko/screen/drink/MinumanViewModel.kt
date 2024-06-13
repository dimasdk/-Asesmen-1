package org.d3if0041.assessment.inventorytoko.screen.drink

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.d3if0041.assessment.inventorytoko.database.MinumanDao
import org.d3if0041.assessment.inventorytoko.model.Minuman

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