package com.example.pokemoninfo.api

import com.squareup.moshi.Json

data class Other (
    @Json(name = "other")
    val other: PokemonPhoto
)