package com.example.pokemoninfo.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokemoninfo.api.ApiInterface

class PassengerViewModelFactory(
    private val service: ApiInterface
): ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PassengerViewModel(service) as T
    }
}