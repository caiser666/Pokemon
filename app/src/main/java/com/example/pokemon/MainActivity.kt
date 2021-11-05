package com.example.pokemon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemon.adapters.PokemonListAdapter
import com.example.pokemon.viewmodels.PokemonListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: PokemonListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(PokemonListViewModel::class.java)

        initUI()
    }

    private fun initUI() {
        rv_pokemon.layoutManager = LinearLayoutManager(this)
        rv_pokemon.adapter = PokemonListAdapter {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("id", it)
            startActivity(intent)
        }

        viewModel.getPokemonList()

        viewModel.pokemonList.observe(this, { list ->
            (rv_pokemon.adapter as PokemonListAdapter).setData(list)
        })

        fab_my_pokemon_list.setOnClickListener {
            val intent = Intent(this, MyListActivity::class.java)
            startActivity(intent)
        }
    }
}