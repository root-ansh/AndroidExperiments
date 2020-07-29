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
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter

class DashboardAdapter(
    private val inflater: LayoutInflater,
    private var itemClickListener: MyRvItemClickListener<Pokemon>

) : PagedListAdapter<Pokemon,DashboardHolder>(Pokemon.DIFF_UTIL){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardHolder {
        val view = inflater.inflate(R.layout.layout_pokemonlist_eachrow, parent, false)
        return DashboardHolder(view)
    }


    override fun onBindViewHolder(holder: DashboardHolder, position: Int) {
        holder.bind(getItem(position), itemClickListener)
    }


//    fun setAdapterData(dataList: List<Pokemon>) {
//        this.dataList = dataList
//        notifyItemRangeChanged(0, dataList.size)
//    }

    override fun submitList(pagedList: PagedList<Pokemon>?) {
        super.submitList(pagedList)
        notifyItemRangeChanged(0,pagedList?.size?:0)
    }



}


class DashboardHolder(v: View) : RecyclerView.ViewHolder(v) {
    private val tvName: TextView? = v.findViewById(R.id.eachrow_tv_pokemon_name)
    private val ivImage: ImageView? = v.findViewById(R.id.eachrow_iv_pokemon)

    fun bind(data: Pokemon?, listener: MyRvItemClickListener<Pokemon>) {
        val nullCaseText = "Loading"
        val nullCaseUrl ="https://via.placeholder.com/150/000000/FFFFFF/?text=AWESOME!"
        val nullCaseItemClickObj = Pokemon.getDefaultPokemon()

        tvName?.text = data?.name?:nullCaseText
        GlideAnimatedLoader.loadImage(ivImage, data?.imageUrl?:nullCaseUrl)

        itemView.setOnClickListener {
            Logito().e("item clicked")
            listener.onItemClick(data?:nullCaseItemClickObj)
        }


    }

}


interface MyRvItemClickListener<T> {

    fun onItemClick(clickedItem: T)

}