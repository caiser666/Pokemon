package com.example.pokemon.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokemon.R
import com.example.pokemon.models.PokemonResult
import kotlinx.android.synthetic.main.card_item_pokemon.view.*

class PokemonListAdapter(val pokemonClick: (Int) -> Unit): RecyclerView.Adapter<PokemonListAdapter.SearchViewHolder>() {
    private var pokemonList: List<PokemonResult> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<PokemonResult>){
        pokemonList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_item_pokemon, parent,false))
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val pokemon = pokemonList[position]
        holder.itemView.tv_pokemon_name.text = pokemon.name
        holder.itemView.setOnClickListener { pokemonClick(position + 1) }
        val imgUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${position + 1}.png"
        Glide.with(holder.itemView.context).load(imgUrl).into(holder.itemView.iv_pokemon_image)
    }

    class SearchViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}