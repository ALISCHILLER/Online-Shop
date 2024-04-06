package com.msa.onlineshopzar.ui.screen.accountInformation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.msa.onlineshopzar.ui.component.RoundedIconTextField
import com.msa.onlineshopzar.ui.screen.detailsProduct.TopBarDetails
import com.msa.onlineshopzar.ui.screen.profile.ItemProfile
import com.msa.onlineshopzar.ui.theme.PlatinumSilver

@Composable
fun AccountInformationScreen() {

    Scaffold(
        modifier = Modifier
            .background(color = PlatinumSilver),
        topBar = {
            TopBarDetails("اطلاعات حساب")
        }
    ) {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {

            Column(
                modifier = Modifier
                    .padding(it)
                    .background(color = PlatinumSilver)
                    .fillMaxSize()
                 ,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween

            )
            {
                Column(
                    modifier = Modifier
                        .width(328.dp)
                        .height(490.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.White),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    RoundedIconTextField(
                        value = "username",
                        onValueChange = { },
                        label = "نام و نام خانوادگی",
                        icon = Icons.Outlined.Person,
                        modifier = Modifier.padding(3.dp),
                        typeEnabled = false
                    )
                    RoundedIconTextField(
                        value = "username",
                        onValueChange = { },
                        label = "شماره تماس",
                        icon = Icons.Outlined.Phone,
                        modifier = Modifier.padding(6.dp),
                        typeEnabled = false
                    )
                    RoundedIconTextField(
                        value = "username",
                        onValueChange = { },
                        label = "شعبه پخش",
                        icon = Icons.Outlined.Business,
                        modifier = Modifier.padding(6.dp),
                        typeEnabled = false
                    )
                    RoundedIconTextField(
                        value = "username",
                        onValueChange = { },
                        label = "شهر",
                        icon = Icons.Outlined.LocationOn,
                        modifier = Modifier.padding(6.dp),
                        typeEnabled = false
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun AccountInformationPreview() {
    AccountInformationScreen()
}