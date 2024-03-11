package com.example.coffeapplication.domain

import com.example.coffeapplication.domain.model.CoffeeItem
import retrofit2.Response

interface CoffeeListRepo {

    suspend fun getCoffeeList() : Response<List<CoffeeItem>>
}