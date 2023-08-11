package com.hackathon.quki.presentation.components.home.filter

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hackathon.quki.R
import com.hackathon.quki.ui.theme.QukiColorGray_1
import com.hackathon.quki.ui.theme.QukiColorGray_3

@Composable
fun FilterIcon(modifier: Modifier = Modifier) {

    Box(
        modifier = modifier
            .border(1.dp, QukiColorGray_1, RoundedCornerShape(15.dp))
            .width(65.dp)
            .height(30.dp)
            .background(Color.White, RoundedCornerShape(15.dp))
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                7.dp,
                alignment = Alignment.CenterHorizontally
            )
        ) {
            Icon(
                modifier = Modifier
                    .width(15.dp)
                    .height(14.dp),
                imageVector = ImageVector.vectorResource(R.drawable.ic_filter),
                contentDescription = "filter_icon",
                tint = QukiColorGray_3
            )
            Text(
                text = stringResource(id = R.string.home_ic_filter_title),
                fontSize = 10.sp,
                fontWeight = FontWeight(700),
                color = QukiColorGray_3,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun FilterIconPreview() {
    FilterIcon()
}