package com.hackathon.quki.presentation.components.qr_card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.hackathon.quki.R
import com.hackathon.quki.core.utils.CustomRippleEffect.clickableWithoutRipple
import com.hackathon.quki.data.source.remote.QrCode
import com.hackathon.quki.ui.theme.QukiColorGray_2
import com.hackathon.quki.ui.theme.QukiColorGray_3
import com.hackathon.quki.ui.theme.QukiColorMain
import com.hackathon.quki.ui.theme.QukiColorShadow

@Composable
fun QrCardView(
    modifier: Modifier = Modifier,
    qrCode: QrCode,
    onFavoriteClick: () -> Unit
) {

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
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .size(90.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    model = qrCode.image,
                    contentDescription = "qr_image",
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Crop,
                )
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(bottom = 4.dp),
                        text = qrCode.title,
                        fontSize = 14.sp,
                        fontWeight = FontWeight(700),
                        color = QukiColorGray_3
                    )
                    Text(
                        text = qrCode.storeId.toString(),
                        fontSize = 10.sp,
                        fontWeight = FontWeight(400),
                        color = QukiColorMain
                    )
                    Text(
                        text = qrCode.content,
                        fontSize = 14.sp,
                        fontWeight = FontWeight(400),
                        color = QukiColorGray_2
                    )
                    Text(
                        text = qrCode.content,
                        fontSize = 12.sp,
                        fontWeight = FontWeight(400),
                        color = QukiColorGray_2
                    )
                }
            }
            Image(
                modifier = Modifier
                    .size(23.dp)
                    .align(Alignment.Top)
                    .clip(RoundedCornerShape(50))
                    .clickableWithoutRipple(
                        interactionSource = MutableInteractionSource(),
                        onClick = onFavoriteClick
                    ),
                painter = if (qrCode.isFavorite) {
                    painterResource(id = R.drawable.ic_favorite_y)
                } else {
                    painterResource(id = R.drawable.ic_favorite_n)
                },
                contentDescription = "favorite_image"
            )
        }
    }
}

@Preview
@Composable
fun QrCardViewPreview() {
    QrCardView(qrCode = QrCode(
        id = 10,
        content = "메가리카노",
        isFavorite = false,
        image = "https://images.dog.ceo/breeds/hound-plott/hhh_plott002.jpg",
        price = 100,
        storeId = 10,
        title = "내 최애 메뉴",
        userId = 1
    ),
        onFavoriteClick = {}
    )
}