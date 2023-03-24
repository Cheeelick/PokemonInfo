package com.example.pokemoninfo.model

import com.example.pokemoninfo.model.PokemonPhotoDto
import com.squareup.moshi.Json

data class OtherDto (
    @Json(name = "other")
    val other: PokemonPhotoDto
)