@file:Suppress("SpellCheckingInspection")

package `in`.curioustools.architectures.utils.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class StringXListConverter{
    private val gsonObj = Gson()
    private val listType: Type = object : TypeToken<List<String>>() {}.type

    @TypeConverter
    fun stringToListOfStrings(json: String?): List<String> {
        return gsonObj.fromJson(json, listType)
    }

    @TypeConverter
    fun listOfStringsToSingleString(list: List<String?>?): String {
        return gsonObj.toJson(list, listType)
    }

}

fun main() {
    println(
        StringXListConverter()
            .listOfStringsToSingleString(listOf("hi","hey")))
    println(StringXListConverter().listOfStringsToSingleString(listOf()))
    println(StringXListConverter().listOfStringsToSingleString(null))
    println(StringXListConverter().listOfStringsToSingleString(listOf("")))
    println(
        StringXListConverter()
            .listOfStringsToSingleString(listOf("",null)))
    println(
        StringXListConverter()
            .listOfStringsToSingleString(listOf("",null,"hi","hey")))

    println(StringXListConverter().stringToListOfStrings("""["hi","hey"]"""))
    println(StringXListConverter().stringToListOfStrings("""[]"""))
    println(StringXListConverter().stringToListOfStrings("""null"""))
    println(StringXListConverter().stringToListOfStrings("""[""]"""))
    println(StringXListConverter().stringToListOfStrings("""["",null]"""))
    println(StringXListConverter().stringToListOfStrings("""["",null,"hi","hey"]"""))





}