package com.hackathon.quki.presentation.components.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hackathon.quki.core.utils.CustomRippleEffect
import com.hackathon.quki.ui.theme.QukiColorBlack
import com.hackathon.quki.ui.theme.QukiColorGray_3

@Composable
fun CommonTopBar(
    modifier: Modifier = Modifier,
    onClose: () -> Unit,
    title: String,
) {

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CompositionLocalProvider(LocalRippleTheme provides CustomRippleEffect.NoRippleTheme) {
            IconButton(
                modifier = Modifier.size(23.dp),
                onClick = onClose
            ) {
                Icon(
                    modifier = Modifier.size(23.dp),
                    imageVector = Icons.Rounded.Close,
                    contentDescription = "close",
                    tint = QukiColorGray_3
                )
            }
        }
        Text(
            text =  title,
            fontSize = 16.sp,
            fontWeight = FontWeight(400),
            color = QukiColorBlack,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.size(23.dp))
    }
}

@Preview
@Composable
fun CommonTopBarPreview() {
    CommonTopBar(
        title = "필터",
        onClose = {}
    )
}