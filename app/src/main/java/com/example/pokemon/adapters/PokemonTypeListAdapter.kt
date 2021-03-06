package com.example.pokemon.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.R
import com.example.pokemon.models.PokemonTypes
import kotlinx.android.synthetic.main.card_type_pokemon.view.*

class PokemonTypeListAdapter(private val pokemonList: List<PokemonTypes>): RecyclerView.Adapter<PokemonTypeListAdapter.SearchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_type_pokemon, parent,false))
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val pokemon = pokemonList[position]
        holder.itemView.tv_pokemon_type_name.text = pokemon.type?.name
    }

    class SearchViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}