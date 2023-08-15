package com.hackathon.quki.presentation.components.home

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hackathon.quki.R
import com.hackathon.quki.core.utils.CustomRippleEffect.clickableWithoutRipple
import com.hackathon.quki.data.source.local.entity.CategoryEntity
import com.hackathon.quki.data.source.remote.QrCodeForApp
import com.hackathon.quki.presentation.components.home.filter.HomeFilterBar
import com.hackathon.quki.presentation.components.qr_card.QrCardView
import com.hackathon.quki.presentation.state.CategoryUiEvent
import com.hackathon.quki.presentation.state.HomeQrUiEvent
import com.hackathon.quki.ui.theme.QukiColorBackground
import com.hackathon.quki.ui.theme.QukiColorGray_1
import com.hackathon.quki.ui.theme.QukiColorGray_3
import com.hackathon.quki.ui.theme.QukiColorShadow

@Composable
fun HomeScreen(
    searchText: String,
    onSearchTextChanged: (String) -> Unit,
    onOpenFilter: () -> Unit,
    filterList: List<CategoryEntity>,
    onFilterDelete: (CategoryUiEvent, CategoryEntity) -> Unit,
    qrCodeList: List<QrCodeForApp>,
    onEvent: (HomeQrUiEvent) -> Unit,
    onOpenQrCard: () -> Unit,
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(QukiColorBackground)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp, alignment = Alignment.Top)
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
                    .padding(horizontal = 25.dp),
                onOpenFilter = onOpenFilter,
                filterList = filterList,
                onFilterDelete = { event, item ->
                    onFilterDelete(event, item)
                }
            )
//            Divider(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(1.dp)
//                    .padding(horizontal = 25.dp)
//                    .background(QukiColorGray_1)
//            )

            // QrCards
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 2.dp),
                    text = stringResource(id = R.string.home_qr_card_list_title),
                    fontSize = 14.sp,
                    fontWeight = FontWeight(700),
                    color = QukiColorGray_3
                )
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(QukiColorGray_1)
                )
                LazyColumn(
                    modifier = Modifier,
//                        .padding(bottom = 56.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(top = 10.dp, bottom = 58.dp)
                ) {
                    items(qrCodeList) { qrCode ->

                        QrCardView(
                            modifier = Modifier
                                .fillMaxWidth()
                                .shadow(
                                    elevation = 7.dp,
                                    spotColor = QukiColorShadow,
                                    ambientColor = QukiColorShadow,
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .clickableWithoutRipple(
                                    interactionSource = MutableInteractionSource(),
                                    onClick = {
                                        onEvent(HomeQrUiEvent.OpenQrCard(qrCode))
                                        onOpenQrCard()
                                    }
                                ),
//                                .clickableWithoutRipple(
//                                    interactionSource = MutableInteractionSource(),
//                                    onClick = { onQrCardClick(qrCode) }
//                                ),
                            qrCodeForApp = qrCode,
                            onFavoriteClick = {}
                        )
                    }
                }
            }
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
        onFilterDelete = { event, item -> },
        qrCodeList = emptyList(),
        onEvent = {},
        onOpenQrCard = {},
    )
}