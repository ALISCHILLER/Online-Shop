package com.msa.onlineshopzar.ui.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.Spring.StiffnessLow
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.msa.onlineshopzar.ui.theme.Typography
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import kotlin.math.sign



private const val ICON_BUTTON_ALPHA_INITIAL = 0.3f
private const val CONTAINER_BACKGROUND_ALPHA_INITIAL = 0.6f
private const val CONTAINER_BACKGROUND_ALPHA_MAX = 0.7f
private const val CONTAINER_OFFSET_FACTOR = 0.1f
private const val DRAG_LIMIT_HORIZONTAL_DP = 72
private const val DRAG_LIMIT_VERTICAL_DP = 64
private const val START_DRAG_THRESHOLD_DP = 2
private const val DRAG_LIMIT_HORIZONTAL_THRESHOLD_FACTOR = 0.9f
private const val DRAG_LIMIT_VERTICAL_THRESHOLD_FACTOR = 0.9f
private const val DRAG_HORIZONTAL_ICON_HIGHLIGHT_LIMIT_DP = 36
private const val DRAG_VERTICAL_ICON_HIGHLIGHT_LIMIT_DP = 60
private const val DRAG_CLEAR_ICON_REVEAL_DP = 2
private const val COUNTER_DELAY_INITIAL_MS = 500L
private const val COUNTER_DELAY_FAST_MS = 100L

@Composable
fun CounterButton(
    value: String,
    onValueDecreaseClick: () -> Unit,
    onValueIncreaseClick: () -> Unit,
    onValueClearClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .width(100.dp)
            .height(50.dp)
    ) {
        val thumbOffsetX = remember { Animatable(0f) }
        val thumbOffsetY = remember { Animatable(0f) }
        val verticalDragButtonRevealPx = DRAG_CLEAR_ICON_REVEAL_DP.dp.dpToPx()

        ButtonContainer(
            thumbOffsetX = thumbOffsetX.value,
            thumbOffsetY = thumbOffsetY.value,
            onValueDecreaseClick = onValueDecreaseClick,
            onValueIncreaseClick = onValueIncreaseClick,
            onValueClearClick = onValueClearClick,
            clearButtonVisible = thumbOffsetY.value >= verticalDragButtonRevealPx,
            modifier = Modifier
        )

        DraggableThumbButton(
            value = value,
            thumbOffsetX = thumbOffsetX,
            thumbOffsetY = thumbOffsetY,
            onClick = onValueIncreaseClick,
            onValueDecreaseClick = onValueDecreaseClick,
            onValueIncreaseClick = onValueIncreaseClick,
            onValueReset = onValueClearClick,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
private fun ButtonContainer(
    thumbOffsetX: Float,
    thumbOffsetY: Float,
    onValueDecreaseClick: () -> Unit,
    onValueIncreaseClick: () -> Unit,
    onValueClearClick: () -> Unit,
    modifier: Modifier = Modifier,
    clearButtonVisible: Boolean = false,
) {
    // at which point the icon should be fully visible
    val horizontalHighlightLimitPx = DRAG_HORIZONTAL_ICON_HIGHLIGHT_LIMIT_DP.dp.dpToPx()
    val verticalHighlightLimitPx = DRAG_VERTICAL_ICON_HIGHLIGHT_LIMIT_DP.dp.dpToPx()

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .offset {
                IntOffset(
                    (thumbOffsetX * CONTAINER_OFFSET_FACTOR).toInt(),
                    (thumbOffsetY * CONTAINER_OFFSET_FACTOR).toInt(),
                )
            }
            .border(border = BorderStroke(width = 1.dp, Color.LightGray), shape =  CircleShape)
            .fillMaxSize()
            .clip(RoundedCornerShape(64.dp))
            .background(
                Color.White.copy(
                    alpha = if (thumbOffsetX.absoluteValue > 0.0f) {
                        // horizontal
                        (CONTAINER_BACKGROUND_ALPHA_INITIAL + ((thumbOffsetX.absoluteValue / horizontalHighlightLimitPx) / 20f))
                            .coerceAtMost(CONTAINER_BACKGROUND_ALPHA_MAX)
                    } else if (thumbOffsetY.absoluteValue > 0.0f) {
                        // vertical
                        (CONTAINER_BACKGROUND_ALPHA_INITIAL + ((thumbOffsetY.absoluteValue / verticalHighlightLimitPx) / 10f))
                            .coerceAtMost(CONTAINER_BACKGROUND_ALPHA_MAX)
                    } else {
                        CONTAINER_BACKGROUND_ALPHA_INITIAL
                    }
                )
            )
            .padding(horizontal = 8.dp)
    ) {
        // decrease button
        IconControlButton(
            icon = Icons.Outlined.Remove,
            contentDescription = "Decrease count",
            onClick = onValueDecreaseClick,
            enabled = !clearButtonVisible,
            tintColor = Color.Black.copy(
                alpha = if (clearButtonVisible) {
                    0.0f
                } else if (thumbOffsetX < 0) {
                    (thumbOffsetX.absoluteValue / horizontalHighlightLimitPx).coerceIn(
                        ICON_BUTTON_ALPHA_INITIAL,
                        1f
                    )
                } else {
                    ICON_BUTTON_ALPHA_INITIAL
                }
            )
        )

        // clear button
        if (clearButtonVisible) {
            IconControlButton(
                icon = Icons.Outlined.Clear,
                contentDescription = "Clear count",
                onClick = onValueClearClick,
                enabled = false,
                tintColor = Color.Black.copy(
                    alpha = (thumbOffsetY.absoluteValue / verticalHighlightLimitPx).coerceIn(
                        ICON_BUTTON_ALPHA_INITIAL,
                        1f
                    )
                )
            )
        }

        // increase button
        IconControlButton(
            icon = Icons.Outlined.Add,
            contentDescription = "Increase count",
            onClick = onValueIncreaseClick,
            enabled = !clearButtonVisible,
            tintColor = Color.Black.copy(
                alpha = if (clearButtonVisible) {
                    0.0f
                } else if (thumbOffsetX > 0) {
                    (thumbOffsetX.absoluteValue / horizontalHighlightLimitPx).coerceIn(
                        ICON_BUTTON_ALPHA_INITIAL,
                        1f
                    )
                } else {
                    ICON_BUTTON_ALPHA_INITIAL
                }
            )
        )
    }
}

@Composable
private fun IconControlButton(
    icon: ImageVector,
    contentDescription: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    tintColor: Color = Color.Black,
    clickTintColor: Color = Color.Red,
    enabled: Boolean = true
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    IconButton(
        onClick = onClick,
        interactionSource = interactionSource,
        enabled = enabled,
        modifier = modifier
            .size(19.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = if (isPressed) clickTintColor else tintColor,
            modifier = Modifier.size(14.dp)
        )
    }
}


@Composable
private fun DraggableThumbButton(
    value: String,
    thumbOffsetX: Animatable<Float, AnimationVector1D>,
    thumbOffsetY: Animatable<Float, AnimationVector1D>,
    onClick: () -> Unit,
    onValueDecreaseClick: () -> Unit,
    onValueIncreaseClick: () -> Unit,
    onValueReset: () -> Unit,
    modifier: Modifier = Modifier
) {
    val dragLimitHorizontalPx = DRAG_LIMIT_HORIZONTAL_DP.dp.dpToPx()
    val dragLimitVerticalPx = DRAG_LIMIT_VERTICAL_DP.dp.dpToPx()
    val startDragThreshold = START_DRAG_THRESHOLD_DP.dp.dpToPx()
    val scope = rememberCoroutineScope()

    val dragDirection = remember {
        mutableStateOf(DragDirection.NONE)
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            // change the x and y position of the composable
            .offset {
                IntOffset(
                    thumbOffsetX.value.toInt(),
                    thumbOffsetY.value.toInt(),
                )
            }
            .shadow(8.dp, shape = CircleShape)
            .size(38.dp)
            .clip(CircleShape)
            .clickable {
                // only allow clicks while not dragging
                if (thumbOffsetX.value.absoluteValue <= startDragThreshold &&
                    thumbOffsetY.value.absoluteValue <= startDragThreshold
                ) {
                    onClick()
                }
            }
            .background(Color.Red)
            .pointerInput(Unit) {
                forEachGesture {
                    awaitPointerEventScope {
                        awaitFirstDown()

                        // reset drag direction
                        dragDirection.value = DragDirection.NONE

                        var counterJob: Job? = null

                        do {
                            val event = awaitPointerEvent()
                            event.changes.forEach { pointerInputChange ->
                                // update logic inside DraggableThumbButton.Modifier.pointerInput
                                scope.launch {
                                    if ((dragDirection.value == DragDirection.NONE &&
                                                pointerInputChange.positionChange().x.absoluteValue >= startDragThreshold) ||
                                        dragDirection.value == DragDirection.HORIZONTAL
                                    ) {
                                        // in case of the initial drag
                                        if (dragDirection.value == DragDirection.NONE) {
                                            counterJob = scope.launch {
                                                delay(COUNTER_DELAY_INITIAL_MS)

                                                var elapsed = COUNTER_DELAY_INITIAL_MS
                                                while (isActive && thumbOffsetX.value.absoluteValue >= (dragLimitHorizontalPx * DRAG_LIMIT_HORIZONTAL_THRESHOLD_FACTOR)) {
                                                    if (thumbOffsetX.value.sign > 0) {
                                                        onValueIncreaseClick()
                                                    } else {
                                                        onValueDecreaseClick()
                                                    }

                                                    delay(COUNTER_DELAY_FAST_MS)
                                                    elapsed += COUNTER_DELAY_FAST_MS
                                                }
                                            }
                                        }

                                        // mark horizontal dragging direction to prevent vertical dragging until released
                                        dragDirection.value = DragDirection.HORIZONTAL

                                        // calculate the drag factor so the more the thumb
                                        // is closer to the border, the more effort it takes to drag it
                                        val dragFactor =
                                            1 - (thumbOffsetX.value / dragLimitHorizontalPx).absoluteValue
                                        val delta =
                                            pointerInputChange.positionChange().x * dragFactor

                                        val targetValue = thumbOffsetX.value + delta
                                        val targetValueWithinBounds =
                                            targetValue.coerceIn(
                                                -dragLimitHorizontalPx,
                                                dragLimitHorizontalPx
                                            )

                                        thumbOffsetX.snapTo(targetValueWithinBounds)
                                    } else if (
                                        (dragDirection.value != DragDirection.HORIZONTAL &&
                                                pointerInputChange.positionChange().y >= startDragThreshold)
                                    ) {
                                        // mark vertical dragging direction to prevent horizontal dragging until released
                                        dragDirection.value = DragDirection.VERTICAL

                                        val dragFactor =
                                            1 - (thumbOffsetY.value / dragLimitVerticalPx).absoluteValue
                                        val delta =
                                            pointerInputChange.positionChange().y * dragFactor

                                        val targetValue = thumbOffsetY.value + delta
                                        val targetValueWithinBounds =
                                            targetValue.coerceIn(
                                                -dragLimitVerticalPx,
                                                dragLimitVerticalPx
                                            )

                                        thumbOffsetY.snapTo(targetValueWithinBounds)
                                    }
                                }
                            }
                        } while (event.changes.any { it.pressed })

                        counterJob?.cancel()
                    }

                    // detect drag to limit
                    if (thumbOffsetX.value.absoluteValue >= (dragLimitHorizontalPx * DRAG_LIMIT_HORIZONTAL_THRESHOLD_FACTOR)) {
                        if (thumbOffsetX.value.sign > 0) {
                            onValueIncreaseClick()
                        } else {
                            onValueDecreaseClick()
                        }
                    } else if (thumbOffsetY.value.absoluteValue >= (dragLimitVerticalPx * DRAG_LIMIT_VERTICAL_THRESHOLD_FACTOR)) {
                        onValueReset()
                    }

                    scope.launch {
                        if (dragDirection.value == DragDirection.HORIZONTAL && thumbOffsetX.value != 0f) {
                            thumbOffsetX.animateTo(
                                targetValue = 0f,
                                animationSpec = spring(
                                    dampingRatio = Spring.DampingRatioMediumBouncy,
                                    stiffness = Spring.StiffnessLow
                                )
                            )
                        } else if (dragDirection.value == DragDirection.VERTICAL && thumbOffsetY.value != 0f) {
                            thumbOffsetY.animateTo(
                                targetValue = 0f,
                                animationSpec = spring(
                                    dampingRatio = Spring.DampingRatioMediumBouncy,
                                    stiffness = Spring.StiffnessLow
                                )
                            )
                        }
                    }
                }
            }
    ) {
        Text(
            text = value,
            color = Color.White,
            textAlign = TextAlign.Center,
            style = Typography.labelSmall
        )
    }
}

@Composable
private fun Dp.dpToPx() = with(LocalDensity.current) { this@dpToPx.toPx() }

private enum class DragDirection {
    NONE, HORIZONTAL, VERTICAL
}




@Preview
@Composable
private fun ButtonPlusMinus() {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.wrapContentSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var valueCounter by remember {
                mutableStateOf(0)
            }

            CounterButton(
                value = valueCounter.toString(),
                onValueIncreaseClick = {
                    valueCounter += 1
                },
                onValueDecreaseClick = {
                    valueCounter = maxOf(valueCounter - 1, 0)
                },
                onValueClearClick = {
                    valueCounter = 0
                },
            )
        }
    }
}