package com.hackathon.quki.presentation.components.home.filter_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hackathon.quki.core.utils.CustomRippleEffect.clickableWithoutRipple
import com.hackathon.quki.ui.theme.QukiColorGray_3
import com.hackathon.quki.ui.theme.QukiColorMain

@Composable
fun FilterItem(
    modifier: Modifier = Modifier,
    filterName: String,
    onClick: (Boolean) -> Unit,
    alreadyChecked: Boolean
) {

    // 나중에 리팩토링 필요!!
    // alreadyChecked -> 실시간 반영 데이터
    // 아래의 isChecked -> ui 제어를 위한 데이터

    var isChecked by rememberSaveable {
        mutableStateOf(alreadyChecked)
    }

    Box(
        modifier = modifier
            .wrapContentSize()
            .border(
                1.dp,
                if (alreadyChecked) QukiColorMain else QukiColorGray_3,
                RoundedCornerShape(15.dp)
            )
            .background(Color.White, RoundedCornerShape(15.dp))
            .clickableWithoutRipple(
                interactionSource = MutableInteractionSource(),
                onClick = {
                    isChecked = !isChecked
                    onClick(isChecked)
                }
            )
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.Center)
                .wrapContentSize()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                7.dp,
                alignment = Alignment.CenterHorizontally
            )
        ) {
            Text(
                text = filterName,
                fontSize = 12.sp,
                fontWeight = FontWeight(700),
                color = QukiColorGray_3,
                textAlign = TextAlign.Center
            )
            if (alreadyChecked) {
                Icon(
                    modifier = Modifier
                        .size(12.dp)
                        .clickableWithoutRipple(
                            interactionSource = MutableInteractionSource(),
                            onClick = { onClick(false) }
                        ),
                    imageVector = Icons.Rounded.Close,
                    contentDescription = "icon",
                    tint = QukiColorGray_3
                )
            }
        }
    }
}

@Preview
@Composable
fun FilterItemPreview() {
    FilterItem(
        filterName = "패스트푸드",
        onClick = {},
        alreadyChecked = true
    )
}