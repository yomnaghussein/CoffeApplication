package com.example.coffeapplication.data

import com.example.coffeapplication.domain.CoffeeListRepo
import com.example.coffeapplication.domain.model.CoffeeItem
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CoffeeListRepoImpl : CoffeeListRepo{

    override suspend fun getCoffeeList(): Response<List<CoffeeItem>> {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .baseUrl("https://api.sampleapis.com")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoffeeApi::class.java)
            .getCoffeeList()
    }

}