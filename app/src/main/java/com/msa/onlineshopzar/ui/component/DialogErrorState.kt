@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
package com.msa.onlineshopzar.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.models.base.UseCaseState
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeker.sheets.core.views.base.DialogBase
import com.maxkeppeler.sheets.state.StateView
import com.maxkeppeler.sheets.state.models.State
import com.maxkeppeler.sheets.state.models.StateConfig
import com.maxkeppeler.sheets.state.models.StateSelection

@Composable
fun DialogErrorState(message:String,closeSelection: () -> Unit) {

    val state = State.Failure(
        labelText = message,
    )
    val currentSample = rememberSaveable { mutableStateOf<Boolean?>(false) }
    val onReset = { currentSample.value }
    val sheetState = rememberUseCaseState(visible = true, onCloseRequest = { closeSelection()})
    StateDialog(
        state = sheetState,
        config = StateConfig(state = state),
        closeSelection = { currentSample.value= it}
    )



}


@Preview
@Composable
private fun DialogErrorStatePreview() {
    DialogErrorState("خطا",{})

}

@ExperimentalMaterial3Api
@Composable
fun StateDialog(
    state: UseCaseState,
    selection: StateSelection? = null,
    config: StateConfig,
    header: Header? = null,
    properties: DialogProperties = DialogProperties(
        dismissOnBackPress = false,
        dismissOnClickOutside = false,
    ),
    closeSelection : (Boolean) -> Unit
) {
    DialogBase(
        state = state,
        properties = properties,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            StateView(
                selection = selection,
                config = config,
                header = header,
            )
            OutlinedButton(
                onClick = {
                    closeSelection(true)
                },
                border = BorderStroke(1.dp, Color.Red),
                shape = RoundedCornerShape(50), // = 50% percent
                // or shape = CircleShape
                colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.Red)
            ) {
                Text(
                    text = "تایید",
                    fontSize = 15.sp,
                    color= Color.White
                )
            }
        }



    }
}