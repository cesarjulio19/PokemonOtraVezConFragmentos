package com.turing.alan.pokemonotravezconfragmentos.ui.list


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.turing.alan.pokemonotravezconfragmentos.data.api.PokemonApiModel
import com.turing.alan.pokemonotravezconfragmentos.data.api.PokemonRepository
import com.turing.alan.pokemonotravezconfragmentos.data.model.Pokemon
import com.turing.alan.pokemonotravezconfragmentos.databinding.FragmentPokemonListBinding
import com.turing.alan.pokemonotravezconfragmentos.ui.adapter.PokemonAdapter


class PokemonListFragment : Fragment() {
    private val viewModel:PokemonListViewModel by viewModels()
    //private val viewModel:PokemonListViewModel by activityViewModels()
    private lateinit var binding: FragmentPokemonListBinding
    private val repository = PokemonRepository.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokemonListBinding.inflate(inflater,
            container,
            false

        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.pokemonList
        val adapter = PokemonAdapter(::onShowDetail)
        recyclerView.adapter = adapter

        viewModel.pokemonUi.observe(viewLifecycleOwner) { pokemonList ->

            adapter.submitList(pokemonList)
        }


    }

    private fun onShowDetail(name: String,view:View) {
        val action = PokemonListFragmentDirections
            .actionPokemonListFragmentToPokemonDetailFragment(name)
        view.findNavController().navigate(action)
    }




}