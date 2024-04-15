package com.msa.onlineshopzar.ui.screen.profile

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector



data class AccountItem(val id:Int ,val name: String, val icon: ImageVector)
fun createAccountItems(): List<AccountItem> {
    return listOf(
        AccountItem(1,"اطلاعات حساب", Icons.Outlined.Person),
        AccountItem(2,"تغییر رمز عبور", Icons.Outlined.Lock),
        AccountItem(3,"آدرس‌ها", Icons.Outlined.LocationOn),
        AccountItem(4,"خروج از برنامه", Icons.Outlined.ExitToApp)
    )
}