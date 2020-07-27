package `in`.curioustools.architectures.utils

import `in`.curioustools.architectures.R
import android.content.Context
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import java.util.*

class TempPokemonColorUtil(var context: Context) {

    @ColorInt
    fun getPokemonColor(typeOfPokemon: List<String> = listOf()): Int {
        if(typeOfPokemon.isEmpty()) return  convertColor(android.R.color.black)

        val type = typeOfPokemon[0]
//        val color = when (type?.toLowerCase(Locale.ROOT)) {
//            "grass", "bug" -> R.color.lightTeal
//            "fire" -> R.color.lightRed
//            "water", "fighting", "normal" -> R.color.lightBlue
//            "electric", "psychic" -> R.color.lightYellow
//            "poison", "ghost" -> R.color.lightPurple
//            "ground", "rock" -> R.color.lightBrown
//            "dark" -> R.color.black
//            else -> R.color.lightBlue
//        }
        return convertColor(R.color.colorAccent)
    }

    @ColorInt
    fun convertColor(@ColorRes color: Int): Int {
        return ContextCompat.getColor(context, color)
    }
}