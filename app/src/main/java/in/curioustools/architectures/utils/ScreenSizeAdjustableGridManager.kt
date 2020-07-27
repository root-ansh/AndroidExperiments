package `in`.curioustools.architectures.utils

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager

class ScreenSizeAdjustableGridManager {
    companion object {

        fun get(context: Context, singleColWidth: Int): GridLayoutManager {
            // eg if u want a 100 dp col then pass 100  as singleColWidth
            val matrix = context.resources.displayMetrics
            val screenWidthInDp = matrix.widthPixels / matrix.density
            val columns = ((screenWidthInDp / singleColWidth) + 0.5)

            val columnCount = columns.toInt()
            return GridLayoutManager(context, columnCount)
        }
    }
}