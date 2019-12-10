package org.devconmyanmar.devconyangon.utils.darkmode

import android.content.Context
import android.graphics.Color
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt

class ColorUtils {

    companion object{
        @ColorInt
        fun getThemeColor(context: Context, @AttrRes attrResId: Int): Int {
            val a = context.obtainStyledAttributes(null, intArrayOf(attrResId))
            try {
                return a.getColor(0, Color.WHITE)
            } finally {
                a.recycle()
            }
        }
    }
}