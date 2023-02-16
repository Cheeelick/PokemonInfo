package com.example.pokemoninfo.api

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("/api/v2/pokemon/")
    fun getAllName(): Call<PokemonResponse>
}