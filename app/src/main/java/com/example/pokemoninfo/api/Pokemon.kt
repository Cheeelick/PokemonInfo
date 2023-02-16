package com.example.pokemoninfo.api

//import com.google.gson.annotations.SerializedName
//import com.squareup.moshi.Json
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class Pokemon (
    @Json(name = "name")
    val namePokemon: String?
)
