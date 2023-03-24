package com.example.pokemoninfo.model

import com.squareup.moshi.Json

data class UrlPokemonPhotoDto (
    @Json(name = "front_default")
    val urlPhoto: String
)