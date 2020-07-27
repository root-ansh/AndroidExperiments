@file:Suppress("SpellCheckingInspection", "unused")

package `in`.curioustools.architectures.ui.dashboard

import `in`.curioustools.architectures.db.PokemonRepo
import `in`.curioustools.architectures.models.Pokemon
import `in`.curioustools.architectures.network.PokemonNetworkRequestService
import `in`.curioustools.architectures.utils.Logito
import `in`.curioustools.architectures.utils.interceptors.InterceptorFactory
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DashboardVM(application: Application) : AndroidViewModel(application) {
    private val logito =Logito(TAG="DashBoardVM>>")
    private val repo = PokemonRepo(application)
    private val service = PokemonNetworkRequestService(InterceptorFactory.getAll(application))
    private val refreshRequestCallback = object :Callback<List<Pokemon>>{
        override fun onFailure(call: Call<List<Pokemon>>, t: Throwable) {
            logito.e("some error happenned")
            logito.e(t.message)
            logito.e(t.cause)

        }

        override fun onResponse(call: Call<List<Pokemon>>, response: Response<List<Pokemon>>) {
            val recievedPokemons:List<Pokemon>? = response.body()

            if(recievedPokemons==null){
                logito.e("null list received")
            }
            else{

                repoUpdateCache(recievedPokemons)
            }

        }
    }
    private val pokemonLiveData = repo.getAllPokemonsLive()



    init {
        refresh()
    }

    fun refresh() {
        service.makeAsyncRequest(refreshRequestCallback)
    }


    fun getLiveListOfPokemons() =pokemonLiveData


    private fun repoUpdateCache(recievedPokemons: List<Pokemon>) {
        repo.refreshCache(recievedPokemons)

    }


}