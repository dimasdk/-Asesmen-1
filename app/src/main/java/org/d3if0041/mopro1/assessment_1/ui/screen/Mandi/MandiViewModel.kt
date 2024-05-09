package org.d3if0041.mopro1.assessment_1.ui.screen.Mandi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import org.d3if0041.mopro1.assessment_1.database.MandiDao
import org.d3if0041.mopro1.assessment_1.model.Mandi

class MandiViewModel(private val dao: MandiDao) : ViewModel() {
    private val _searchText = MutableStateFlow("")
    private val _showList = MutableStateFlow(false) // Mengatur showList ke false secara default

    val searchText: StateFlow<String> = _searchText.asStateFlow()
    val showList: StateFlow<Boolean> = _showList.asStateFlow() // Expose showList sebagai StateFlow



    val data: StateFlow<List<Mandi>> = _searchText
        .flatMapLatest { search ->
            dao.getMandi().map { list ->
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
        // Set showList to false by default
        _showList.value = false
    }
}

