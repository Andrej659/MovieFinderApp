package org.unizd.rma.markanjevic.objects

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import org.unizd.rma.markanjevic.interfaces.ApiService

object RetrofitClient {

    private const val BASE_URL = "https://www.omdbapi.com/"

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)
}

