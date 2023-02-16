package com.example.pokemoninfo.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pokemoninfo.fragments.PokemonListFragment
//import com.squareup.moshi.JsonAdapter
//import com.squareup.moshi.KotlinJsonAdapterFactory
//import com.squareup.moshi.Moshi
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
//import retrofit2.converter.moshi.MoshiConverterFactory

private const val TAG = "RetrofitClient"
private const val BASE_URL = "https://pokeapi.co/"


class RetrofitClient {

    private var retrofitApi: ApiInterface

    init{
//        val moshi = Moshi.Builder()
//            .addLast(KotlinJsonAdapterFactory())
//            .build()

        val retrofit:Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofitApi = retrofit.create(ApiInterface::class.java)
    }

    fun getPokemonName(): LiveData<List<Pokemon>>{
        val responseLiveData: MutableLiveData<List<Pokemon>> = MutableLiveData()
        val pokemonRequest = retrofitApi.getAllName()

        pokemonRequest.enqueue(object: Callback<PokemonResponse>{
            override fun onResponse(
                call: Call<PokemonResponse>,
                response: Response<PokemonResponse>
            ) {
                val pokemonResponse: PokemonResponse = response.body()!!
                val result: MutableList<Pokemon> = mutableListOf()
                for (i in pokemonResponse.results!!){
                    result += i
                }
                responseLiveData.value = result
            }

            override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                Log.e(TAG, "${t.message}")
            }

        })
        return responseLiveData
    }






}