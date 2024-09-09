package com.davidspartan.pocketquiz.model.game

import com.davidspartan.pocketquiz.model.api.SimplePokemon

class GameLogic {

    fun generateOptions(correctOption: SimplePokemon):List<String> {
        correctOption.id
        var copyOfPokemonList = pokemonNames.toMutableList()
        copyOfPokemonList.remove(correctOption.name)
        val options = copyOfPokemonList.shuffled().take(3).toMutableList()
        options.add(correctOption.name)
        for (x in options){
            println(options)
        }
        return options.shuffled()

    }
}