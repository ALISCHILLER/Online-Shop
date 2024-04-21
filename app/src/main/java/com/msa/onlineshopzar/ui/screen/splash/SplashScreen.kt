package com.msa.onlineshopzar.ui.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.msa.onlineshopzar.R



@Composable
fun SplashScreen(
    viewModel: SplashViewModel = hiltViewModel()
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                // on below line we are adding brush for background color.
                Brush.horizontalGradient(
                    // in this color we are specifying a gradient
                    // with the list of the colors.
                    listOf(
                        // on below line we are adding two colors.
                        Color(0xEB700C24),
                        Color(0xEBF0023A)
                    )
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logozar),
            contentDescription = "logo",
            modifier = Modifier
                .size(110.dp, 82.dp)
                .layoutId("logo")
        )

        viewModel.splashCheck()
    }

}