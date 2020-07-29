@file:Suppress("SpellCheckingInspection", "unused", "JoinDeclarationAndAssignment")

package `in`.curioustools.architectures.ui.dashboard

import `in`.curioustools.architectures.db.PokemonRepo
import `in`.curioustools.architectures.models.Pokemon
import `in`.curioustools.architectures.network.PokemonNetworkRequestService
import `in`.curioustools.architectures.utils.Logito
import `in`.curioustools.architectures.utils.interceptors.InterceptorFactory
import `in`.curioustools.architectures.utils.threading.AppExecutors
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import androidx.paging.toLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DashboardVM(application: Application) : AndroidViewModel(application) {
    //lateinit is redundant for all below
    private /*lateinit var*/  val logito: Logito
    private val executors: AppExecutors
    private val repo: PokemonRepo
    private val service: PokemonNetworkRequestService
    private val refreshRequestCallback: Callback<List<Pokemon>>

    private val pokemonLiveData: LiveData<PagedList<Pokemon>>


    init {
        logito = Logito(TAG = "DashBoardVM>>")

        executors = AppExecutors.getSingletonInstance()
        repo = PokemonRepo(application, executors)

        service = PokemonNetworkRequestService(InterceptorFactory.getAll(application))
        refreshRequestCallback = object : Callback<List<Pokemon>> {
            override fun onFailure(call: Call<List<Pokemon>>, t: Throwable) {
                logito.e("some error happenned")
                logito.e(t.message)
                logito.e(t.cause)

            }

            override fun onResponse(call: Call<List<Pokemon>>, response: Response<List<Pokemon>>) {
                val recievedPokemons: List<Pokemon>? = response.body()

                if (recievedPokemons == null) {
                    logito.e("null list received")
                } else {

                    repoUpdateCache(recievedPokemons)
                }

            }
        }
        pokemonLiveData = internalGetPokemonPagedLiveList()
    }

    private fun internalGetPokemonPagedLiveList(): LiveData<PagedList<Pokemon>> {

        /*
            private  val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(true)            //will pass null to adapter when user is scrolling fastly . the adapter will   have logic for  handling nulls , so adapter will show placeholder ui for null values. it would come back later and provide the actual item values
                    .setInitialLoadSizeHint(40)             // how many items are loaded on the first run. should be usually kept larger than page size
                    .setPageSize(10)                        // default page size
                    .setPrefetchDistance(9)                 //trigger next page load when user reaches 9th result. this should usually be kept to half of paged list but currently checking out something
                .setMaxSize(5)                              //how many pages should be kept in memory. must be atleast  (2xprefetch distace+pagesize)
                    .build()
        */

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(true).setInitialLoadSizeHint(40)
            .setPageSize(10).setPrefetchDistance(9).setMaxSize(30)
            .build()

        val factory:DataSource.Factory<Int,Pokemon> = repo.getPagedPokemonLiveListFactory()


        return factory.toLiveData(config,fetchExecutor = executors.singleThreadExecutorService)
        // there is also something called boundry callback and data source/data source factory.
        // but i couldn't understand tha much, nor did i really needed it. in fact, we could siply
        // return factory.toLiveData(page_size) and everything will be handled automatically

        // i shall cover the use of pagination in detail in a sepearte network based project, coz that's where its mostly useful





    }


    private fun repoUpdateCache(recievedPokemons: List<Pokemon>) {
        repo.refreshCache(recievedPokemons)

    }

    fun refresh() {
        service.makeAsyncRequest(refreshRequestCallback)
    }


    fun getLiveListOfPokemons() = pokemonLiveData


}