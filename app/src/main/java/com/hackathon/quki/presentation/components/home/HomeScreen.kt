package com.hackathon.quki.presentation.components.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hackathon.quki.R
import com.hackathon.quki.data.source.local.entity.CategoryEntity
import com.hackathon.quki.presentation.components.home.filter.HomeFilterBar
import com.hackathon.quki.presentation.state.CategoryUiEvent
import com.hackathon.quki.ui.theme.QukiColorBackground
import com.hackathon.quki.ui.theme.QukiColorGray_1

@Composable
fun HomeScreen(
    searchText: String,
    onSearchTextChanged: (String) -> Unit,
    onOpenFilter: () -> Unit,
    filterList: List<CategoryEntity>,
    onFilterDelete: (CategoryUiEvent, CategoryEntity) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(QukiColorBackground)
    ) {
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
            HomeFilterBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp, end = 25.dp, top = 22.dp, bottom = 13.dp),
                onOpenFilter = onOpenFilter,
                filterList = filterList,
                onFilterDelete = { event, item ->
                    onFilterDelete(event, item)
                }
            )
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .padding(horizontal = 25.dp)
                    .background(QukiColorGray_1)
            )

            // QrCards

        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    HomeScreen(
        searchText = "",
        onSearchTextChanged = {},
        onOpenFilter = {},
        filterList = emptyList(),
        onFilterDelete = { event, item ->}
    )
}