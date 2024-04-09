package com.msa.onlineshopzar.ui.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.state.StateDialog
import com.maxkeppeler.sheets.state.models.ProgressIndicator
import com.maxkeppeler.sheets.state.models.State
import com.maxkeppeler.sheets.state.models.StateConfig

@ExperimentalMaterial3Api
@Composable
fun DialogLoadingWait(closeSelection: () -> Unit) {
    val state = State.Loading(
        "منتظز بمانید .",
        ProgressIndicator.Linear()
    )
    StateDialog(
        state = rememberUseCaseState(visible = true, onCloseRequest = { closeSelection() }),
        config = StateConfig(state = state)
    )
}