package com.shashank.quizapp.utils

object ColorPicker {
    private val lightColors = arrayOf(
        "#F08080", // Light Coral
        "#FFA07A", // Light Salmon
        "#FFB6C1", // Light Pink
        "#90EE90", // Light Green
        "#20B2AA", // Light Sea Green
        "#778899", // Light Slate Gray
        "#B0C4DE" // Light Steel Blue
    )
    private var currentColorIndex = 0
    fun nextColor() : String {
        currentColorIndex = (currentColorIndex + 1) % lightColors.size
        return lightColors[currentColorIndex]
    }
}