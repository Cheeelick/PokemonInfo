package com.example.pokemoninfo.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokemoninfo.PokemonAdapter
import com.example.pokemoninfo.R
import com.example.pokemoninfo.api.ApiInterface
import com.example.pokemoninfo.databinding.FragmentListPokemonBinding
import com.example.pokemoninfo.model.PokemonResponse
import com.example.pokemoninfo.model.PokemonType
import com.example.pokemoninfo.viewmodels.PassengerViewModel
import com.example.pokemoninfo.viewmodels.PassengerViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private const val TAG = "PokemonListFragment"

class PokemonListFragment: Fragment(R.layout.fragment_list_pokemon) {

    private var callbacks: Callbacks? = null
    private lateinit var viewModel: PassengerViewModel
    private var _binding: FragmentListPokemonBinding? = null
    private val binding get() = _binding!!
    private val pokemonAdapter by lazy(LazyThreadSafetyMode.NONE) {
        PokemonAdapter(context, callbacks)
    }

    interface Callbacks{
        fun onPokemonSelected(pokemon: PokemonResponse)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = PassengerViewModelFactory(ApiInterface())
        viewModel = ViewModelProviders.of(this, factory).get(PassengerViewModel::class.java)

        lifecycleScope.launch {
            viewModel.passengers.collectLatest { pageData ->
                pokemonAdapter.submitData(pageData)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        ceState: Bundle?
    ): View? {
        _binding = FragmentListPokemonBinding.inflate(inflater, container, false)

        binding.recyclerPokemon.apply{
            layoutManager = GridLayoutManager(context,2)
            adapter = pokemonAdapter
        }


        return _binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    companion object{
        fun newInstance(): PokemonListFragment{
            return PokemonListFragment()
        }
    }
}