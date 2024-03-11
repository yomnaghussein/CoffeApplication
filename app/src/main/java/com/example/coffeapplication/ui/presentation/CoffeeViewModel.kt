package com.example.coffeapplication.ui.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeapplication.domain.GetCoffeeListUseCase
import com.example.coffeapplication.domain.model.CoffeeItem
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class CoffeeViewModel(private val coffeeListUseCase: GetCoffeeListUseCase = GetCoffeeListUseCase()) :
    ViewModel() {

    private val _coffeeListSharedFlow = MutableSharedFlow<MutableList<CoffeeItem>?>(replay = 1)
    val coffeeListSharedFlow = _coffeeListSharedFlow

    private val _selectedCoffeeItem = MutableSharedFlow<CoffeeItem?>()
    val selectedCoffeeItem = _selectedCoffeeItem


    fun getListOfCoffee() {
        viewModelScope.launch {
            val response = coffeeListUseCase.getCoffeeList()
            if (response.isSuccessful)
                _coffeeListSharedFlow.emit(response.body() as MutableList<CoffeeItem>)
            else
                _coffeeListSharedFlow.emit(null)
        }

    }

    suspend fun getCoffeeItem(coffeeId: Int): CoffeeItem {
        _coffeeListSharedFlow.collect {
            val selectedCoffeeItem = it?.firstOrNull { it.id.toInt() == coffeeId }

            _selectedCoffeeItem.emit(selectedCoffeeItem)
        }


    }

}