package com.turing.alan.pokemonotravezconfragmentos.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.turing.alan.pokemonotravezconfragmentos.data.model.Pokemon
import com.turing.alan.pokemonotravezconfragmentos.databinding.PokemonItemBinding

class PokemonAdapter(private val onShowDetail:(n:String,v: View)->Unit)
    : RecyclerView.Adapter<PokemonAdapter.PokemonItemViewHolder>() {

    private var pokemonList: List<Pokemon> = emptyList()

    inner class PokemonItemViewHolder(private val binding: PokemonItemBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(p: Pokemon){
            binding.nameText.text =p.name
            binding.idp.text = p.id.toString()

           binding.nameText.setOnClickListener{
               onShowDetail(p.name,binding.root)
           }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonItemViewHolder {
        val binding = PokemonItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return PokemonItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonItemViewHolder, position: Int) {
        val pokemon = pokemonList[position]
        holder.bind(pokemon)
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    fun submitList(list: List<Pokemon>) {
        pokemonList = list
        notifyDataSetChanged()
    }



}