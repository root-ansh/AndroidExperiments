@file:Suppress("SpellCheckingInspection")

package `in`.curioustools.architectures.models_old

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

//https://pokeapi.co/api/v2/pokemon?limit=100&offset=200

 data class MainResp(

    @Expose
    @SerializedName("count")
    val postCount: Int,

    @SerializedName("next")
    val nextUrl: String,

    @SerializedName("previous")
    val previousUrl: String,

    @SerializedName("results")
    val pokemonOverViewList: List<PokemonOverView> = listOf()

)
data class PokemonOverView(
    @SerializedName("name") @Expose
    val name: String,

    @SerializedName("url") @Expose
    val detailsUrl: String
)


// when any url is triggered,i.e : https://pokeapi.co/api/v2/pokemon/129/ or https://pokeapi.co/api/v2/pokemon/magikarp
data class PokemonDetails(

    @SerializedName("name")

    val name: String,


    @SerializedName("id")

    val pokedexIndex: Int,


    @SerializedName("sprites")

    val sprites: Sprites,

    @SerializedName("base_experience")

    val baseExperience: Int,


    @SerializedName("height")

    val height: Int,


    @SerializedName("weight")

    val weight: Int

)

data class Sprites(
    @SerializedName("front_default")
    val frontDefault: String?,

    @SerializedName("front_female")
    val frontFemale: String?,

    @SerializedName("front_shiny")
    val frontShiny: String?,

    @SerializedName("front_shiny_female")
    val frontShinyFemale: String?
)


