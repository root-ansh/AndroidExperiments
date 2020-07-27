package `in`.curioustools.architectures.utils


import `in`.curioustools.architectures.R
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class GlideAnimatedLoader {
    companion object{
        private const val TAG  = "UiUtils>>"


        fun loadImage(ivCover: ImageView?, imageUrl: String?) {
            if(ivCover==null){
                Log.e(TAG, "loadImage: imageview is null " )
                return
            }


            Glide
                .with(ivCover.context)
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.bg_circle_white_fff) // can also be a drawable
                .error(R.drawable.bg_circle_white_fff)
                .fallback(R.drawable.bg_circle_white_fff)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(ivCover);
        }



    }
}