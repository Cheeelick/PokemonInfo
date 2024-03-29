package com.example.pokemoninfo.model

import com.squareup.moshi.Json

data class PokemonResponseDto(

    @Json(name = "sprites")
    val sprites: OtherDto,

    @Json(name = "name")
    val name: String,

    @Json(name = "types")
    val types: List<PokemonTypeDto>
)