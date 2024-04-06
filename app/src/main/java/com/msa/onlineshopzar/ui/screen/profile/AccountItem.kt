package com.msa.onlineshopzar.ui.screen.profile

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector



data class AccountItem(val name: String, val icon: ImageVector)
fun createAccountItems(): List<AccountItem> {
    return listOf(
        AccountItem("اطلاعات حساب", Icons.Outlined.Person),
        AccountItem("تغییر رمز عبور", Icons.Outlined.Lock),
        AccountItem("آدرس‌ها", Icons.Outlined.LocationOn),
        AccountItem("خروج از برنامه", Icons.Outlined.ExitToApp)
    )
}