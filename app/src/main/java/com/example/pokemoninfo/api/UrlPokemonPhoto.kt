package com.example.pokemoninfo.api

import com.squareup.moshi.Json

data class UrlPokemonPhoto (
    @Json(name = "front_default")
    val urlPhoto: String
)