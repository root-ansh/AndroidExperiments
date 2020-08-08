@file:Suppress("SpellCheckingInspection", "unused")

package `in`.curioustools.architectures.network

import `in`.curioustools.architectures.models.Pokemon
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit


//NetworkConstants.must add a '/' to the default url
private const val FULL_URL =
    "https://gist.githubusercontent.com/chaostools/c95295a53be03f07be0ab3dcaaeab63b/raw/d8f17acfc1151fd7cfcef6eb21a1400c49160050/api.json/"

interface PokemonApiClient {
    @GET(".")// if function has no params and request is to be made only using base url, add '.' here
    fun getPokemonList(): Call<List<Pokemon>>

}

class PokemonNetworkRequestService(interceptors: List<Interceptor> = listOf()) {
    private  var clientInstance: PokemonApiClient
    init {

         val okHttpClientBuilder = OkHttpClient().newBuilder()
         interceptors.forEach { okHttpClientBuilder.addInterceptor(it) }

        
         val retrofitBuilder = Retrofit.Builder()
         val responseConverter = GsonConverterFactory.create(Gson().newBuilder().create())

         val okHttpClient = okHttpClientBuilder.build()

        val retrofitInstance= retrofitBuilder
                .baseUrl(FULL_URL)
                .client(okHttpClient)
                .addConverterFactory(responseConverter).build()

        clientInstance = retrofitInstance.create(PokemonApiClient::class.java)
    }

    fun makeAsyncRequest(onResponse: Callback<List<Pokemon>>) {
        val call = clientInstance.getPokemonList()
        call.enqueue(onResponse)
    }

    fun makeSyncRequest(): List<Pokemon> {
        var result = listOf<Pokemon>()
        val response: Response<List<Pokemon>?>? = clientInstance.getPokemonList().execute()

        return if (response != null) {
            result = response.body() ?: listOf()
            result
        } else {

            result
        }
    }

}


private fun main() {

//    val loggingInterceptor = LogInterceptor(HttpLoggingInterceptor.Level.BODY).create()
//    val networkInterceptor = InternetAvailabilityInterceptor(context)
//    val interceptors = listOf(loggingInterceptor, networkInterceptor)

//    `in`.curioustools.architectures.network.testAsyncRequest()
    testSyncRequest()
}

private fun testAsyncRequest(interceptors: List<Interceptor> = listOf()) {
    val latch = CountDownLatch(1)
    val callback = object : Callback<List<Pokemon>> {
        override fun onFailure(call: Call<List<Pokemon>>, t: Throwable) {
            println("ASYNC :FAIL")
            println("${t}")
            latch.countDown()
        }

        override fun onResponse(call: Call<List<Pokemon>>, response: Response<List<Pokemon>>) {
            println("ASYNC :Success")
            val pokemons: List<Pokemon> = response.body() ?: listOf()
            pokemons.forEach {
                println(it)
                println("-----------------------------------------------------------------------")
            }
            latch.countDown()
        }
    }


    PokemonNetworkRequestService(interceptors).makeAsyncRequest(callback)

    try {
        latch.await()
    } catch (e: Exception) {
        e.printStackTrace()
    }

}

private fun testSyncRequest() {
    val result = PokemonNetworkRequestService().makeSyncRequest()
    result.forEach {
        println(it)
        println("=============")
    }
}

