package com.example.pokemoninfo.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.filter
import androidx.paging.map
import androidx.transition.TransitionInflater
import com.example.pokemoninfo.PokemonAdapter
import com.example.pokemoninfo.R
import com.example.pokemoninfo.api.ApiInterface
import com.example.pokemoninfo.databinding.FragmentListPokemonBinding
import com.example.pokemoninfo.databinding.FragmentPokemonDescriptionBinding
import com.example.pokemoninfo.model.PokemonResponse
import com.example.pokemoninfo.model.PokemonType
import com.example.pokemoninfo.viewmodels.PassengerViewModel
import com.example.pokemoninfo.viewmodels.PassengerViewModelFactory
import com.squareup.picasso.Picasso
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private const val ARGS_POKEMONS = "pokemons"
private const val ARG_POKEMON_TYPE0 = "pokemon_type0"
private const val ARG_POKEMON_TYPE1 = "pokemon_type1"
private const val ARG_POKEMON_PHOTO = "pokemon_photo"
private const val TAG = "PokemonDescriptionFragment"

class PokemonDescriptionFragment : Fragment(R.layout.fragment_pokemon_description) {

    private var _binding: FragmentPokemonDescriptionBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPokemonDescriptionBinding.inflate(inflater, container, false)

        binding.apply {
            val pokemon = arguments?.get(ARGS_POKEMONS) as PokemonResponse
            val pokemonPhoto = pokemon.sprites.other.official_artwork.urlPhoto
            namePokemon.text = pokemon.name
            typePokemon0.text = pokemon.types[0].type.typeName
            if(pokemon.types.size == 2){
                typePokemon1.text = pokemon.types[1].type.typeName
            }

            Picasso.get()
                .load(pokemonPhoto)
                .into(binding.imagePokemon)

        }

        return _binding?.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object{
        fun newInstance(pokemon: PokemonResponse): PokemonDescriptionFragment {
            val args = Bundle().apply {
                putParcelable(ARGS_POKEMONS, pokemon)
            }
            return PokemonDescriptionFragment().apply {
                arguments = args
            }
        }

    }

}