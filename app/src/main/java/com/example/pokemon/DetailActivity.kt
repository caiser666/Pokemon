package com.example.pokemon

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.pokemon.Constants.MY_POKEMON_LIST
import com.example.pokemon.Constants.MY_SHARED_PREF
import com.example.pokemon.adapters.MyPokemonListAdapter
import com.example.pokemon.adapters.PokemonStatListAdapter
import com.example.pokemon.adapters.PokemonTypeListAdapter
import com.example.pokemon.models.PokemonDetailResponse
import com.example.pokemon.utils.MySharedPref
import com.example.pokemon.viewmodels.PokemonDetailViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.log

class DetailActivity : AppCompatActivity() {
    private lateinit var viewModel: PokemonDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        MySharedPref.init(this)

        viewModel = ViewModelProvider(this).get(PokemonDetailViewModel::class.java)
        initUI()
    }

    @SuppressLint("SetTextI18n")
    private fun initUI() {
        val id = intent.extras?.get("id") as Int

        viewModel.getPokemonDetail(id)

        Log.d("TAG", "initUI: ${MySharedPref.myPokemonList}")

        fab_pokemon_save.isEnabled = MySharedPref.myPokemonList?.filter { data -> data.id == id }.isNullOrEmpty()

        rv_pokemon_stats.layoutManager = LinearLayoutManager(this)
        rv_pokemon_types.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        viewModel.pokemonDetail.observe(this, Observer { pokemon ->
            tv_pokemon_types.text = "Types:"
            tv_pokemon_physically.text = "Physically:"
            tv_pokemon_name.text = pokemon.name
            tv_pokemon_height.text = "Height: ${pokemon.height} m";
            tv_pokemon_weight.text = "Weight: ${pokemon.weight} kg";
            rv_pokemon_stats.adapter = PokemonStatListAdapter(pokemon.stats)
            rv_pokemon_types.adapter = PokemonTypeListAdapter(pokemon.types)

            Glide.with(this).load(pokemon.sprites.frontDefault).into(iv_pokemon_image)

            fab_pokemon_save.setOnClickListener {
                savePokemon(id, pokemon);
            }
        })


    }

    private fun savePokemon(id: Int, pokemon: PokemonDetailResponse) {
        val pokemonList = MySharedPref.myPokemonList

        when {
            pokemonList?.filter { data -> data.id == id && data.name == pokemon.name }.isNullOrEmpty() -> {
                pokemonList?.add(pokemon)
            }
        }

        Log.d("TAG", "savePokemon: $pokemonList")

        MySharedPref.myPokemonList = pokemonList

        val intent = Intent(this, MyListActivity::class.java)
        startActivity(intent)
        finish()
    }
}