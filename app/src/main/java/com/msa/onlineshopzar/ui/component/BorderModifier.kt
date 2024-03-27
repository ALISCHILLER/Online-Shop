package com.msa.onlineshopzar.ui.component

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.msa.onlineshopzar.ui.theme.OnlineShopZarTheme

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.semiBorder(strokeWidth: Dp, color: Color, cornerRadiusDp: Dp) = composed(
    factory = {
        val density = LocalDensity.current
        val strokeWidthPx = density.run { strokeWidth.toPx() }
        val cornerRadius = density.run { cornerRadiusDp.toPx() }

        Modifier.drawBehind {
            val width = size.width
            val height = size.height

            drawLine(
                color = color,
                start = Offset(x = 0f, y = height),
                end = Offset(x = 0f, y = cornerRadius),
                strokeWidth = strokeWidthPx
            )

            // Top left arc
            drawArc(
                color = color,
                startAngle = 180f,
                sweepAngle = 90f,
                useCenter = false,
                topLeft = Offset.Zero,
                size = Size(cornerRadius * 2, cornerRadius * 2),
                style = Stroke(width = strokeWidthPx)
            )


            drawLine(
                color = color,
                start = Offset(x = cornerRadius, y = 0f),
                end = Offset(x = width - cornerRadius, y = 0f),
                strokeWidth = strokeWidthPx
            )

            // Top right arc
            drawArc(
                color = color,
                startAngle = 270f,
                sweepAngle = 90f,
                useCenter = false,
                topLeft = Offset(x = width - cornerRadius * 2, y = 0f),
                size = Size(cornerRadius * 2, cornerRadius * 2),
                style = Stroke(width = strokeWidthPx)
            )

            drawLine(
                color = color,
                start = Offset(x = width, y = height),
                end = Offset(x = width, y = cornerRadius),
                strokeWidth = strokeWidthPx
            )
        }
    }
)


@Preview
@Composable
private fun UShapeBorderSample() {

    OnlineShopZarTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){


            Card(
                shape = RoundedCornerShape(
                    topEnd = 10.dp,
                    topStart = 10.dp,
                    bottomEnd = 0.dp,
                    bottomStart = 0.dp
                ),
                modifier = Modifier
                    .semiBorder(1.dp, Color.Black, 10.dp)

            ) {
                Box(
                    modifier = Modifier
                        .size(150.dp)
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Hello World")
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Card(
                shape = RoundedCornerShape(
                    topEnd = 20.dp,
                    topStart = 20.dp,
                    bottomEnd = 0.dp,
                    bottomStart = 0.dp
                ),
                modifier = Modifier
                    .semiBorder(1.dp, Color.Black, 20.dp)

            ) {
                Box(
                    modifier = Modifier
                        .size(150.dp)
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Hello World")
                }
            }
        }
    }
}


@Composable
private fun createShape(cornerRadius: Dp): Shape {

    val density = LocalDensity.current
    return GenericShape { size, _ ->

        val width = size.width
        val height = size.height
        val cornerRadiusPx = density.run { cornerRadius.toPx() }

        moveTo(0f, height)

        // Vertical line on left size
        lineTo(0f, cornerRadiusPx * 2)

        arcTo(
            rect = Rect(
                offset = Offset.Zero,
                size = Size(cornerRadiusPx * 2, cornerRadiusPx * 2)
            ),
            startAngleDegrees = 180f,
            sweepAngleDegrees = 90f,
            forceMoveTo = false
        )

        lineTo(width - cornerRadiusPx * 2, 0f)
        arcTo(
            rect = Rect(
                offset = Offset(width - cornerRadiusPx * 2, 0f),
                size = Size(cornerRadiusPx * 2, cornerRadiusPx * 2)
            ),
            startAngleDegrees = 270f,
            sweepAngleDegrees = 90f,
            forceMoveTo = false
        )
        // Vertical line on right size
        lineTo(width, height)

    }
}


@Preview
@Composable
private fun UShapeBorderSamples() {
    Card(
        shape = RoundedCornerShape(
            topEnd = 10.dp,
            topStart = 10.dp,
            bottomEnd = 0.dp,
            bottomStart = 0.dp
        ),
        modifier = Modifier
            .border(BorderStroke(width = 1.dp, color = Color.Red), createShape(10.dp))

    ) {
        Box(
            modifier = Modifier
                .size(200.dp)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Text("Hello World")
        }
    }
}