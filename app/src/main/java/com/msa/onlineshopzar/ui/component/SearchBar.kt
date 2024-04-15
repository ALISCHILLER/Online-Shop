package com.msa.onlineshopzar.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.traversalIndex
import com.msa.onlineshopzar.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarM3() {

    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    val searchHistory = listOf("Android", "Kotlin", "Compose", "Material Design", "GPT-4")

    SearchBar(
        query = query,
        onQueryChange = { query = it },
        onSearch = { newQuery ->
            println("Performing search on query: $newQuery")
        },
        active = active,
        onActiveChange = { active = it },
        placeholder = {
            Text(text = "Search")
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
                        onClick = { if (query.isNotEmpty()) query = "" else active = false }
                    ) {
                        Icon(imageVector = Icons.Filled.Close, contentDescription = "Close")
                    }
                }
            }
        }
    ) {
        searchHistory.takeLast(3).forEach { item ->
            ListItem(
                modifier = Modifier.clickable { query = item },
                headlineContent = { Text(text = item) },
                leadingContent = {
                    Icon(
                        imageVector = Icons.Default.History,
                        contentDescription = null
                    )
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DockedSearchBarM3() {
    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    val searchHistory = listOf("Android", "Kotlin", "Compose", "Material Design", "GPT-4")

    DockedSearchBar(
        query = query,
        onQueryChange = { query = it },
        onSearch = { newQuery ->
            println("Performing search on query: $newQuery")
        },
        active = active,
        onActiveChange = { active = it },
        placeholder = {
            Text(text = "Search")
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
                        onClick = { if (query.isNotEmpty()) query = "" else active = false }
                    ) {
                        Icon(imageVector = Icons.Filled.Close, contentDescription = "Close")
                    }
                }
            }
        }
    ) {
        searchHistory.takeLast(3).forEach { item ->
            ListItem(
                modifier = Modifier.clickable { query = item },
                headlineContent = { Text(text = item) },
                leadingContent = {
                    Icon(
                        imageVector = Icons.Default.History,
                        contentDescription = null
                    )
                }
            )
        }
    }
}




//SearchBar(
//modifier = Modifier
//.padding(5.dp)
//.fillMaxWidth()
//.align(Alignment.TopCenter)
//.semantics { traversalIndex = -1f },
//query = text,
//onQueryChange = { text = it },
//onSearch = { active = false },
//active = active,
//onActiveChange = {
//    active = it
//    if (active)
//        viewModel.searchProduct(text)
//},
//placeholder = { Text("محصول مورد نظر را جستجو کنید") },
//colors = SearchBarDefaults.colors(
//containerColor = Color.White
//),
//leadingIcon = {
//    Icon(
//        Icons.Default.Search,
//        contentDescription = null,
//        tint = Color.Red
//    )
//},
//trailingIcon = { Icon(Icons.Default.Mic, contentDescription = null) },
//) {
////                    repeat(4) { idx ->
////                        val resultText = "Suggestion $idx"
////                        ListItem(
////                            headlineContent = { Text(resultText) },
////                            supportingContent = { Text("Additional info") },
////                            leadingContent = {
////                                Icon(
////                                    Icons.Filled.Star,
////                                    contentDescription = null
////                                )
////                            },
////                            modifier = Modifier
////                                .clickable {
////                                    text = resultText
////                                    active = false
////                                }
////                                .fillMaxWidth()
////                                .padding(horizontal = 16.dp, vertical = 4.dp)
////                        )
////                    }
//}