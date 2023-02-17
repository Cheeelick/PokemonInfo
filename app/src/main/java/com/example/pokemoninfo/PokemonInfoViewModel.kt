package com.example.pokemoninfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.pokemoninfo.api.Pokemon
import com.example.pokemoninfo.api.RetrofitClient

class PokemonInfoViewModel: ViewModel() {

    val pokemonItemLiveData: LiveData<List<Pokemon>>

    init{
        pokemonItemLiveData = RetrofitClient().getPokemonName()
    }

}