package com.hackathon.quki.presentation.components.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hackathon.quki.R
import com.hackathon.quki.ui.theme.QukiColorBackground

@Composable
fun HomeScreen(
    searchText: String,
    onSearchTextChanged: (String) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize().background(QukiColorBackground)) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            HomeTopBar(
                modifier = Modifier.fillMaxWidth(),
                logoIcon = R.drawable.ic_logo,
                optionIcon = R.drawable.ic_help,
                optionIcon2 = R.drawable.ic_setting,
                searchText = searchText,
                onSearchTextChanged = { onSearchTextChanged(it) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    HomeScreen(
        searchText = "",
        onSearchTextChanged = {}
    )
}