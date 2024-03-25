package com.msa.onlineshopzar.ui.navigation.bottomNav.shape

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.LayoutDirection

fun Path.circleIndent(
    size: Size,
    indentShapeData: IndentShapeData,
    layoutDirection: LayoutDirection,
): Path {
    val centerX = size.width / 2f
    val centerY = size.height / 2f
    val radius = size.minDimension / 4f
    val cornerRadius = indentShapeData.cornerRadius

    return apply {
        moveTo(centerX + radius, centerY)
        arcTo(
            rect = Rect(
                left = centerX - radius,
                top = centerY - radius,
                right = centerX + radius,
                bottom = centerY + radius,
            ),
            startAngleDegrees = 0f,
            sweepAngleDegrees = 180f,
            forceMoveTo = false
        )
// بریدگی به داخل
        val indentStartX = centerX + cornerRadius.topLeft
        val indentEndX = centerX + radius - cornerRadius.topLeft
        val indentY = centerY - cornerRadius.topLeft
        lineTo(indentEndX, centerY)



        close()
    }
}