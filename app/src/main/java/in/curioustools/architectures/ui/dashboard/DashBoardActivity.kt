@file:Suppress("SpellCheckingInspection")

package `in`.curioustools.architectures.ui.dashboard

import `in`.curioustools.architectures.R
import `in`.curioustools.architectures.databinding.ActivityDashBoardBinding
import `in`.curioustools.architectures.models.Pokemon
import `in`.curioustools.architectures.ui.details.DetailsActivity
import `in`.curioustools.architectures.utils.Logito
import `in`.curioustools.architectures.utils.ScreenSizeAdjustableGridManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class DashBoardActivity : AppCompatActivity() {

    private lateinit var adp: DashboardAdapter
    private val logito = Logito(TAG = "DashboardAct>>")
    private lateinit var viewModel: DashboardVM

    lateinit var bindedUi: ActivityDashBoardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindedUi = DataBindingUtil.setContentView(this, R.layout.activity_dash_board)

        //init ui
        val itemClickListener = object : MyRvItemClickListener<Pokemon> {
            override fun onItemClick(clickedItem: Pokemon) {
                DetailsActivity.open(this@DashBoardActivity, clickedItem.indexID)
            }
        }
        adp = DashboardAdapter( itemClickListener, layoutInflater)

        //initViewModel
        initViewModel()

    }

    override fun onStart() {
        super.onStart()

        //bind th ui with xml
        /*
          We could have gone the same way as directly passing the layout manager and adapter to
          recycler as that  we did in for textviews in  details activity, ie by passing both of them
          as data variables, but that's not that simple becauuse although recyler view xml allows us
          to pass layout manager in xml, it doesn't allow passing adapter directly. so first option
          is to go onto make a custom binding adapter( lik we did  for glide image loader), but
          that's not a very helpful approach since we would be doing the same task as
          my 2nd approach(given below) but via a long route .

          Or we could just access the recycler view via bindedUiObj and directly attach the
          layout manager/adapter.
         */
        val layoutManager = ScreenSizeAdjustableGridManager.get(this, 200)
        bindedUi.rvPokemon.adapter = adp
        bindedUi.rvPokemon.layoutManager =layoutManager


        //refreshDataList
        startListeningToChanges()
        refreshDataList()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider
            .AndroidViewModelFactory
            .getInstance(this.application)
            .create(DashboardVM::class.java)

    }

    private fun startListeningToChanges() {
        val liveData = viewModel.getLiveListOfPokemons()

        val pokemonListObserver: Observer<List<Pokemon>?> = Observer<List<Pokemon>?> { list ->
            if (list != null) adp.setAdapterData(list)
            else logito.e("livedata sent a null list: $list")
        }

        liveData?.observe(this,pokemonListObserver)

    }

    private fun refreshDataList() {
        viewModel.refresh()

    }


}

