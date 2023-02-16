package com.example.pokemoninfo.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemoninfo.R
import com.example.pokemoninfo.api.Pokemon
import com.example.pokemoninfo.api.PokemonResponse
import com.example.pokemoninfo.api.RetrofitClient
import retrofit2.Call
import retrofit2.Retrofit

private const val TAG = "PokemonListFragment"

class PokemonListFragment: Fragment() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val responsePokemon = RetrofitClient().getPokemonName()
        responsePokemon.observe(
            this,
            Observer { requestMessage ->
                Log.d(TAG, "${requestMessage}")
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list_pokemon, container, false)

        recyclerView = view.findViewById(R.id.recycler_list)
        recyclerView.layoutManager = LinearLayoutManager(context)


        return view
    }


//    private inner class PokemonAdapter(): RecyclerView.Adapter<PokemonHolder>(){
//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonHolder {
//
//        }
//
//
//        override fun onBindViewHolder(holder: PokemonHolder, position: Int) {
//        }
//
//        override fun getItemCount(): Int {
//        }
//
//    }
//
//
//    private inner class PokemonHolder(): RecyclerView.ViewHolder(){
//
//    }

    companion object{
        fun newInstance(): PokemonListFragment{
            return PokemonListFragment()
        }
    }
}