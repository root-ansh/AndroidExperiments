package `in`.curioustools.architectures.models_old

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class MyPokemonDbModal(
    val id :String = getUniqueStringID(16),
    val name:String,
    val pokeIndex :Int,
    val imageUrl :String,
    val baseExperience :Int,
    val height:Int,
    val weight :Int

):Parcelable{
    companion object{
         fun getUniqueStringID(length:Int):String{
            val l =if(length>30) 30 else length
            return UUID
                .randomUUID()
                .toString()
                .replace("-","")
                .substring(0,l)

        }
    }
}


fun main() {
    println(MyPokemonDbModal.getUniqueStringID(8))
    println(MyPokemonDbModal.getUniqueStringID(16))
    println(MyPokemonDbModal.getUniqueStringID(24))
    println(MyPokemonDbModal.getUniqueStringID(32))
    println(MyPokemonDbModal.getUniqueStringID(40))

}