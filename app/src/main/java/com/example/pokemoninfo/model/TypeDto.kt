package com.example.pokemoninfo.model

import com.squareup.moshi.Json

data class TypeDto(
    @Json(name = "name")
    val typeName: String
)