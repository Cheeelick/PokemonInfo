package com.example.pokemoninfo.api

import androidx.annotation.IntRange
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("/api/v2/pokemon/{pages}")
    suspend fun getAllName(
        @Path("pages") pages: Int,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): PokemonResponse

    companion object {

        private const val BASE_URL = "https://pokeapi.co/"

        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()

        operator fun invoke(): ApiInterface = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(ApiInterface::class.java)
    }
}
