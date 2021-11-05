package com.example.pokemon.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PokemonDetailResponse(
    @Expose @SerializedName("id") val id: Int,
    @Expose @SerializedName("name") val name: String,
    @Expose @SerializedName("weight") val weight: Int,
    @Expose @SerializedName("height") val height: Int,
    @Expose @SerializedName("stats") val stats: List<PokemonStats>,
    @Expose @SerializedName("types") val types: List<PokemonTypes>,
    @Expose @SerializedName("sprites") val sprites: PokemonSprites,
)

data class PokemonSprites(
    @Expose @SerializedName("front_default") val frontDefault: String?,
    @Expose @SerializedName("front_shiny") val frontShiny: String?,
    @Expose @SerializedName("back_default") val backDefault: String?,
    @Expose @SerializedName("back_shiny") val backShiny: String?
)

data class PokemonStats(
    @Expose @SerializedName("base_stat") val baseStat: Int?,
    @Expose @SerializedName("stat") val stat: PokemonStat?
)

data class PokemonStat(
    @Expose @SerializedName("name") val name: String?,
    @Expose @SerializedName("stat") val url: String?
)

data class PokemonTypes(
    @Expose @SerializedName("slot") val slot: Int?,
    @Expose @SerializedName("type") val type: PokemonType?
)

data class PokemonType(
    @Expose @SerializedName("name") val name: String?,
    @Expose @SerializedName("stat") val url: String?
)