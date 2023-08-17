package com.hackathon.quki.presentation.components.qr_card

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.hackathon.quki.R
import com.hackathon.quki.core.utils.CustomRippleEffect.clickableWithoutRipple
import com.hackathon.quki.core.utils.CustomRippleEffect.shimmerEffect
import com.hackathon.quki.data.source.remote.QrCodeForApp
import com.hackathon.quki.data.source.remote.api_server.StoreId
import com.hackathon.quki.data.source.remote.kiosk.KioskQrCode
import com.hackathon.quki.data.source.remote.kiosk.Options
import com.hackathon.quki.presentation.state.HomeQrUiEvent
import com.hackathon.quki.ui.theme.QukiColorBlack
import com.hackathon.quki.ui.theme.QukiColorGray_3
import com.hackathon.quki.ui.theme.QukiColorMain
import com.hackathon.quki.ui.theme.QukiColorShadow

@Composable
fun QrCardViewExpanded(
    modifier: Modifier = Modifier,
    likeCount: Int,
    qrCodeForApp: QrCodeForApp,
    onHomeQrUiEvent: (HomeQrUiEvent.CheckFavorite) -> Unit,
    enabledFavorite: Boolean,
    text: String = "",
    onTextChanged: (String) -> Unit = {}
) {

    val context = LocalContext.current

    var isLoading by rememberSaveable {
        mutableStateOf(true)
    }
    var isCheckFavoriteCopy by rememberSaveable {
        mutableStateOf(qrCodeForApp.isFavorite)
    }

    var isEditTitle by rememberSaveable {
        mutableStateOf(false)
    }

    Box(
        modifier = modifier
            .wrapContentSize()
            .shadow(
                elevation = 15.dp,
                shape = RoundedCornerShape(10.dp),
                spotColor = QukiColorShadow,
                ambientColor = QukiColorShadow
            )
            .background(Color.White, RoundedCornerShape(10.dp))
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(25.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                15.dp,
                alignment = Alignment.CenterVertically
            )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
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
                        painter = painterResource(id = R.drawable.ic_logo),
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
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
//                    LikeIcon(
//                        likeCount = likeCount
//                    )
                    if (enabledFavorite) {
                        Image(
                            modifier = Modifier
                                .size(25.dp)
                                .padding(start = 5.dp, bottom = 5.dp)
                                .clickableWithoutRipple(
                                    interactionSource = MutableInteractionSource(),
                                    onClick = {
//                                        val userId = CustomSharedPreference(context).getUserPrefs(LOGIN_TOKEN)
//                                        onHomeQrUiEvent(
//                                            HomeQrUiEvent.CheckFavorite(
//                                                userId,
//                                                qrCodeForApp
//                                            )
//                                        )
//                                        isCheckFavoriteCopy = !isCheckFavoriteCopy
                                    }
                                ),
                            painter = if (isCheckFavoriteCopy) {
                                painterResource(id = R.drawable.img_favorite_y_no_bg)
                            } else {
                                painterResource(id = R.drawable.img_favorite_n_no_bg)
                            },
                            contentDescription = "favorite"
                        )
                    }
                }
            }
            AsyncImage(
                modifier = Modifier
                    .size(280.dp)
                    .shimmerEffect(isLoading),
                model = qrCodeForApp.imageUrl,
                contentDescription = "qrcode image",
                contentScale = ContentScale.Crop,
                onLoading = { isLoading = true },
                onSuccess = { isLoading = false }
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
//                    Text(
//                        text = qrCodeForApp.title,
//                        fontSize = 16.sp,
//                        fontWeight = FontWeight(700),
//                        color = QukiColorGray_3,
//                        maxLines = 1
//                    )
                    BasicTextField(
                        modifier = Modifier.onFocusChanged {
                            if (enabledFavorite) {
                                if (it.isFocused) {
                                    isEditTitle = true
                                }
                                if (!it.hasFocus) {
                                    isEditTitle = false
                                }
                            }
                        },
                        value = text,
                        onValueChange = { onTextChanged(it) },
                        singleLine = true,
                        maxLines = 1,
                        cursorBrush = SolidColor(QukiColorBlack),
                        textStyle = LocalTextStyle.current.copy(
                            color = QukiColorGray_3,
                            fontSize = 15.sp,
                            fontWeight = FontWeight(700),
                            textAlign = TextAlign.Center
                        ),
                        decorationBox = { innerTextField ->
                            Box(
                                Modifier
                                    .padding(7.dp)
                                    .border(
                                        color = if (isEditTitle) QukiColorGray_3.copy(0.3f) else Color.Transparent,
                                        shape = RoundedCornerShape(10.dp),
                                        width = 0.5.dp
                                    )
                            ) {
                                Row(
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                        .padding(7.dp),
                                    horizontalArrangement = Arrangement.spacedBy(
                                        5.dp,
                                        alignment = Alignment.CenterHorizontally
                                    ),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    innerTextField()
                                    Icon(
                                        modifier = Modifier
                                            .width(14.dp)
                                            .height(16.dp)
                                            .clickableWithoutRipple(
                                                interactionSource = MutableInteractionSource(),
                                                onClick = {
                                                    if (enabledFavorite) {
                                                        isEditTitle = !isEditTitle
                                                    }
                                                }
                                            ),
                                        imageVector = ImageVector.vectorResource(R.drawable.ic_edit),
                                        contentDescription = "edit",
                                        tint = Color(0xFFD9D9D9)
                                    )
                                }
                            }
                        }
                    )
                }
                Text(
                    text = qrCodeForApp.storeId.storeName,
                    fontSize = 15.sp,
                    fontWeight = FontWeight(700),
                    color = QukiColorMain
                )
            }

            LaunchedEffect(key1 = Unit) {
                Log.d("QrCard_Log", qrCodeForApp.menus)
                Log.d("QrCard_Log", qrCodeForApp.options)
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                text = "${qrCodeForApp.price} 원",
                fontSize = 18.sp,
                fontWeight = FontWeight(700),
                color = QukiColorGray_3,
                textAlign = TextAlign.End
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray.copy(0.15f), RoundedCornerShape(10.dp)),
            ) {
                LazyColumn(
                    modifier = Modifier.padding(horizontal = 5.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    val menuList = qrCodeForApp.menus.split("-")
                    val optionList = qrCodeForApp.options.split("-")

                    items(menuList.size) { index ->

                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = menuList[index],
                            fontSize = 18.sp,
                            fontWeight = FontWeight(700),
                            color = QukiColorGray_3,
                            textAlign = TextAlign.Start
                        )
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = optionList[index],
                            fontSize = 16.sp,
                            fontWeight = FontWeight(700),
                            color = QukiColorMain,
                            textAlign = TextAlign.Start
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun QrCardViewExpandedPreview() {
    QrCardViewExpanded(
        modifier = Modifier.fillMaxSize(),
        qrCodeForApp = QrCodeForApp(
            id = 0,
            title = "내 QR 카드 (메가커피)",
            storeId = StoreId(
                storeId = 10,
                storeName = "메가커피"
            ),
            price = 6200,
            imageUrl = "https://api.qrserver.com/v1/create-qr-code/?size=300x300&data=qr test adsadsa",
            isFavorite = false,
            kioskEntity = KioskQrCode(
                id = 3,
                price = 1000,
                count = 1,
                type = "커피",
                url = "", // QrImage
                options = Options("", "", ""),
                ice = false,
                cream = false,
                information = 1,
            ),
            options = "옵션1-옵션2-옵션3",
            menus = "메뉴메뉴메뉴1-메뉴메뉴메뉴2-메뉴메뉴메뉴3",
            count = 0,
            category = ""
        ),
        onHomeQrUiEvent = {},
        likeCount = 122,
        enabledFavorite = false,
        text = "",
        onTextChanged = {}
    )
}