package com.msa.onlineshopzar.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.state.StateDialog
import com.maxkeppeler.sheets.state.models.ProgressIndicator
import com.maxkeppeler.sheets.state.models.State
import com.maxkeppeler.sheets.state.models.StateConfig
import kotlin.math.roundToInt

@ExperimentalMaterial3Api
@Composable
fun DialogLoadingWait(closeSelection: () -> Unit) {

    val customProgressIndicator: @Composable (Float) -> Unit = { progress: Float ->
        customProgressBar(progress)
    }

    val state = State.Loading(
        "لطفا منتظر بمانید ",
        ProgressIndicator.Linear(
            value = 0.5f, customProgressIndicator = customProgressIndicator
        )

    )
    StateDialog(
        state = rememberUseCaseState(visible = true, onCloseRequest = { closeSelection() }),
        config = StateConfig(state = state)
    )
}

@Composable
fun CustomRedProgressBar(progress: Float) {
    LinearProgressIndicator(
        progress = progress,
        color = Color.Red // رنگ قرمز
    )
}
// on below line we are creating a function for custom progress bar.
@Composable
fun customProgressBar(progressva: Float) {
    // in this method we are creating a column
    Column(
        // in this column we are specifying modifier to
        // align the content within the column
        // to center of the screen.
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
        ,

        // on below line we are specifying horizontal
        // and vertical alignment for the content of our column
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // in this column we are creating a variable
        // for the progress of our progress bar.
        val currentSample = rememberSaveable { mutableStateOf(0.0) }



        var progress: Int =0;

        for (i in 0..100 step 1) {
            progress = i
        }

        // on the below line we are creating a box.
        Box(
            // inside this box we are adding a modifier
            // to add rounded clip for our box with
            // rounded radius at 15
            modifier = Modifier
                .clip(RoundedCornerShape(15.dp))
                // on below line we are specifying
                // height for the box
                .height(30.dp)

                // on below line we are specifying
                // background color for box.
                .background(Color.Gray)

                // on below line we are
                // specifying width for the box.
                .width(300.dp)
        ) {
            // in this box we are creating one more box.
            Box(
                // on below line we are adding modifier to this box.
                modifier = Modifier
                    // on below line we are adding clip \
                    // for the modifier with round radius as 15 dp.
                    .clip(RoundedCornerShape(15.dp))

                    // on below line we are
                    // specifying height as 30 dp
                    .height(30.dp)

                    // on below line we are adding background
                    // color for our box as brush
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
                    )
                    // on below line we are specifying width for the inner box
                    .width(300.dp * progress / 100)
            )
            // on below line we are creating a text for our box
            Text(
                // in text we are displaying a text as progress bar value.
                text = "$progress %",

                // on below line we are adding
                // a modifier to it as center.
                modifier = Modifier.align(Alignment.Center),

                // on below line we are adding
                // font size to it.
                fontSize = 15.sp,

                // on below line we are adding
                // font weight as bold.
                fontWeight = FontWeight.Bold,

                // on below line we are
                // specifying color for our text
                color = Color.White
            )
        }

    }
}