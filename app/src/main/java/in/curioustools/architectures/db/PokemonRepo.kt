@file:Suppress("SpellCheckingInspection", "unused")

package `in`.curioustools.architectures.db

import `in`.curioustools.architectures.models.Pokemon
import `in`.curioustools.architectures.utils.Logito
import `in`.curioustools.architectures.utils.threading.AppExecutors
import android.content.Context
import androidx.lifecycle.LiveData

class PokemonRepo (ctx:Context){
    private val dao = PokeDb.getInstance(ctx.applicationContext).notesDbAccessDao
    private val executors: AppExecutors = AppExecutors.getSingletonInstance()
    private val logito = Logito(TAG= "PokemonRepo>>")

     fun insertPokeMons(vararg pokemon: Pokemon){
        if(dao==null){
            logito.e("dao is null")
            return
        }
        executors.singleThreadExecutorService.execute { dao.insertPokemon(*pokemon) }
    }

     fun deleteAllPokemons(){
        if(dao==null){
            logito.e("dao is null")
            return
        }
        executors.singleThreadExecutorService.execute { dao.deleteAllPokemons() }
    }

      fun getPokemonByIndex(index:String):Pokemon?{
        if(dao==null) return  null

        return executors.singleThreadExecutorService.submit {
            dao.getPokemonByID(index) }.get() as Pokemon?

    }

     fun getAllPokemonsLive(): LiveData<List<Pokemon>>?{
        if(dao==null){
            logito.e("dao is null")
            return null
        }
        return dao.getAllPokemonsLive()
    }

    fun refreshCache(recievedPokemons: List<Pokemon>) {

        if(dao==null){
            logito.e("dao is null")
            return
        }
        executors.singleThreadExecutorService.execute {
            dao.deleteAllPokemons()
            dao.insertPokemon(*recievedPokemons.toTypedArray())

        }


    }


}


















































































/*
* @Query("SELECT * FROM table_pokemon_full WHERE id = :mainID")
fun getPokemonByID(mainID:String):Pokemon


@Query("SELECT * FROM table_pokemon_full")
fun getAllPokemonsLive():LiveData<List<Pokemon>>

*
* */


