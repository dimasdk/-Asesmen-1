package org.d3if0041.mopro1.assessment_1.ui.screen.Fork

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.d3if0041.mopro1.assessment_1.database.DapurDao
import org.d3if0041.mopro1.assessment_1.database.MandiDao
import org.d3if0041.mopro1.assessment_1.model.Dapur
import org.d3if0041.mopro1.assessment_1.model.Mandi

class DapurViewModel(private val dao: DapurDao) : ViewModel() {
    private val _searchText = MutableStateFlow("")
    private val _setShowList = MutableStateFlow(false) // Mengatur showList ke false secara default

    val searchText: StateFlow<String> = _searchText.asStateFlow()
    val setShowList: StateFlow<Boolean> = _setShowList.asStateFlow() // Expose showList sebagai StateFlow



    val data: StateFlow<List<Dapur>> = _searchText
        .flatMapLatest { search ->
            dao.getDapur().map { list ->
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

    fun setShowList(value: Boolean) {
        _setShowList.value = value
    }

    init {
        _setShowList.value = false
    }
}