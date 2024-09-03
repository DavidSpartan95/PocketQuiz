package com.davidspartan.pocketquiz.model.api

data class SimplePokemon(
    val id: Int,
    val name: String,
    val stats: List<Stat>,
    var spriteUrl: String?
)

data class Stat(
    val base_stat: Int?, // Represents the base value of the stat
    val effort: Int, //Represents effort points gained
    val stat: StatX // Contains details about the stat itself
)

data class StatX(
    val name: String, // Name of the stat (e.g., "hp", "attack")
    val url: String // URL for more information about the stat
)



