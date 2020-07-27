package `in`.curioustools.architectures.utils.interceptors

import `in`.curioustools.architectures.utils.Logito
import `in`.curioustools.architectures.utils.network.NetworkCheck
import android.content.Context
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException


// TODO: 27-07-2020  make it such way that log interceptor and internet interceptor can only be created via factory


//add    implementation "com.squareup.okhttp3:logging-interceptor:4.8.0" in app.gradle
class LogInterceptor constructor(private val severity: HttpLoggingInterceptor.Level ) {
    fun create(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level =severity
        return httpLoggingInterceptor
    }
}

class InternetAvailabilityInterceptor constructor(private val ctx: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {

        if (!NetworkCheck.isNetworkAvailable(ctx)) {
            throw IOException("INTERNET NOT AVAILABLE")
        } else {
            return chain.proceed(chain.request())
        }
    }

}


class InterceptorFactory {

    companion object{

        /*returns all interceptors with default config*/
        fun getAll(ctx: Context):List<Interceptor>{
            return listOf(
                //InternetAvailabilityInterceptor(ctx),
                LogInterceptor(HttpLoggingInterceptor.Level.BODY).create()
            )


        }

    }



}