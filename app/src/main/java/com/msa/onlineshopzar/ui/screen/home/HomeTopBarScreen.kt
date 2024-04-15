@file:OptIn(ExperimentalMaterial3Api::class)

package com.msa.onlineshopzar.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.msa.onlineshopzar.data.local.entity.UserModelEntity
import com.msa.onlineshopzar.ui.component.ButtonAlarm
import com.msa.onlineshopzar.ui.theme.PlatinumSilver

@ExperimentalMaterial3Api
@Composable
fun HomeTopBar(
    userModelEntity: UserModelEntity,
) {
   val viewModel: HomeViewModel = hiltViewModel()
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Column(
            modifier = Modifier,
        ) {
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .background(color = PlatinumSilver)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        imageVector = Icons.Default.Person,
                        contentDescription = "User Profile Image",
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape)
                            .border(width = 1.dp, color = Color.Gray, shape = CircleShape)
                    )

                    Column {
                        Text(
                            text = userModelEntity.customerName
                        )
                        Text(
                            text = "(کد مشتری:${userModelEntity.customerCode})",
                            fontSize = 9.sp
                        )
                    }


                }
                ButtonAlarm(
                    icon = Icons.Default.Notifications,
                    onClick = { /* Do something on click */ },
                    hasMessage = true
                )

            }

            var text by rememberSaveable { mutableStateOf("") }
            var active by rememberSaveable { mutableStateOf(false) }

            Box(
                Modifier
                    .fillMaxWidth()
                    .semantics { isTraversalGroup = true }) {

                DockedSearchBar(
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth()
                        .align(Alignment.TopCenter)
                        .semantics { traversalIndex = -1f },
                    query = text,
                    onQueryChange = {
                        text = it
                        // Call search function here with the current query
                        viewModel.searchProduct(it)
                                    },
                    onSearch = { newQuery ->
                        viewModel.searchProduct(newQuery)
                    },
                    active = false,
                    onActiveChange = { active = it },
                    placeholder = {
                        Text(text = "جستجو")
                    },
                    leadingIcon = {
                        Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
                    },
                    trailingIcon = {
                        Row {
                            IconButton(onClick = { /* open mic dialog */ }) {
                                Icon(
                                    imageVector = Icons.Default.Mic,
                                    contentDescription = "Mic"
                                )
                            }
                            if (active) {
                                IconButton(
                                    onClick = { if (text.isNotEmpty()) text = "" else active = false }
                                ) {
                                    Icon(imageVector = Icons.Filled.Close, contentDescription = "Close")
                                }
                            }
                        }
                    }
                ) {

                }
            }
        }
    }

}

@Preview
@Composable
private fun HomeTopBarPreview() {
    HomeTopBar(
        UserModelEntity(
            customerCode = "234",
            customerName = "dsf",
            mobile = "093",
            brache = "sds",
            city = "sdf",
        )
    )
}