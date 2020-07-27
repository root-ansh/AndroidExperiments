@file:Suppress("unused")

package `in`.curioustools.architectures.ui.dashboard

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import `in`.curioustools.architectures.R
import `in`.curioustools.architectures.models.Pokemon
import `in`.curioustools.architectures.utils.GlideAnimatedLoader
import `in`.curioustools.architectures.utils.Logito
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView


class DashboardAdapter(
    private var dataList: List<Pokemon> = listOf(),
    private var itemClickListener: MyRvItemClickListener<Pokemon>
) : RecyclerView.Adapter<DashboardHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_pokemonlist_eachrow, parent, false)
        return DashboardHolder(view)
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


class DashboardHolder(v: View) : RecyclerView.ViewHolder(v) {
    private val tvName: TextView? = v.findViewById(R.id.eachrow_tv_pokemon_name)
    private val ivImage: ImageView? = v.findViewById(R.id.eachrow_iv_pokemon)

    fun bind(data: Pokemon, listener: MyRvItemClickListener<Pokemon>) {

        tvName?.text = data.name
        GlideAnimatedLoader.loadImage(ivImage, data.imageUrl)

        itemView.setOnClickListener {
            Logito().e("item clicked")
            listener.onItemClick(data)
        }


    }

}


interface MyRvItemClickListener<T> {

    fun onItemClick(clickedItem: T)

}