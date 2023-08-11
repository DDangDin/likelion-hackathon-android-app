package com.hackathon.quki.presentation.components.home.filter_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hackathon.quki.R
import com.hackathon.quki.core.utils.CustomRippleEffect.clickableWithoutRipple
import com.hackathon.quki.ui.theme.QukiColorBlack
import com.hackathon.quki.ui.theme.QukiColorGray_1
import com.hackathon.quki.ui.theme.QukiColorMain

@Composable
fun FilterContent(
    modifier: Modifier = Modifier,
    title: String,
    firstSubTitle: String,
    secondSubTitle: String,
    onFirstSubTitleClick: () -> Unit,
    onSecondSubTitleClick: () -> Unit,
) {

    var isFavoriteAlignChecked by rememberSaveable {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp, alignment = Alignment.CenterVertically)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            fontSize = 14.sp,
            fontWeight = FontWeight(700),
            color = QukiColorBlack,
            textAlign = TextAlign.Start
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(41.dp)
                .background(Color.White, RoundedCornerShape(20.dp))
                .border(1.dp, QukiColorGray_1, RoundedCornerShape(20.dp))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
                    .padding(horizontal = 10.dp, vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(
                            if (isFavoriteAlignChecked) QukiColorMain else Color.White,
                            RoundedCornerShape(15.dp)
                        )
                        .clickableWithoutRipple(
                            interactionSource = MutableInteractionSource(),
                            onClick = {
                                isFavoriteAlignChecked = true
                                onFirstSubTitleClick()
                            }
                        )
                ) {
                    Text(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(horizontal = 40.dp, vertical = 9.dp),
                        text = firstSubTitle,
                        fontSize = 10.sp,
                        fontWeight = FontWeight(700),
                        color = if (isFavoriteAlignChecked) Color.White else QukiColorMain,
                        textAlign = TextAlign.Center
                    )
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(
                            if (!isFavoriteAlignChecked) QukiColorMain else Color.White,
                            RoundedCornerShape(15.dp)
                        )
                        .clickableWithoutRipple(
                            interactionSource = MutableInteractionSource(),
                            onClick = {
                                isFavoriteAlignChecked = false
                                onSecondSubTitleClick()
                            }
                        )
                ) {
                    Text(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(horizontal = 40.dp, vertical = 9.dp),
                        text = secondSubTitle,
                        fontSize = 10.sp,
                        fontWeight = FontWeight(700),
                        color = if (!isFavoriteAlignChecked) Color.White else QukiColorMain,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FilterContentPreview() {
    FilterContent(
        onFirstSubTitleClick = {},
        onSecondSubTitleClick = {},
        title = stringResource(id = R.string.filter_content_align_title),
        firstSubTitle = stringResource(id = R.string.filter_sub_content_align_text_1),
        secondSubTitle = stringResource(id = R.string.filter_sub_content_align_text_2),
    )
}