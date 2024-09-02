package com.davidspartan.pocketquiz.data

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

suspend fun apiCall(id: Int): SimplePokemon? {
    return withContext(Dispatchers.IO) {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://pokeapi.co/api/v2/pokemon/$id")
            .get()
            .build()

        val response = client.newCall(request).execute()
        if (response.isSuccessful) {
            val responseBody = response.body?.string()

            // Use Moshi to parse the full JSON object
            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

            // Parse the JSON to get the main structure as a Map
            val jsonAdapter = moshi.adapter(Map::class.java)
            val jsonMap = jsonAdapter.fromJson(responseBody)

            // Extract the sprite URL from the JSON structure
            val spriteUrl = ((jsonMap?.get("sprites") as Map<*, *>)["versions"] as Map<*, *>)["generation-vi"]
                ?.let { it as Map<*, *> }
                ?.get("x-y")
                ?.let { it as Map<*, *> }
                ?.get("front_default") as String
            println(spriteUrl)
            // Now parse the rest of the SimplePokemon object
            val simplePokemonAdapter = moshi.adapter(SimplePokemon::class.java)
            val simplePokemon = simplePokemonAdapter.fromJson(responseBody)
            println(simplePokemon?.spriteUrl)
            // Return the SimplePokemon object with the sprite URL set
            simplePokemon.let {
                simplePokemon?.spriteUrl = spriteUrl
            }
            simplePokemon
        } else {
            println("Error: ${response.code}")
            null // Return null on error
        }
    }
}
