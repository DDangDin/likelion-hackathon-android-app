package com.hackathon.quki.presentation.components.qr_card

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hackathon.quki.R
import com.hackathon.quki.core.utils.CustomRippleEffect.clickableWithoutRipple
import com.hackathon.quki.ui.theme.QukiColorGray_1
import com.hackathon.quki.ui.theme.QukiColorGray_2
import com.hackathon.quki.ui.theme.QukiColorGray_3

@Composable
fun QrCardFullScreenBottomBar(
    modifier: Modifier = Modifier,
    onShare: () -> Unit,
    onDownload: () -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Text(
            text = stringResource(id = R.string.qr_card_full_screen_notice),
            fontSize = 12.sp,
            fontWeight = FontWeight(700),
            color = QukiColorGray_2
        )
        Divider(
            modifier = Modifier.height(1.dp),
            color = QukiColorGray_1
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(70.dp, alignment = Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .clickableWithoutRipple(
                        interactionSource = MutableInteractionSource(),
                        onClick = onShare
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .width(22.dp)
                        .height(20.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.ic_share),
                    contentDescription = "share",
                    tint = QukiColorGray_3
                )
                Text(
                    text = stringResource(id = R.string.qr_card_full_screen_bottom_bar_share),
                    fontSize = 10.sp,
                    fontWeight = FontWeight(700),
                    color = QukiColorGray_3,
                    textAlign = TextAlign.Center
                )
            }
            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .clickableWithoutRipple(
                        interactionSource = MutableInteractionSource(),
                        onClick = onDownload
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .width(22.dp)
                        .height(20.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.ic_download),
                    contentDescription = "download",
                    tint = QukiColorGray_3
                )
                Text(
                    text = stringResource(id = R.string.qr_card_full_screen_bottom_bar_download),
                    fontSize = 10.sp,
                    fontWeight = FontWeight(700),
                    color = QukiColorGray_3,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview
@Composable
fun QrCardFullScreenBottomBarPreview() {
    QrCardFullScreenBottomBar(
        onShare = {  },
        onDownload = {  }
    )
}