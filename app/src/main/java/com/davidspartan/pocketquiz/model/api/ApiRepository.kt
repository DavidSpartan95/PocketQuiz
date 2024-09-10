package com.davidspartan.pocketquiz.model.api

class ApiRepository {
    suspend fun getPokemon(id:Int): SimplePokemon{
            return getPokemonService(id)
    }
}