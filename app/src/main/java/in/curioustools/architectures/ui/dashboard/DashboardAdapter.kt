@file:Suppress("unused")

package `in`.curioustools.architectures.ui.dashboard

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import `in`.curioustools.architectures.R
import `in`.curioustools.architectures.databinding.LayoutPokemonlistEachrowBinding
import `in`.curioustools.architectures.models.Pokemon
import `in`.curioustools.architectures.utils.GlideAnimatedLoader
import `in`.curioustools.architectures.utils.Logito
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil


class DashboardAdapter(
    private var itemClickListener: MyRvItemClickListener<Pokemon>,
    private val inflater: LayoutInflater

) : RecyclerView.Adapter<DashboardHolder>() {


    private var dataList: List<Pokemon> = listOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardHolder {
        val binding: LayoutPokemonlistEachrowBinding =
            DataBindingUtil.inflate(inflater, R.layout.layout_pokemonlist_eachrow, parent, false)
        return DashboardHolder(binding)
    }


    override fun onBindViewHolder(holder: DashboardHolder, position: Int) {
        holder.bind(dataList[position], itemClickListener)
    }

    override fun getItemCount() = dataList.size

    fun setAdapterData(dataList: List<Pokemon>) {
        this.dataList = dataList
        notifyItemRangeChanged(0, dataList.size)
    }

}


class DashboardHolder(val binding: LayoutPokemonlistEachrowBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(data: Pokemon, listener: MyRvItemClickListener<Pokemon>) {

        binding.listenerInstance = listener
        binding.pokemonObj = data
        binding.executePendingBindings()
        /*
           https://medium.com/androiddevelopers/android-data-binding-recyclerview-db7c40d9f0e4
           This forces the bindings to run immediately instead of delaying them until the next
            frame. RecyclerView will measure the view immediately after onBindViewHolder.
            If the wrong data is in the views because the binding is waiting until the next frame,
            it will be measured improperly. thus The executePendingBindings() is important!
         */
    }

}


interface MyRvItemClickListener<T> {

    fun onItemClick(clickedItem: T)

}