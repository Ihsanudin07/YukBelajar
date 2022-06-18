package org.d3if4050.yukbelajar.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if4050.yukbelajar.LatihanSoal
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/Ihsanudin07/api_json/main/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface LatihanSoalApiService {
    @GET("latihansoal.json")
    suspend fun getLatihanSoal(): List<LatihanSoal>
}

object LatihanSoalApi{
    val service: LatihanSoalApiService by lazy {
        retrofit.create(LatihanSoalApiService::class.java)
    }

    fun getLatihanSoalUrl(soal: String) : String{
        return "$BASE_URL$soal.jpg"
    }
}