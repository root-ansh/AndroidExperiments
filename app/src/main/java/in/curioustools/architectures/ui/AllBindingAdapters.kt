package `in`.curioustools.architectures.ui

import `in`.curioustools.architectures.ui.details.DetailsActivity
import `in`.curioustools.architectures.utils.GlideAnimatedLoader
import android.content.Context
import android.content.Intent
import android.widget.ImageView
import androidx.databinding.BindingAdapter

public class AllBindingAdapters {
    // all are binded to xmlns:binding schema

    companion object {

        @JvmStatic
        @BindingAdapter(" binding:url")
        public fun bindImage(view: ImageView, receivedUrl: String?) {
            GlideAnimatedLoader.loadImage(view, receivedUrl)
        }
    }


}