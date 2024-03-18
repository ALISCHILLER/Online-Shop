@file:OptIn(ExperimentalMaterial3Api::class)

package com.msa.onlineshopzar.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.msa.onlineshopzar.ui.component.ButtonAlarm

@ExperimentalMaterial3Api
@Composable
fun HomeTopBar() {

        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            Column(
                modifier = Modifier,
            ) {
                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "Location"
                        )
                        Text(
                            text = "ایران-اهواز"
                        )
                        Image(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = "Location"
                        )
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
                    SearchBar(
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .semantics { traversalIndex = -1f },
                        query = text,
                        onQueryChange = { text = it },
                        onSearch = { active = false },
                        active = active,
                        onActiveChange = {
                            active = it
                        },
                        placeholder = { Text("محصول مورد نظر را جستجو کنید") },
                        colors=SearchBarDefaults.colors(
                            containerColor= Color.White
                        ),
                        leadingIcon = {
                            Icon(
                                Icons.Default.Search,
                                contentDescription = null,
                                tint = Color.Red
                            )
                        },
                        trailingIcon = { Icon(Icons.Default.Mic, contentDescription = null) },
                    ) {
                        repeat(4) { idx ->
                            val resultText = "Suggestion $idx"
                            ListItem(
                                headlineContent = { Text(resultText) },
                                supportingContent = { Text("Additional info") },
                                leadingContent = {
                                    Icon(
                                        Icons.Filled.Star,
                                        contentDescription = null
                                    )
                                },
                                modifier = Modifier
                                    .clickable {
                                        text = resultText
                                        active = false
                                    }
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 4.dp)
                            )
                        }
                    }
                }

//                Row(
//                    modifier = Modifier
//                        .padding(10.dp)
//                        .fillMaxWidth()
//                    ,
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.SpaceBetween
//                ){
//                    Text(
//                        text = "دسته بندی ها",
//                        color = Color.Black
//                    )
//
//                    Row(
//                        verticalAlignment = Alignment.CenterVertically,
//
//                        ) {
//                        Text(
//                            text = "مشاهده همه",
//                            color = Color.Red
//                        )
//                        Icon(
//                            imageVector = Icons.Default.ArrowBackIosNew,
//                            contentDescription = "" ,
//                            tint = Color.Red
//                        )
//                    }
//
//                }

            }
        }

}

@Preview
@Composable
private fun HomeTopBarPreview() {
    HomeTopBar()
}