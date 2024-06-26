package com.msa.onlineshopzar.ui.screen.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.msa.onlineshopzar.ui.screen.login.LoginViewModel
import com.msa.onlineshopzar.ui.theme.barcolor
import javax.annotation.meta.When
import com.msa.onlineshopzar.ui.component.AlertDialogConfirm as AlertDialogConfirm1

@Composable
fun ItemProfile(
    item : AccountItem,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        var chack by remember { mutableStateOf(false) }
        if (chack)
        AlertDialogConfirm1(
            onConfirmation = {
                viewModel.navigateToLogin()
                chack=false
            },
            onDismissRequest = {chack=false},
            message = "آیا می‌خواهید از برنامه خارج شوید؟"
        )

        OutlinedButton(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .padding(15.dp)
            ,
            onClick = {
                 when(item.id){
                     1-> {
                         viewModel.navigateToAccount()
                     }
                     4->chack=true
                 }
            },
            border = BorderStroke(1.dp, barcolor),
            shape = RoundedCornerShape(50), // = 50% percent
            // or shape = CircleShape
            colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White),

            ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            )
            {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Image(
                         item.icon,
                        contentDescription = "Location Icon"
                    )
                    Text(
                        text = item.name,
                    )
                }

                Image(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "ArrowBackIosNew"
                )

            }
        }
    }
}

@Preview
@Composable
private fun ItemProfilePrivew() {
    val accountItems = createAccountItems()
    LazyColumn {
        items(accountItems){
            ItemProfile(it)
        }
    }
}

