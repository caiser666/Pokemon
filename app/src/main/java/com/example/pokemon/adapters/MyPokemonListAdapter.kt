package com.example.pokemon.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokemon.R
import com.example.pokemon.models.PokemonDetailResponse
import com.example.pokemon.utils.MySharedPref
import kotlinx.android.synthetic.main.card_item_my_list_pokemon.view.*
import kotlinx.android.synthetic.main.card_item_pokemon.view.iv_pokemon_image
import kotlinx.android.synthetic.main.card_item_pokemon.view.tv_pokemon_name

class MyPokemonListAdapter(val pokemonClick: (Int) -> Unit): RecyclerView.Adapter<MyPokemonListAdapter.SearchViewHolder>() {
    private lateinit var pokemonList: MutableList<PokemonDetailResponse>

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: MutableList<PokemonDetailResponse>){
        pokemonList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        MySharedPref.init(parent.context)
        return SearchViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_item_my_list_pokemon, parent,false))

    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val pokemon = pokemonList[position]
        holder.itemView.tv_pokemon_name.text = pokemon.name
        holder.itemView.setOnClickListener { pokemonClick(pokemon.id) }
        val imgUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemon.id}.png"
        Glide.with(holder.itemView.context).load(imgUrl).into(holder.itemView.iv_pokemon_image)

        holder.itemView.ibtn_remove_pokemon.setOnClickListener {
            deleteItem(position)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun deleteItem(index: Int) {
        pokemonList.removeAt(index)
        MySharedPref.myPokemonList = pokemonList
        notifyDataSetChanged()
    }

    class SearchViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}