package com.hackathon.quki.presentation.components.home.filter_screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hackathon.quki.R
import com.hackathon.quki.data.source.local.entity.CategoryEntity
import com.hackathon.quki.presentation.components.common.CommonTopBar
import com.hackathon.quki.presentation.state.CategoryUiEvent
import com.hackathon.quki.presentation.state.FilterUiEvent

@Composable
fun FilterScreen(
    modifier: Modifier = Modifier,
    categoryList: List<CategoryEntity>,
    onClose: () -> Unit,
    categoryUiEvent: (CategoryUiEvent, CategoryEntity) -> Unit,
    getFilteredQrCards: () -> Unit,
    filterUiEvent: (FilterUiEvent) -> Unit
) {

    LaunchedEffect(key1 = categoryList) {
        Log.d("categoryList_Log", "categoryList updated")
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        CommonTopBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp, vertical = 32.dp),
            onClose = onClose,
            title = stringResource(id = R.string.home_filter_title_text)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 35.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                30.dp,
                alignment = Alignment.CenterVertically
            )
        ) {
            FilterContent(
                title = stringResource(id = R.string.filter_content_align_title),
                firstSubTitle = stringResource(id = R.string.filter_sub_content_align_text_1),
                secondSubTitle = stringResource(id = R.string.filter_sub_content_align_text_2),
                onFirstSubTitleClick = { filterUiEvent(FilterUiEvent.FavoriteAlign) },
                onSecondSubTitleClick = { filterUiEvent(FilterUiEvent.DefaultAlign) }
            )
            FilterContent(
                title = stringResource(id = R.string.filter_content_view_title),
                firstSubTitle = stringResource(id = R.string.filter_sub_content_view_text_1),
                secondSubTitle = stringResource(id = R.string.filter_sub_content_view_text_2),
                onFirstSubTitleClick = { filterUiEvent(FilterUiEvent.FavoriteAlign) },
                onSecondSubTitleClick = { filterUiEvent(FilterUiEvent.DefaultAlign) }
            )
            CategoryContent(
                title = stringResource(id = R.string.filter_content_category_title),
                categoryList = categoryList,
                onClick = { it, item ->
                    // it -> Check, UnCheck
                    if (it) {
                        categoryUiEvent(CategoryUiEvent.Checked, item)
                    } else {
                        categoryUiEvent(CategoryUiEvent.UnChecked, item)
                    }
                    getFilteredQrCards()
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FilterScreenPreview() {
    FilterScreen(
        modifier = Modifier.fillMaxSize(),
        categoryList = emptyList(),
        onClose = {},
        categoryUiEvent = { event, item -> },
        getFilteredQrCards = {},
        filterUiEvent = {}
    )
}