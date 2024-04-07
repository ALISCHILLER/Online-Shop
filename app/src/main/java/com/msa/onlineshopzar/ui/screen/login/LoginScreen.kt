package com.msa.onlineshopzar.ui.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msa.onlineshopzar.R
import com.msa.onlineshopzar.ui.component.RoundedIconTextField

@Composable
fun LoginScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.backgroundlogin),
            contentDescription = "backgroundlogin",
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var username by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }

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

                    Text(
                        text = "وارد شوید!",

                    )

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
                            // اینجا می‌توانید عملیات ورود را انجام دهید
                            // مثلا می‌توانید اطلاعات را به سرور ارسال کرده و ورود کاربر را بررسی کنید
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "iconbutton"
                        )
                        Text("بریم که خرید رو شروع کنیم")
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