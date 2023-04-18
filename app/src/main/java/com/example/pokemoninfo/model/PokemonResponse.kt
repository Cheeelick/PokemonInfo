package com.example.pokemoninfo.model

data class PokemonResponse(
    val sprites: Other,
    val name: String,
    val types: List<PokemonType>
)