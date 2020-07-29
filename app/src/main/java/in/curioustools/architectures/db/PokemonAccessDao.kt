package `in`.curioustools.architectures.db

import `in`.curioustools.architectures.models.Pokemon
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PokemonAccessDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemon(vararg pokemon: Pokemon)

    @Query("DELETE FROM table_pokemon_full")
    fun deleteAllPokemons()

    @Query("SELECT * FROM table_pokemon_full WHERE index_id LIKE '%' || :indexID || '%'")
    fun getPokemonByID(indexID:String):Pokemon?


    @Query("SELECT * FROM table_pokemon_full")
    fun getAllPokemonsLive():LiveData<List<Pokemon>>

    /*
    //might be useless

    @Delete
    fun deletePokemon(pokemon: Pokemon)

    @Query("SELECT * FROM table_pokemon_full ")
    fun getAllPokemons():List<Pokemon>

    */


//    getbyid:Livedata(?),          getevolutionsbyids : livedata(?)
//    @Query("SELECT * FROM pokemon WHERE id IN(:evolutionIds)")
//    fun getEvolutionsByIds(evolutionIds: List<String>): LiveData<List<Pokemon>>



}