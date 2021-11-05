package com.example.pokemon.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokemon.R
import com.example.pokemon.models.PokemonDetailResponse
import com.example.pokemon.models.PokemonResult
import com.example.pokemon.models.PokemonStats
import com.example.pokemon.models.PokemonTypes
import kotlinx.android.synthetic.main.card_item_pokemon.view.*
import kotlinx.android.synthetic.main.card_stat_pokemon.view.*
import kotlinx.android.synthetic.main.card_type_pokemon.view.*

class PokemonStatListAdapter(private val pokemonList: List<PokemonStats>): RecyclerView.Adapter<PokemonStatListAdapter.SearchViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_stat_pokemon, parent,false))
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val pokemon = pokemonList[position]
        holder.itemView.tv_pokemon_stat_name.text = pokemon.stat?.name?.replace("-", " ")
        holder.itemView.tv_pokemon_base_stat.text = ": ${pokemon.baseStat.toString()}"
    }

    class SearchViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}