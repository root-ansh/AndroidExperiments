@file:Suppress("SpellCheckingInspection")

package `in`.curioustools.architectures.ui.dashboard

import `in`.curioustools.architectures.R
import `in`.curioustools.architectures.models.Pokemon
import `in`.curioustools.architectures.ui.details.DetailsActivity
import `in`.curioustools.architectures.utils.Logito
import `in`.curioustools.architectures.utils.ScreenSizeAdjustableGridManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_dash_board.*

class DashBoardActivity : AppCompatActivity() {

    private lateinit var adp: DashboardAdapter
    private val logito = Logito(TAG = "DashboardAct>>")
    private lateinit var viewModel: DashboardVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)

        //initUi
        adp = DashboardAdapter(listOf(), object : MyRvItemClickListener<Pokemon> {
            override fun onItemClick(clickedItem: Pokemon) {
                DetailsActivity.open(this@DashBoardActivity, clickedItem.indexID)
            }
        })


        initViewModel()


    }

    override fun onStart() {
        super.onStart()

        //attach adapter and layout manager
        rv_pokemon?.layoutManager = ScreenSizeAdjustableGridManager.get(this, 200)
        rv_pokemon?.adapter = adp

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


