package com.tiki.challenge.utils

import android.graphics.drawable.Drawable
import top.defaults.drawabletoolbox.DrawableBuilder
import java.util.*


const val COLOR_1 = 0xFFf5a623.toInt()
const val COLOR_2 = 0xFF20a8a3.toInt()
const val COLOR_3 = 0xFF9e9ea2.toInt()
const val COLOR_4 = 0xFF20a8a3.toInt()
const val COLOR_5 = 0xFF3F51B5.toInt()
const val COLOR_6 = 0xFF417505.toInt()

class DrawableUtils {
    companion object {

        private val listColor = listOf(COLOR_1, COLOR_2, COLOR_3, COLOR_4, COLOR_5, COLOR_6)
        private val randomGenerator = Random()
        fun generateDrawable(color: Int): Drawable {
            return DrawableBuilder()
                .rectangle()
                .solidColor(color)
                .bottomLeftRadius(20) // in pixels
                .topLeftRadius(20) // in pixels
                .topRightRadius(20) // in pixels
                .bottomRightRadius(20) // in pixels
                .build()
        }

        fun generateColor(): Int {
            return listColor[randomGenerator.nextInt(listColor.size)]
        }

    }
}