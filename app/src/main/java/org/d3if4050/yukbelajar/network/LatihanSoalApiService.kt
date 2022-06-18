package org.d3if4050.yukbelajar.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/Ihsanudin07/api_json/main/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface LatihanSoalApiService {
    @GET("latihansoal.json")
    suspend fun getLatihanSoal(): String
}

object LatihanSoalApi{
    val service: LatihanSoalApiService by lazy {
        retrofit.create(LatihanSoalApiService::class.java)
    }
}