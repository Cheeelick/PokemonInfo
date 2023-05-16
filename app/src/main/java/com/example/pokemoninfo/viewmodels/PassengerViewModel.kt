 package com.example.pokemoninfo.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.pokemoninfo.api.ApiInterface
import com.example.pokemoninfo.api.PokemonPageSource
import com.example.pokemoninfo.api.PokemonSearchPageSource
import com.example.pokemoninfo.model.PokemonResponse

 class PassengerViewModel(
    private val service: ApiInterface
): ViewModel() {

     var pagingLiveData: LiveData<PagingData<PokemonResponse>>
     var searchPokemon = MutableLiveData("")

     init {
         pagingLiveData = Transformations.switchMap(searchPokemon){ searchName->
            if (searchName.isBlank()){
                getAllPokemon()
            }else{
                getSearchPokemon(searchName)
            }
         }
     }

     private fun getAllPokemon(): LiveData<PagingData<PokemonResponse>> {
         return Pager(config = PagingConfig(pageSize = 10), pagingSourceFactory = {
                 PokemonPageSource(service)
             }).liveData.cachedIn(viewModelScope)
     }

     private fun getSearchPokemon(searchPokemon: String): LiveData<PagingData<PokemonResponse>> {
         return Pager(config = PagingConfig(pageSize = 1), pagingSourceFactory = {
             PokemonSearchPageSource(service, searchPokemon)
         }).liveData.cachedIn(viewModelScope)
     }

     fun setSearchPokemon(query : String) {
         searchPokemon.value = query
     }








 }