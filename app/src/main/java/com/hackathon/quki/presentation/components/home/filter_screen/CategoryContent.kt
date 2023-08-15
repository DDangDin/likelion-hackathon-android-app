package com.hackathon.quki.presentation.components.home.filter_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hackathon.quki.R
import com.hackathon.quki.data.source.local.entity.CategoryEntity
import com.hackathon.quki.ui.theme.QukiColorBlack

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoryContent(
    modifier: Modifier = Modifier,
    title: String,
    categoryList: List<CategoryEntity>,
    onClick: (Boolean, CategoryEntity) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp, alignment = Alignment.CenterVertically)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            fontSize = 14.sp,
            fontWeight = FontWeight(700),
            color = QukiColorBlack,
            textAlign = TextAlign.Start
        )

        LazyVerticalGrid(
            modifier = Modifier.fillMaxWidth(),
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp, alignment = Alignment.Start),
            contentPadding = PaddingValues(10.dp),
        ) {
            items(categoryList) { item ->
                FilterItem(
                    filterName = item.name,
                    onClick = {
                        // it -> 클릭에 따라 true, false
                        onClick(it, item)
                    },
                    alreadyChecked = item.isFilterChecked
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryContentPreview() {

    val categoryList = listOf(
        CategoryEntity(
            name = "카페",
            desc = "",
            code = "",
            isFilterChecked = true
        ),
        CategoryEntity(
            name = "패스트푸드",
            desc = "",
            code = "",
            isFilterChecked = true
        ),
        CategoryEntity(
            name = "도시락",
            desc = "",
            code = "",
            isFilterChecked = true
        ),
        CategoryEntity(
            name = "중식",
            desc = "",
            code = ""
        ),
        CategoryEntity(
            name = "일식",
            desc = "",
            code = ""
        ),
        CategoryEntity(
            name = "양식",
            desc = "",
            code = ""
        ),
    )

    CategoryContent(
        title = stringResource(id = R.string.filter_content_category_title),
        categoryList = categoryList,
//        onCheck = {},
//        onUnCheck = {},
        onClick = { it, item -> }
    )
}