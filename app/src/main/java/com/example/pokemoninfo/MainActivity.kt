package com.example.pokemoninfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.pokemoninfo.fragments.PokemonDescriptionFragment
import com.example.pokemoninfo.fragments.PokemonListFragment

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(),
    PokemonListFragment.Callbacks{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (currentFragment == null){
            val fragment = PokemonListFragment.newInstance()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }

    }

    override fun onCrimeSelected(pokemonId: String) {
        val fragment = PokemonDescriptionFragment.newInstance(pokemonId)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}