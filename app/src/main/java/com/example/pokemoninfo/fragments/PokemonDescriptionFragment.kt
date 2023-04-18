package com.example.pokemoninfo.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.transition.TransitionInflater
import com.example.pokemoninfo.R
import com.example.pokemoninfo.databinding.FragmentListPokemonBinding
import com.example.pokemoninfo.databinding.FragmentPokemonDescriptionBinding
import com.example.pokemoninfo.model.PokemonType
import com.squareup.picasso.Picasso

private const val ARG_POKEMON_ID = "pokemon_id"
private const val ARG_POKEMON_TYPE0 = "pokemon_type0"
private const val ARG_POKEMON_TYPE1 = "pokemon_type1"
private const val ARG_POKEMON_PHOTO = "pokemon_photo"



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
            val pokemonPhoto = arguments?.getSerializable(ARG_POKEMON_PHOTO) as String
            namePokemon.text = arguments?.getSerializable(ARG_POKEMON_ID) as String
            typePokemon0.text = arguments?.getSerializable(ARG_POKEMON_TYPE0) as String?
            typePokemon1.text = arguments?.getSerializable(ARG_POKEMON_TYPE1) as String?

            Picasso.get()
                .load(pokemonPhoto)
                .into(binding.imagePokemon)



        }

        return _binding?.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object{
        fun newInstance(pokemonNameId: String, photo: String, type: List<PokemonType>): PokemonDescriptionFragment{
            val args = Bundle().apply{
                putSerializable(ARG_POKEMON_ID, pokemonNameId)
                putSerializable(ARG_POKEMON_TYPE0, type[0].type.typeName)
                if (type.size == 2){
                    putSerializable(ARG_POKEMON_TYPE1, type[1].type.typeName)
                }
                putSerializable(ARG_POKEMON_PHOTO, photo)

            }
            return PokemonDescriptionFragment().apply {
                arguments = args
            }
        }
    }

}