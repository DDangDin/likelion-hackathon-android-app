package com.hackathon.quki.presentation.components.home.filter

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
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
import com.hackathon.quki.core.utils.CustomRippleEffect.clickableWithoutRipple
import com.hackathon.quki.ui.theme.QukiColorGray_1
import com.hackathon.quki.ui.theme.QukiColorGray_3
import com.hackathon.quki.ui.theme.QukiColorMain

@Composable
fun FilterItem(
    modifier: Modifier = Modifier,
    filterName: String,
    onClick: () -> Unit
) {

    Box(
        modifier = modifier
            .border(1.dp, QukiColorMain, RoundedCornerShape(15.dp))
            .wrapContentWidth()
            .height(30.dp)
            .background(Color.White, RoundedCornerShape(15.dp))
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.Center)
                .wrapContentWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                7.dp,
                alignment = Alignment.CenterHorizontally
            )
        ) {
            Text(
                text = filterName,
                fontSize = 10.sp,
                fontWeight = FontWeight(700),
                color = QukiColorGray_3,
                textAlign = TextAlign.Center
            )
            Icon(
                modifier = Modifier
                    .size(12.dp)
                    .clickableWithoutRipple(
                        interactionSource = MutableInteractionSource(),
                        onClick = onClick
                    ),
                imageVector = Icons.Rounded.Close,
                contentDescription = "icon",
                tint = QukiColorGray_3
            )
        }
    }
}

@Preview
@Composable
fun FilterItemPreview() {
    FilterItem(filterName = "도시락") {
        
    }
}