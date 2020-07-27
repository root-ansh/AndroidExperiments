package `in`.curioustools.architectures.db.test

import `in`.curioustools.architectures.db.PokemonRepo
import `in`.curioustools.architectures.models.Pokemon
import `in`.curioustools.architectures.network.PokemonNetworkRequestService
import `in`.curioustools.architectures.utils.Logito
import `in`.curioustools.architectures.utils.interceptors.InterceptorFactory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.min

class TestActivity : AppCompatActivity() {
    private lateinit var repo: PokemonRepo
    private lateinit var service: PokemonNetworkRequestService
    private  val logito= Logito()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        repo = PokemonRepo(this)
        service = PokemonNetworkRequestService(InterceptorFactory.getAll(this))


    }

    override fun onStart() {
        super.onStart()

        startLiveDataTest()
        startNetworkRequest()


    }

    private fun startLiveDataTest() {
        val livePokemons: LiveData<List<Pokemon>>? = repo.getAllPokemonsLive()
        val observer: Observer<List<Pokemon?>?> = Observer { list ->

            if (list != null) {
                logito.e( "startLiveDataTest: list size= ${list.size}")
                val size = min(6, list.size)

                for (i in 0 until size) {
                    logito.e( "startLiveDataTest: ${list[i]}")
                }
            } else {
                logito.e( "startLiveDataTest: livedata recieved a null list")
            }

        }

        livePokemons?.observe(this, observer)

    }

    private fun startNetworkRequest() {
        val callback = object : Callback<List<Pokemon>> {
            override fun onFailure(call: Call<List<Pokemon>>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<List<Pokemon>>, response: Response<List<Pokemon>>) {
                logito.e( "onResponse: response size=${(response.body() ?: listOf()).size}")
                startDbTest(response.body())
            }
        }
        service.makeAsyncRequest(callback)


    }

    private fun startDbTest(nullableListOfPokemons: List<Pokemon>?) {
        logito.e("startDbTest: starting db test recieved response: ${nullableListOfPokemons?.size}"
        )

        val pokeList = nullableListOfPokemons ?: listOf()

        Thread {
            logito.e( "startDbTest: thread is running")
            pokeList.forEach {

                Thread.sleep(5)
                runOnUiThread {
                    logito.e( "startDbTest: //=================/insertion test/==============//")
                    repo.insertPokeMons(it)
                }

            }
            Thread.sleep(8000)
            runOnUiThread {
                logito.e( "startDbTest: //=================/deletion test/==============//")
                repo.deleteAllPokemons()
            }

        }.start()

//        Thread {

//
//        }.start()

    }


}