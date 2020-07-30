package `in`.curioustools.architectures.ui.details

import `in`.curioustools.architectures.R
import `in`.curioustools.architectures.databinding.ActivityDetailsBinding
import `in`.curioustools.architectures.db.PokemonRepo
import `in`.curioustools.architectures.models.Pokemon
import `in`.curioustools.architectures.utils.GlideAnimatedLoader
import `in`.curioustools.architectures.utils.Logito
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import kotlinx.android.synthetic.main.activity_details.*


//to bind Pokemon directly, simply replace uipokemon temp with pokemon in xml followed by
// returning pokemon from  getDataFromIntent()

class DetailsActivity : AppCompatActivity() {
    lateinit var bindedView: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindedView = DataBindingUtil.setContentView(this, R.layout.activity_details)

        val id: String = intent?.getStringExtra(KEY_DATA) ?: "error"
        Logito().e(id)
        val repo = PokemonRepo(this)
        val pokemon = repo.getPokemonByIndex(id) ?: Pokemon.getDefaultPokemon()
        Logito().e(pokemon)
        bindedView.pokemonObj =
            UiPokemonTemp(pokemon) // also works with null or UiPokemonTemp.getDefaultUiPokemon()

    }

    companion object {
        private const val KEY_DATA = "data"
        fun open(context: Context, indexID: String) {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(KEY_DATA, indexID)
            context.startActivity(intent)
        }
    }

}