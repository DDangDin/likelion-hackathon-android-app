package com.hackathon.quki.presentation.components.main.filter

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hackathon.quki.ui.theme.QukiColorGray_1

@Composable
fun FilterIcon(modifier: Modifier = Modifier) {

    Box(
        modifier = modifier
            .border(1.dp, QukiColorGray_1, RoundedCornerShape(15.dp))
            .width(65.dp)
            .height(30.dp)
            .background(Color.White, RoundedCornerShape(15.dp))
    ) {

    }
}

@Preview
@Composable
fun FilterIconPreview() {
    FilterIcon()
}