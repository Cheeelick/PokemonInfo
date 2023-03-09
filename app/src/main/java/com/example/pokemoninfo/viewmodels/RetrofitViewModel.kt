package com.example.pokemoninfo.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.pokemoninfo.api.Pokemon
import com.example.pokemoninfo.api.RetrofitClient

class RetrofitViewModel: ViewModel() {

    val responseLiveData:LiveData<List<Pokemon>> = RetrofitClient().getPokemonName()

//    val imageLiveData: LiveData

}