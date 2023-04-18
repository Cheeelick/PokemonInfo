package com.example.pokemoninfo.model

import com.squareup.moshi.Json

data class PokemonTypeDto (
    @Json(name = "type")
    val type: TypeDto
)