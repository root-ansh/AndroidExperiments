package `in`.curioustools.architectures.utils.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

class NetworkCheck {
    companion object{
        fun isNetworkAvailable(ctx: Context): Boolean {

            val cm = ctx.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE)
                    as ConnectivityManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

                val capabilities = cm.getNetworkCapabilities(cm.activeNetwork) ?: return false
                return when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }

            } else {
                @Suppress("DEPRECATION")
                return cm.activeNetworkInfo?.isConnectedOrConnecting ?: false

            }

        }
    }
}