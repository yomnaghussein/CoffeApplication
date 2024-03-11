package com.example.coffeapplication.domain

import com.example.coffeapplication.data.CoffeeListRepoImpl
import com.example.coffeapplication.domain.model.CoffeeItem
import retrofit2.Response

class GetCoffeeListUseCase(private val coffeeListRepo: CoffeeListRepo = CoffeeListRepoImpl()) {

    suspend fun getCoffeeList(): Response<List<CoffeeItem>> {
        return coffeeListRepo.getCoffeeList()
    }

}