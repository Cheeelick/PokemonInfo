package com.example.pokemoninfo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokemoninfo.MainActivity
import com.example.pokemoninfo.PokemonAdapter
import com.example.pokemoninfo.R
import com.example.pokemoninfo.api.ApiInterface
import com.example.pokemoninfo.databinding.FragmentListPokemonBinding
import com.example.pokemoninfo.viewmodels.PassengerViewModel
import com.example.pokemoninfo.viewmodels.PassengerViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private const val TAG = "PokemonListFragment"

class PokemonListFragment: Fragment(R.layout.fragment_list_pokemon) {

    private var _binding: FragmentListPokemonBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: PassengerViewModel
    private val pokemonAdapter by lazy(LazyThreadSafetyMode.NONE) {
        PokemonAdapter(context)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = PassengerViewModelFactory(ApiInterface())
        viewModel = ViewModelProviders.of(this, factory).get(PassengerViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListPokemonBinding.inflate(inflater, container, false)

        binding.recyclerPokemon.apply{
            layoutManager = GridLayoutManager(context,2)
            adapter = pokemonAdapter
        }

        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        lifecycleScope.launch {
            viewModel.passengers.collectLatest { pageData ->
                pokemonAdapter.submitData(pageData)
            }
        }

        binding.recyclerPokemon.adapter = pokemonAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object{
        fun newInstance(): PokemonListFragment{
            return PokemonListFragment()
        }
    }
}