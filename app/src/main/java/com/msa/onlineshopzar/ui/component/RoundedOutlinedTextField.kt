package com.msa.onlineshopzar.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RoundedIconTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    icon: ImageVector,
    isPassword: Boolean = false,
    modifier: Modifier = Modifier
) {
    var passwordVisibility by remember { mutableStateOf(!isPassword) }

    Column(modifier = modifier) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(text = label) },
            singleLine = true,
            trailingIcon = {
                if (isPassword) {
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(
                        imageVector = if (passwordVisibility) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = "Toggle password visibility"
                    )
                }
                }else {
                    Icon(
                        imageVector = icon,
                        contentDescription = null
                    )
                }
            },
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = { /* Handle Done action */ }),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun RoundedOutlinedTextFieldPreview() {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        RoundedIconTextField(
            value = username,
            onValueChange = { username = it },
            label = "نام کاربری",
            icon = Icons.Default.Person,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        RoundedIconTextField(
            value = password,
            onValueChange = { password = it },
            label = "رمز عبور",
            icon = Icons.Default.Lock,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Button(
            onClick = {
                // اینجا می‌توانید عملیات ورود را انجام دهید
                // مثلا می‌توانید اطلاعات را به سرور ارسال کرده و ورود کاربر را بررسی کنید
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("ورود")
        }
    }
}