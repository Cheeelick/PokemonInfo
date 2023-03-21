package com.example.pokemoninfo.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class PokemonResponse(
    @Json(name = "forms")
    val results: List<Pokemon>,

    @Json(name = "sprites")
    val sprites: Other
)