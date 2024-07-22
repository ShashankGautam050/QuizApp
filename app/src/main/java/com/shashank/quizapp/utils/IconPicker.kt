package com.shashank.quizapp.utils

import com.shashank.quizapp.R

object IconPicker {
    private val iconPicker = arrayOf(
        R.drawable.pic1,
        R.drawable.pic2,
        R.drawable.pic3
    )
    private var currentIcon = 0
    fun getIcon(): Int {
     currentIcon = (currentIcon + 1) % iconPicker.size
        return iconPicker[currentIcon]
    }

}