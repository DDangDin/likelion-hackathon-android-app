package com.hackathon.quki.presentation.components.qr_card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
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
import com.hackathon.quki.ui.theme.QukiColorGray_2
import com.hackathon.quki.ui.theme.QukiColorGray_3
import com.hackathon.quki.ui.theme.QukiColorMain

@Composable
fun QrCardView(
    modifier: Modifier = Modifier,
    qrCodeForApp: QrCodeForApp,
    onFavoriteClick: () -> Unit,
    isLongClick: Boolean,
    onDelete: () -> Unit
) {

    var isLoading by rememberSaveable {
        mutableStateOf(false)
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .height(110.dp)
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(0.85f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .size(90.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .shimmerEffect(isLoading),
                    model = qrCodeForApp.imageUrl,
                    contentDescription = "qr_image",
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Crop,
                    onLoading = { isLoading = true },
                    onSuccess = { isLoading = false }
                )
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(bottom = 4.dp),
                        text = qrCodeForApp.title,
                        fontSize = 14.sp,
                        fontWeight = FontWeight(700),
                        color = QukiColorGray_3,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = qrCodeForApp.storeId.storeName,
                        fontSize = 10.sp,
                        fontWeight = FontWeight(400),
                        color = QukiColorMain,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = qrCodeForApp.menus,
                        fontSize = 14.sp,
                        fontWeight = FontWeight(400),
                        color = QukiColorGray_2,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = qrCodeForApp.options,
                        fontSize = 12.sp,
                        fontWeight = FontWeight(400),
                        color = QukiColorGray_2,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
        Image(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(10.dp)
                .size(24.dp)
                .clip(RoundedCornerShape(50))
                .clickableWithoutRipple(
                    interactionSource = MutableInteractionSource(),
                    onClick = {
                        onFavoriteClick()
                    }
                ),
            painter = if (qrCodeForApp.isFavorite) {
                painterResource(id = R.drawable.img_favorite_y)
            } else {
                painterResource(id = R.drawable.img_favorite_n)
            },
            contentDescription = "favorite_image"
        )

        if (isLongClick) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White.copy(0.8f))
            ) {
                Icon(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(10.dp)
                        .size(23.dp)
                        .clickableWithoutRipple(
                            interactionSource = MutableInteractionSource(),
                            onClick = onDelete
                        ),
                    imageVector = ImageVector.vectorResource(R.drawable.ic_trash),
                    contentDescription = "trash",
                    tint = Color(0xFFA36363)
                )
            }
        }
    }
}

@Preview
@Composable
fun QrCardViewPreview() {
    QrCardView(
        qrCodeForApp = QrCodeForApp(
            id = 0,
            title = "내 QR 카드 (메가 커피) 1 ㅁㄴㅇㅁㅇㄴㅁㄴㅁㄴㅇㅁㅁㄴㅇㅁ",
            storeId = StoreId(
                storeId = 10,
                storeName = "메가커피"
            ),
            price = 1000,
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
                information = 1
            ),
            options = "옵션1, 옵션2, ...",
            menus = "",
            count = 0,
            category = ""
        ),
        onFavoriteClick = {},
        isLongClick = false,
        onDelete = {}
    )
}