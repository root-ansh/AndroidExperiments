@file:Suppress("unused", "SpellCheckingInspection")

package `in`.curioustools.architectures.models

import `in`.curioustools.architectures.db.DbConstants
import `in`.curioustools.architectures.utils.room.UniqueStringGenerator
import android.os.Parcelable
import androidx.room.*
import com.google.gson.annotations.SerializedName
import androidx.room.ColumnInfo
import androidx.room.Ignore
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = DbConstants.TABLE_NAME) //@TypeConverter(StringListConverter::class)
data class Pokemon constructor(
    @SerializedName("this_arg_is_not_in_response") @PrimaryKey
    var key: String = UniqueStringGenerator.getUniqueStringID(16),

    @SerializedName("id")
    @ColumnInfo(name = "index_id")
    var indexID: String = "#1",

    @SerializedName("name")
    @ColumnInfo(name = "name")
    var name: String = "pokemonName",

    @SerializedName("imageurl")
    @ColumnInfo(name = "image_url")
    var imageUrl: String = "error",

    @SerializedName("xdescription")
    @ColumnInfo(name = "decs_main")
    var description1: String = "error",

//    @SerializedName("ydescription")
//    @Ignore
//    var descriptionFallBack: String = " This is the fallback description of Pokemon",

    @SerializedName("evolutions")
    @ColumnInfo(name = "list_evolution_ids")
    var evolutionsIDs: List<String> = listOf(),

    @SerializedName("typeofpokemon")
    @ColumnInfo(name = "list_cat_self")
    var categoriesSelf: List<String> = listOf(),

    @SerializedName("weaknesses")
    @ColumnInfo(name = "list_cat_weakness")
    var categoriesWeakness: List<String> = listOf(),

    @SerializedName("base_exp")
    @ColumnInfo(name = "base_exp")
    var baseExp: String = "default_base_exp",

    var height: String = "0",

    var weight: String = "0",

    var hp: Int = 0,

    var attack: Int = 0,

    var defense: Int = 0,

    var speed: Int = 0


) : Parcelable {

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
        evolutionsIDs.forEach {
            if (it == null) return "Null evolution id in evolution id list"
        }

        if (height == null) return "NULL height"
        if (weight == null) return "NULL weight"
        if (categoriesSelf == null) return "NULL categoriesSelf"
        categoriesSelf.forEach {
            if (it == null) {
                return "null string in categories self"
            }

        }

        if (categoriesWeakness == null) return "NULL categoriesWeakness"
        categoriesWeakness.forEach {
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
        fun getDefaultPokemon()= Pokemon()

        const val maxHp = 300
        const val maxAttack = 300
        const val maxDefense = 300
        const val maxSpeed = 300
        const val maxExp = 1000

    }


}
