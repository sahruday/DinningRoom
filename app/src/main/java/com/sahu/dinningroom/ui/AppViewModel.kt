package com.sahu.dinningroom.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sahu.dinningroom.data.Repository
import com.sahu.dinningroom.dataHolders.MenuItem
import com.sahu.dinningroom.dataHolders.Order
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val availableItems: Flow<List<MenuItem>> = getMenuItems()
    var selectedCategory: MutableLiveData<Int> = MutableLiveData(0)
    var searchString: MutableLiveData<String> = MutableLiveData("")

    fun getMenuItems(): Flow<List<MenuItem>> = repository.getMenuData()

    fun getOrders(): Flow<List<Order>> = repository.getData()

    fun placeAnOrder() = viewModelScope.launch { repository.postMockOrder() }

    fun updateOrderState(orderId: Int, updateStatus: Int) = viewModelScope.launch { repository.updateOrderState(orderId, updateStatus) }

    fun searchInIngredients(searchText: String) = viewModelScope.launch { repository.searchForIngredients(searchText) }

    fun clearOrders() = viewModelScope.launch { repository.clearOrders() }

}