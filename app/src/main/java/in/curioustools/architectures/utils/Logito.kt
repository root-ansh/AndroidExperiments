@file:Suppress("SpellCheckingInspection", "MemberVisibilityCanBePrivate", "unused")

package `in`.curioustools.architectures.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import java.util.*
import kotlin.math.min


private val defContext = null
private const val defTAG = "Logito"
private const val defIsProd = false
private const val defIsJVMOnly = false

class Logito(
    var TAG: String = defTAG,
    var context: Context? = defContext,
    var isProd: Boolean = defIsProd,
    var isJVMOnly: Boolean = defIsJVMOnly
) {

    //===================================================================
    fun e(msg: String?) {
        if (!isProd) {
            Log.e(TAG, "$msg")
        }
    }

    fun e(vararg msg: Any?) {
        if (!isProd) {
            Log.e(TAG, "e: ARGUEMENTS RECIEVED=")
            msg.forEach {
                Log.e(TAG, "e: $ arg= $it")
            }
        }
    }

    fun e(funcName: String, msg: String?) {
        if (!isProd) {
            Log.e(TAG, ">>>>$funcName: $msg")
        }
    }
    //===================================================================


    fun i(list: List<Any>?, entryCount: Int) {
        val date= Date()
        Log.i(TAG, "//===========================================// ")

        if (list == null) {
            Log.i(TAG, "i: Passed list is null")
        }
        else {
            for (i in 0..min(entryCount, list.size-1)) {
                Log.i(TAG, "entry $i : ${list[i]} ")
            }
        }

        Log.i(TAG, "//===========================================// ")


    }


    //===================================================================


    fun toast(msg: String?) {
        if (context == null) {
            Log.e(TAG, "toast: CONTEXT IS NULL, CANNOT MAKE TOAST")
            return
        }
        toast(context, msg)
    }

    fun toast(ctx: Context?, msg: String?) {
        if (context == null) {
            Log.e(TAG, "toast: CONTEXT IS NULL, CANNOT MAKE TOAST")
            return
        }
        Toast.makeText(ctx, "$msg", Toast.LENGTH_SHORT).show()

    }


}

fun i(list: List<Any>?, entryCount: Int) {


    if (list == null) {
        println("i: Passed list is null")
    } else {
        for (i in 0..min(entryCount, list.size-1)) {
           println( "entry $i : ${list[i]} ")
        }
    }

}


fun main() {
   print(Date())
}


// TODO: 07-07-2020 add a multiple msg logger so that multiple entries can be logged
//  in multiple lines
// TODO: 07-07-2020  add arr/list/.. etc container loggers for logging multiple values in
//  multiple lines
