package com.hackathon.quki.presentation.components.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hackathon.quki.R
import com.hackathon.quki.presentation.components.home.filter.CustomTextField
import com.hackathon.quki.ui.theme.QukiColorBlack
import com.hackathon.quki.ui.theme.QukiColorGray_1
import com.hackathon.quki.ui.theme.QukiColorGray_3
import com.hackathon.quki.ui.theme.QukiColorMain
import com.hackathon.quki.ui.theme.QukiColorShadow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    modifier: Modifier = Modifier,
    @DrawableRes logoIcon: Int,
    @DrawableRes optionIcon: Int,
    @DrawableRes optionIcon2: Int,
    searchText: String,
    onSearchTextChanged: (String) -> Unit
) {

    // 나중에 focus 처리 하기
//    val focusRequester = rememberSaveable() {
//        FocusRequester()
//    }

    var isSearchBarFocused by rememberSaveable {
        mutableStateOf(false)
    }

    Box(
        modifier = modifier
            .shadow(elevation = 15.dp, spotColor = QukiColorShadow, ambientColor = QukiColorShadow)
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                18.dp,
                alignment = Alignment.CenterVertically
            )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        3.dp,
                        alignment = Alignment.CenterHorizontally
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier.size(25.dp),
                        painter = painterResource(id = logoIcon),
                        contentDescription = "logo icon",
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = stringResource(id = R.string.app_name),
                        fontSize = 20.sp,
                        fontWeight = FontWeight(700),
                        color = QukiColorBlack
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        30.dp,
                        alignment = Alignment.CenterHorizontally
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
//                    IconButton(
//                        modifier = Modifier.size(20.dp),
//                        onClick = { /*TODO*/ }
//                    ) {
//                        Icon(
//                            modifier = Modifier.size(20.dp),
//                            imageVector = ImageVector.vectorResource(optionIcon),
//                            contentDescription = "optionIcon_1",
//                            tint = QukiColorBlack
//                        )
//                    }
                    IconButton(
                        modifier = Modifier.size(20.dp),
                        onClick = { }
                    ) {
                        Icon(
                            modifier = Modifier.size(20.dp),
                            imageVector = ImageVector.vectorResource(optionIcon2),
                            contentDescription = "optionIcon_1",
                            tint = QukiColorBlack
                        )
                    }
                }
            }

            CustomTextField(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .fillMaxWidth()
                    .height(40.dp)
                    .background(QukiColorGray_1)
                    .border(
                        1.5.dp,
                        if (isSearchBarFocused) {
                            QukiColorMain
                        } else {
                            Color.Transparent
                        },
                        RoundedCornerShape(10.dp)
                    )
                    .onFocusChanged {
                        isSearchBarFocused = it.isFocused
                    },
                text = searchText,
                onTextChanged = { onSearchTextChanged(it) },
                textColor = QukiColorGray_3,
                fontSize = 15.sp,
                placeholderText = stringResource(id = R.string.home_tf_placeholder_text),
                leadingIcon = {
                    Icon(
                        modifier = Modifier.padding(start = 11.dp),
                        imageVector = Icons.Default.Search,
                        contentDescription = "tf_search_icon",
                        tint = QukiColorGray_3
                    )
                }
            )

//                    colors = TextFieldDefaults.outlinedTextFieldColors(
//                        cursorColor = QukiColorGray_3,
//                        textColor = QukiColorBlack,
//                        containerColor = QukiColorGray_1,
//                        focusedBorderColor = QukiColorMain,
//                        unfocusedBorderColor = QukiColorGray_1,
//                    )

        }
    }
}

@Preview
@Composable
fun MainTopBarPreview() {
    HomeTopBar(
        logoIcon = R.drawable.ic_logo,
        optionIcon = R.drawable.ic_help,
        optionIcon2 = R.drawable.ic_setting,
        searchText = "",
        onSearchTextChanged = {}
    )
}