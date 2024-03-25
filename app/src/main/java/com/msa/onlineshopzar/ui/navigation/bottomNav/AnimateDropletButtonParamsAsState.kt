package com.msa.onlineshopzar.ui.navigation.bottomNav

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.isSpecified
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.msa.onlineshopzar.R
import com.msa.onlineshopzar.ui.navigation.bottomNav.anim.BallAnimInfo
import com.msa.onlineshopzar.ui.navigation.bottomNav.anim.BallAnimation
import com.msa.onlineshopzar.ui.navigation.bottomNav.anim.Height
import com.msa.onlineshopzar.ui.navigation.bottomNav.anim.IndentAnimation
import com.msa.onlineshopzar.ui.navigation.bottomNav.anim.Parabolic
import com.msa.onlineshopzar.ui.navigation.bottomNav.anim.ShapeCornerRadius
import com.msa.onlineshopzar.ui.navigation.bottomNav.anim.shapeCornerRadius
import com.msa.onlineshopzar.ui.navigation.bottomNav.layout.animatedNavBarMeasurePolicy
import com.msa.onlineshopzar.ui.navigation.bottomNav.util.ballTransform
import com.msa.onlineshopzar.ui.theme.barcolor

@Composable
fun AnimatedNavigationBar(
    modifier: Modifier = Modifier,
    selectedIndex: Int,
    barColor: Color = Color.White,
    ballColor: Color = Color.Black,
    cornerRadius: ShapeCornerRadius = shapeCornerRadius(0f),
    ballAnimation: BallAnimation = Parabolic(tween(300)),
    indentAnimation: IndentAnimation = Height(tween(300)),
    navigationBarItems: List<NavigationBarItems>,
    content: @Composable () -> Unit,
) {
    var itemPositions by remember { mutableStateOf(listOf<Offset>()) }
    val measurePolicy = animatedNavBarMeasurePolicy {
        itemPositions = it.map { xCord ->
            Offset(xCord, 0f)
        }
    }

    val selectedItemOffset by remember(selectedIndex, itemPositions) {
        derivedStateOf {
            if (itemPositions.isNotEmpty()) itemPositions[selectedIndex] else Offset.Unspecified
        }
    }

    val indentShape = indentAnimation.animateIndentShapeAsState(
        shapeCornerRadius = cornerRadius,
        targetOffset = selectedItemOffset
    )

    val ballAnimInfoState = ballAnimation.animateAsState(
        targetOffset = selectedItemOffset,
    )

    Box(
        modifier = modifier
    ) {
        Layout(
            modifier = Modifier
                .graphicsLayer {
                    clip = true
                    shape = indentShape.value
                }
                .background(barColor),
            content = content,
            measurePolicy = measurePolicy
        )

        if (ballAnimInfoState.value.offset.isSpecified) {
            ColorBall(
                ballAnimInfo = ballAnimInfoState.value,
                ballColor = ballColor,
                sizeDp = ballSize,
                nav = navigationBarItems.get(selectedIndex)
            )
        }
    }

}

@Composable
private fun ColorBall(
    modifier: Modifier = Modifier,
    ballColor: Color,
    ballAnimInfo: BallAnimInfo,
    sizeDp: Dp,
    nav:NavigationBarItems
) {
    Column(
        modifier = modifier
            .ballTransform(ballAnimInfo)
            .size(sizeDp)
            .clip(shape = CircleShape)
            .background(ballColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Icon(
            modifier = Modifier.size(26.dp),
            imageVector = nav.icon,
            contentDescription = "Bottom Bar",
            tint = Color.White
        )
    }
}


val ballSize = 70.dp

//enum class NavigationBarItems(val icon: ImageVector, val title: String) {
//    Setting(icon = ImageVector.vectorResource(R.drawable.iconproduct), title = ""),
//    Report(icon = Icons.Default.Analytics),
//    g(icon = Icons.Default.Home),
//    h(icon = Icons.Default.AcUnit),
//    u(icon = Icons.Default.AddAlert),
//
//}

data class NavigationBarItems(
    val icon: ImageVector, val title: String
)
@Preview
@Composable
private fun AnimatedNavigationBarPreview() {
    var selectedIndex by remember {
        mutableStateOf(0)
    }
    val navigationBarItemsList = listOf(
        NavigationBarItems(icon = ImageVector.vectorResource(R.drawable.iconproduct), title = "Product"),
        NavigationBarItems(icon = ImageVector.vectorResource(R.drawable.report), title = "Report"),
        NavigationBarItems(icon =ImageVector.vectorResource(R.drawable.basket), title = "Basket"),
        NavigationBarItems(icon =ImageVector.vectorResource(R.drawable.profile), title = "Profile"),
        // ادامه دادن برای سایر آیتم‌ها
    )
    AnimatedNavigationBar(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 40.dp)
            .height(85.dp),
        selectedIndex = selectedIndex,
        cornerRadius = shapeCornerRadius(cornerRadius = 0.dp),
        ballAnimation = Parabolic(tween(300)),
        indentAnimation = Height(tween(300)),
        barColor = barcolor,
        ballColor = Color.Red,
        navigationBarItems = navigationBarItemsList
    ) {
    }
}

