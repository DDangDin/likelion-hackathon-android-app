package com.hackathon.quki.presentation.components.home.filter

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hackathon.quki.core.utils.CustomRippleEffect.clickableWithoutRipple
import com.hackathon.quki.data.source.local.entity.CategoryEntity
import com.hackathon.quki.presentation.state.CategoryUiEvent

@Composable
fun HomeFilterBar(
    modifier: Modifier = Modifier,
    onOpenFilter: () -> Unit,
    filterList: List<CategoryEntity>,
    onFilterDelete: (CategoryUiEvent, CategoryEntity) -> Unit
) {

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.CenterHorizontally),
    ) {
        FilterIcon(
            modifier = Modifier
                .clickableWithoutRipple(
                    interactionSource = MutableInteractionSource(),
                    onClick = onOpenFilter
                )
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.Start),
        ) {
            items(filterList) { item ->
                FilterItemForDisplay(
                    filterName = item.name,
                    onDelete = { onFilterDelete(CategoryUiEvent.UnChecked, item) }
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun MainFilterBarPreview() {

    val filterList = listOf(
        CategoryEntity(
            name = "카페",
            code = "",
            desc = ""
        ),
        CategoryEntity(
            name = "카페",
            code = "",
            desc = ""
        ),
        CategoryEntity(
            name = "카페",
            code = "",
            desc = ""
        ),
    )

    HomeFilterBar(
        modifier = Modifier.fillMaxWidth(),
        onOpenFilter = {},
        filterList = filterList,
        onFilterDelete = { event, item ->}
    )
}