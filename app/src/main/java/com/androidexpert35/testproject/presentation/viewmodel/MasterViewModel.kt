package com.androidexpert35.testproject.presentation.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidexpert35.testproject.domain.entity.Item
import com.androidexpert35.testproject.domain.usecase.DeleteItemUseCase
import com.androidexpert35.testproject.domain.usecase.GetItemsUseCase
import com.androidexpert35.testproject.presentation.intent.MasterIntent
import com.androidexpert35.testproject.presentation.state.MasterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MasterViewModel @Inject constructor(
    private val getItemsUseCase: GetItemsUseCase,
    private val deleteItemUseCase: DeleteItemUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<MasterState>(MasterState.Loading)
    val state: StateFlow<MasterState> = _state.asStateFlow()

    private val _items = MutableStateFlow<List<Item>>(emptyList())
    val items: StateFlow<List<Item>> = _items.asStateFlow()

    init {
        loadItems()
    }

    fun sendIntent(intent: MasterIntent) {
        when (intent) {
            is MasterIntent.FetchItems -> loadItems()
            is MasterIntent.DeleteItem -> deleteItem(intent.item)
            is MasterIntent.SelectItem -> { /* Handle in navigation */ }
        }
    }

    private fun loadItems() {
        viewModelScope.launch {
            _state.value = MasterState.Loading
            try {
                val fetchedItems = getItemsUseCase()
                _state.value = MasterState.Success(fetchedItems)
                _items.value = fetchedItems
            } catch (e: Exception) {
                _state.value = MasterState.Error("Failed to load items")
            }
        }
    }

    private fun deleteItem(item: Item) {
        viewModelScope.launch {
            try {
                deleteItemUseCase(item)
                _items.value = _items.value.toMutableList().also { it.remove(item) }
                _state.value = MasterState.Success(_items.value)
            } catch (e: Exception) {
                _state.value = MasterState.Error("Failed to delete item")
            }
        }
    }

    fun findItemByTitle(title: String): Item? {
        return _items.value.find { it.mediaTitleCustom == title }
    }
}