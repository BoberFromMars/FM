package com.babrou.fm.core.theme.component

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.viewModelFactory
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.time.delay
import okhttp3.internal.wait
import javax.inject.Inject
import kotlin.collections.emptyList
import kotlin.time.Duration

class DropdownViewModel(
//    private val preferencesManager: IPreferencesManager
) : ViewModel() {
    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText.asStateFlow()
    private var _value = MutableStateFlow(DdItem())
    val selectedValue: StateFlow<DdItem> = _value.asStateFlow()

//    val listCopy: MutableStateFlow<List<String>> = MutableStateFlow(ddList.toList())
    private var _items: MutableStateFlow<List<DdItem>> = MutableStateFlow(emptyList())
    val ddList: StateFlow<List<DdItem>> = _items.asStateFlow()
    val filteredList: StateFlow<List<DdItem>> = _searchText
        .combine(_items) { search, items ->
            items.filter { it.label.contains(search, ignoreCase = true) }
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    private val _selection = MutableStateFlow(DdItem())
    val selection: StateFlow<DdItem> = _selection.asStateFlow()

    fun onSearchTextChanged(text: String) {
        _searchText.value = text
    }

    fun fillDdList(ddList: List<DdItem>) {
        _items.value = ddList
    }

    fun onItemSelected(item: DdItem) {
        _selection.value = item
        Log.v("TEST SELECT", item.label)
    }

}