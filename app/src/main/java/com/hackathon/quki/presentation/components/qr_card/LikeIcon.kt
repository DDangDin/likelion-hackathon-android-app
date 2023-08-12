package com.hackathon.quki.presentation.components.qr_card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hackathon.quki.R
import com.hackathon.quki.ui.theme.QukiColorGray_2

@Composable
fun LikeIcon(
    modifier: Modifier = Modifier,
    likeCount: Int
) {

    Box(
        Modifier
            .width(60.dp)
            .height(25.dp)
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.Center)
                .width(60.dp)
                .height(25.dp),
            painter = painterResource(id = R.drawable.ic_like_bg),
            contentDescription = "like_bg",
        )
        Row(
            modifier = Modifier.align(Alignment.Center),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Icon(
                modifier = Modifier
                    .width(12.dp)
                    .height(11.dp),
                imageVector = ImageVector.vectorResource(R.drawable.ic_like),
                contentDescription = "like",
                tint = Color(0xFFFFA3B9)
            )
            Text(
                text = likeCount.toString(),
                fontSize = 10.sp,
                fontWeight = FontWeight(700),
                color = QukiColorGray_2
            )
        }
    }
}

@Preview
@Composable
fun LikeIconPreview() {
    LikeIcon(
        likeCount = 122
    )
}