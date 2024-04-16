@file:OptIn(ExperimentalMaterial3Api::class)

package com.msa.onlineshopzar.ui.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.msa.onlineshopzar.R
import com.msa.onlineshopzar.ui.component.DialogLoadingWait
import com.msa.onlineshopzar.ui.component.ErrorDialog
import com.msa.onlineshopzar.ui.component.RoundedIconTextField
import com.msa.onlineshopzar.ui.navigation.navgraph.Route
import com.msa.onlineshopzar.utils.dialog.Sample

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel()
) {

    val currentSample = rememberSaveable { mutableStateOf<Boolean?>(null) }

    // بازیابی نام کاربری و رمز عبور از SharedPreferences
    val savedUsername = viewModel.getSavedUsername()
    val savedPassword = viewModel.getSavedPassword()

    val state by viewModel.state.collectAsState()
    val onReset = { currentSample.value = state.isLoading }
    if (state.isLoading) {
        DialogLoadingWait(onReset)
    }
    state.error?.let {
        ErrorDialog(it, {}, false)
    }
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.backgroundlogin),
                contentDescription = "backgroundlogin",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )

            state.error?.let {
                ErrorDialog(
                    it,
                    { viewModel.clearState() },
                    false
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var username by remember { mutableStateOf(savedUsername) }
                var password by remember { mutableStateOf(savedPassword) }

                Card(
                    modifier = Modifier
                        .width(328.dp)
                        .padding(16.dp),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.logozar),
                            contentDescription = "logo",
                            modifier = Modifier
                                .size(110.dp, 82.dp)
                                .layoutId("logo")
                        )

//                        Text(
//                            modifier = Modifier.fillMaxWidth(),
//                            text = "وارد شوید!",
//                        )

                        RoundedIconTextField(
                            value = username,
                            onValueChange = { username = it },
                            label = "کد ملی",
                            icon = Icons.Default.Person
                        )

                        RoundedIconTextField(
                            value = password,
                            onValueChange = { password = it },
                            label = "رمز عبور",
                            icon = Icons.Default.Lock,
                            isPassword = true
                        )

                        Text(
                            text = "رمز عبور خود را فراموش کرده اید؟",
                            modifier = Modifier.padding(8.dp)
                        )

                        Button(
                            onClick = {
                                viewModel.getToken(username, password)
                                // اینجا می‌توانید عملیات ورود را انجام دهید
                                // مثلا می‌توانید اطلاعات را به سرور ارسال کرده و ورود کاربر را بررسی کنید
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                        ) {
                            Text("بریم که خرید رو شروع کنیم")
                            Icon(
                                imageVector = Icons.Default.ArrowBackIosNew,
                                contentDescription = "iconbutton"
                            )

                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
private fun LoginScreenPreview() {

    LoginScreen()
}