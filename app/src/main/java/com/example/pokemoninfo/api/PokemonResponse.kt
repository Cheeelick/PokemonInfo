package com.example.pokemoninfo.api

//import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class PokemonResponse(
    @Json(name = "results")
    val results: List<Pokemon>,

    @Json(name="count")
    val count: Int
)