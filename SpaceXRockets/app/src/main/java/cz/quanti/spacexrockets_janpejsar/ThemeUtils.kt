package cz.quanti.spacexrockets_janpejsar

import android.content.Context
import androidx.annotation.ColorInt
import androidx.annotation.AttrRes
import android.graphics.Color

object ThemeUtils {
    @ColorInt
    fun getAttributeColor(context: Context, @AttrRes attr: Int): Int {
        val attrs = intArrayOf(attr)
        val color: Int
        val typedArray = context.obtainStyledAttributes(attrs)
        color = try {
            typedArray.getColor(0, Color.BLACK)
        } finally {
            typedArray.recycle()
        }
        return color
    }
}