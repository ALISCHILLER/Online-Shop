package com.msa.onlineshopzar.ui.navigation.bottomNav.shape

import com.msa.onlineshopzar.ui.navigation.bottomNav.anim.ShapeCornerRadius
import com.msa.onlineshopzar.ui.navigation.bottomNav.anim.shapeCornerRadius


data class IndentShapeData(
    val xIndent: Float = 0f,
    val height: Float = 0f,
    val width: Float = 0f,
    val cornerRadius: ShapeCornerRadius = shapeCornerRadius(0f),
    val ballOffset: Float = 0f,
)