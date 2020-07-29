@file:Suppress("SpellCheckingInspection", "unused")

package `in`.curioustools.architectures.db

import `in`.curioustools.architectures.models.Pokemon
import `in`.curioustools.architectures.utils.Logito
import `in`.curioustools.architectures.utils.threading.AppExecutors
import android.content.Context
import androidx.lifecycle.LiveData
import java.util.concurrent.Callable

class PokemonRepo(ctx: Context) {
    private val dao = PokeDb.getInstance(ctx.applicationContext).notesDbAccessDao
    private val executors: AppExecutors = AppExecutors.getSingletonInstance()
    private val logito = Logito(TAG = "PokemonRepo>>")

    fun insertPokeMons(vararg pokemon: Pokemon) {

        executors.singleThreadExecutorService.execute { dao.insertPokemon(*pokemon) }
    }

    fun deleteAllPokemons() {

        executors.singleThreadExecutorService.execute { dao.deleteAllPokemons() }
    }

    fun getPokemonByIndex(index: String): Pokemon? {
        return executors
            .singleThreadExecutorService
            .submit(Callable { dao.getPokemonByID(index) })
            .get()

    }

    fun getAllPokemonsLive(): LiveData<List<Pokemon>>? {

        return dao.getAllPokemonsLive()
    }

    fun refreshCache(recievedPokemons: List<Pokemon>) {
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


