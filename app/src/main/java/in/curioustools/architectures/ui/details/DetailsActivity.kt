package `in`.curioustools.architectures.ui.details

import `in`.curioustools.architectures.R
import `in`.curioustools.architectures.db.PokemonRepo
import `in`.curioustools.architectures.models.Pokemon
import `in`.curioustools.architectures.utils.GlideAnimatedLoader
import `in`.curioustools.architectures.utils.Logito
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    lateinit var pokemon: Pokemon

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        initData()

    }

    override fun onStart() {
        super.onStart()

        setDataOnUi()

    }


    private fun initData() {
        val logito =  Logito()
        val id:String = intent?.getStringExtra(KEY_DATA)?:"error"
        logito.e("id = $id ")

        val repo = PokemonRepo(this)

        pokemon = repo.getPokemonByIndex(id)?: Pokemon.getDefaultPokemon()

        logito.e("Pokemon = \n $pokemon")
    }

    @SuppressLint("SetTextI18n")
    private fun setDataOnUi() {
        GlideAnimatedLoader.loadImage(iv_pokemon,pokemon.imageUrl)
        tv_pokemon_name?.text =pokemon.name
        tv_1?.text = pokemon.description1
        tv_2?.text = pokemon.indexID
        tv_3?.text = "${pokemon.height} metres."
        tv_4?.text = "${pokemon.weight} kgs."
        tv_5?.text = "${pokemon.categoriesSelf}"
        tv_6?.text = "${pokemon.categoriesWeakness}"
        tv_7?.text = "${pokemon.evolutionsIDs}" // TODO: 27-07-2020  replace with clckable names of other pokemons

        tv9?.text = "${pokemon.hp}/ ${Pokemon.maxHp}"
        tv10?.text = "${pokemon.attack}/ ${Pokemon.maxAttack}"
        tv11?.text = "${pokemon.defense}/ ${Pokemon.maxDefense}"
        tv12?.text = "${pokemon.speed}/ ${Pokemon.maxSpeed}"
        tv13?.text = "${pokemon.baseExp}/ ${Pokemon.maxExp}"



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