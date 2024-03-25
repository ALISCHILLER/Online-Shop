package com.msa.onlineshopzar.ui.screen.resetPassword

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AmpStories
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.msa.onlineshopzar.R
import com.msa.onlineshopzar.ui.component.OTP_VIEW_TYPE_BORDER
import com.msa.onlineshopzar.ui.component.OtpView
import com.msa.onlineshopzar.ui.component.RoundedIconTextField
import com.msa.onlineshopzar.ui.theme.PlatinumSilver

@Composable
fun NewPasswordScreen() {
    Scaffold {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            Column(
                modifier = Modifier
                    .padding(it)
                    .background(color = PlatinumSilver)
                    .fillMaxSize()
                    .padding(start = 16.dp, end = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                var newPassword by remember { mutableStateOf("") }
                var repeatPassword by remember { mutableStateOf("") }
                Column(
                    modifier = Modifier
                        .width(328.dp)
                        .height(490.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.White),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceAround
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.logozar),
                        contentDescription = "logo",
                        modifier = Modifier
                            .size(110.dp, 82.dp)
                    )

                    RoundedIconTextField(
                        value = newPassword,
                        onValueChange = { newPassword = it },
                        label = "رمزعبورجدید",
                        icon = Icons.Default.Lock,
                        isPassword = true,
                        modifier = Modifier.padding(6.dp)
                    )
                    RoundedIconTextField(
                        value = repeatPassword,
                        onValueChange = { repeatPassword = it },
                        label = "تکراررمزعبور",
                        icon = Icons.Default.Lock,
                        isPassword = true,
                        modifier = Modifier.padding(6.dp)
                    )

                    Button(
                        onClick = {
                            // اینجا می‌توانید عملیات ورود را انجام دهید
                            // مثلا می‌توانید اطلاعات را به سرور ارسال کرده و ورود کاربر را بررسی کنید
                        },
                        modifier = Modifier
                            .padding(top = 16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                    ) {
                        Text("ادامه")
                    }
                }

            }

        }
    }
}


@Preview
@Composable
private fun NewPasswordScreenPreview() {
    NewPasswordScreen()
}