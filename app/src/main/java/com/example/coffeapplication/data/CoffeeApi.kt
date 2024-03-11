package com.example.coffeapplication.data

import com.example.coffeapplication.domain.model.CoffeeItem
import retrofit2.Response
import retrofit2.http.GET

interface CoffeeApi {

    @GET("coffee/hot")
    suspend fun getCoffeeList(): Response<List<CoffeeItem>>
}