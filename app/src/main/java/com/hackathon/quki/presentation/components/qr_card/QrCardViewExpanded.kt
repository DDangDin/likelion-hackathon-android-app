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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
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
import com.hackathon.quki.core.common.Constants.megaCoffeeMenu
import com.hackathon.quki.core.utils.CustomRippleEffect.clickableWithoutRipple
import com.hackathon.quki.data.source.remote.Content
import com.hackathon.quki.data.source.remote.QrCode
import com.hackathon.quki.ui.theme.QukiColorBlack
import com.hackathon.quki.ui.theme.QukiColorGray_3
import com.hackathon.quki.ui.theme.QukiColorMain
import com.hackathon.quki.ui.theme.QukiColorShadow

@Composable
fun QrCardViewExpanded(
    modifier: Modifier = Modifier,
    likeCount: Int,
    qrCode: QrCode,
    onFavoriteClick: () -> Unit
) {

    var isImageLoading by rememberSaveable {
        mutableStateOf(true)
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
                    LikeIcon(
                        likeCount = likeCount
                    )
                    Image(
                        modifier = Modifier
                            .size(25.dp)
                            .clickableWithoutRipple(
                                interactionSource = MutableInteractionSource(),
                                onClick = onFavoriteClick
                            ),
                        painter = if (qrCode.isFavorite) {
                            painterResource(R.drawable.img_favorite_y)
                        } else {
                            painterResource(R.drawable.img_favorite_n)
                        },
                        contentDescription = "favorite"
                    )
                }
            }
            AsyncImage(
                modifier = Modifier.size(280.dp),
                model = qrCode.image,
                contentDescription = "qrcode image",
                contentScale = ContentScale.Crop,
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(7.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = qrCode.title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight(700),
                        color = QukiColorGray_3
                    )
                    Icon(
                        modifier = Modifier
                            .width(10.dp)
                            .height(12.dp),
                        imageVector = ImageVector.vectorResource(R.drawable.ic_edit),
                        contentDescription = "edit",
                        tint = Color(0xFFD9D9D9)
                    )
                }
                Text(
                    text = "가게 명",
                    fontSize = 14.sp,
                    fontWeight = FontWeight(700),
                    color = QukiColorMain
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = megaCoffeeMenu[qrCode.content.id]!!,
                    fontSize = 18.sp,
                    fontWeight = FontWeight(700),
                    color = QukiColorGray_3
                )
                Text(
                    text = "옵션1, 옵션2, 옵션3, ...",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(700),
                    color = QukiColorMain
                )
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 28.dp),
                text = "${qrCode.price} 원",
                fontSize = 18.sp,
                fontWeight = FontWeight(700),
                color = QukiColorGray_3,
                textAlign = TextAlign.End
            )
        }
    }
}

@Preview
@Composable
fun QrCardViewExpandedPreview() {
    QrCardViewExpanded(
        modifier = Modifier.fillMaxSize(),
        qrCode = QrCode(
            userId = 1,
            title = "내 최애 메뉴",
            storeId = 10,
            price = 1000,
            image = "https://images.dog.ceo/breeds/hound-plott/hhh_plott002.jpg",
            isFavorite = false,
            content = Content(
                id = 3,
                price = 1000,
                count = 1,
                type = "커피",
                url = "" // QrImage
            ),
            id = 7
        ),
        onFavoriteClick = {},
        likeCount = 122
    )
}