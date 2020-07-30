@file:Suppress("unused", "SpellCheckingInspection")

package `in`.curioustools.architectures.ui.details

import `in`.curioustools.architectures.models.Pokemon


data class UiPokemonTemp constructor(
    var key: String?,
    var indexID: String?,
    var name: String?,
    var imageUrl: String?,
    var description1: String?,
    var evolutionsIDs: List<String>?,
    var categoriesSelf: List<String>?,
    var categoriesWeakness: List<String>,
    var baseExp: String?,
    var height: String?,
    var weight: String?,
    var hp: Int?,
    var attack: Int,
    var defense: Int,
    var speed: Int?
) {


    constructor(pokemon: Pokemon) : this(
        key = pokemon.key,
        indexID = pokemon.indexID,
        name = pokemon.name,
        imageUrl = pokemon.imageUrl,
        description1 = pokemon.description1,
        evolutionsIDs = pokemon.evolutionsIDs,
        categoriesSelf = pokemon.categoriesSelf,
        categoriesWeakness = pokemon.categoriesWeakness,
        baseExp = pokemon.baseExp,
        height = pokemon.height,
        weight = pokemon.weight,
        hp = pokemon.hp,
        attack = pokemon.attack,
        defense = pokemon.defense,
        speed = pokemon.speed
    )

    override fun toString(): String {
        return """
                 Pokemon(
                        id='$key',
                        indexID='$indexID',
                        name='$name',
                        imageUrl='$imageUrl',
                        description1='$description1',
                        evolutionsIDs=$evolutionsIDs,
                        height='$height',
                        weight='$weight',
                        categoriesSelf=$categoriesSelf,
                        categoriesWeakness=$categoriesWeakness,
                        hp=$hp,
                        attack=$attack,
                        defense=$defense,
                        speed=$speed,
                        baseExp='$baseExp'
                    )
                """
    }


    @Suppress("SENSELESS_COMPARISON")
    private fun testForNull(): String {
        if (key == null) return "NULL ID"
        if (indexID == null) return "NULL index id"
        if (name == null) return "NULL NAME"
        if (imageUrl == null) return "NULL image"
        if (description1 == null) return "NULL description"
        if (evolutionsIDs == null) return "NULL evolutionsIDs"
        evolutionsIDs?.forEach {
            if (it == null) return "Null evolution id in evolution id list"
        }

        if (height == null) return "NULL height"
        if (weight == null) return "NULL weight"
        if (categoriesSelf == null) return "NULL categoriesSelf"
        categoriesSelf?.forEach {
            if (it == null) {
                return "null string in categories self"
            }

        }

        if (categoriesWeakness == null) return "NULL categoriesWeakness"
        categoriesWeakness?.forEach {
            if (it == null) {
                return "null string in categoriesWeakness"
            }

        }

        if (hp == null) return "NULL hp"
        if (attack == null) return "NULL attack"
        if (defense == null) return "NULL defense"
        if (speed == null) return "NULL speed"
        if (baseExp == null) return "NULL baseExp"

        return "fine"

    }

    companion object {
        fun getDefaultUiPokemon() = UiPokemonTemp(
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            emptyList(),
            null,
            null,
            null,
            null,
            0,
            0,
            null
        )

        const val maxHp = 300
        const val maxAttack = 300
        const val maxDefense = 300
        const val maxSpeed = 300
        const val maxExp = 1000

    }


}
