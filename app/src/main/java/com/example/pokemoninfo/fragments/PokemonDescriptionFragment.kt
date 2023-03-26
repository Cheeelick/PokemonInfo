package com.example.pokemoninfo.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pokemoninfo.R
import com.example.pokemoninfo.databinding.FragmentListPokemonBinding
import com.example.pokemoninfo.databinding.FragmentPokemonDescriptionBinding

private const val ARG_POKEMON_ID = "pokemon_id"

class PokemonDescriptionFragment : Fragment(R.layout.fragment_pokemon_description) {

    private var _binding: FragmentPokemonDescriptionBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pokemonId: String = arguments?.getSerializable(ARG_POKEMON_ID) as String
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPokemonDescriptionBinding.inflate(inflater, container, false)

        binding.apply { 
            namePokemon.text = arguments?.getSerializable(ARG_POKEMON_ID) as String
        }

        return _binding?.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object{
        fun newInstance(pokemonId: String): PokemonDescriptionFragment{
            val args = Bundle().apply{
                putSerializable(ARG_POKEMON_ID, pokemonId)
            }
            return PokemonDescriptionFragment().apply {
                arguments = args
            }
        }
    }

}