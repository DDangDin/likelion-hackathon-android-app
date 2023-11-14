package com.hackathon.quki.presentation.components.login

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.hackathon.quki.R
import com.hackathon.quki.core.utils.CustomRippleEffect.clickableWithoutRipple
import com.hackathon.quki.ui.theme.QukiColorBlack
import com.hackathon.quki.ui.theme.QukiColorMain

@Composable
fun CustomLoginButton(
    modifier: Modifier = Modifier,
    @StringRes text: Int,
    @DrawableRes icon: Int,
    onClick: () -> Unit
) {

//    OutlinedButton(
//        onClick = {  },
//        modifier = modifier
//            .fillMaxWidth()
//            .height(50.dp),
//        shape = RoundedCornerShape(25.dp),
//        colors = ButtonDefaults.buttonColors(
//            contentColor = Color.Transparent,
//            containerColor = Color.Transparent
//        ),
//        border = BorderStroke(1.dp, QukiColorMain),
//        interactionSource = MutableInteractionSource()
//    ) {}

    Box( // edge 25.dp, border 1.dp ColorMain
        modifier = modifier
            .clip(RoundedCornerShape(25.dp))
            .fillMaxWidth()
            .height(50.dp)
            .background(Color.Transparent)
            .border(1.dp, QukiColorMain, RoundedCornerShape(25.dp))
            .clickable { onClick() }
//            .clickableWithoutRipple(
//                interactionSource = MutableInteractionSource(),
//                onClick = onClick
//            ),
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .height(50.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp, alignment = Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(23.dp),
                painter = painterResource(id = icon),
                contentDescription = "button_icon",
                contentScale = ContentScale.Fit
            )
            Text(
                text = stringResource(id = text),
                fontSize = 14.sp,
                fontWeight = FontWeight(700),
                color = QukiColorMain,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun CustomLoginButtonPreview() {
    CustomLoginButton(
        text = R.string.login_btn_title_kakao,
        icon = R.drawable.ic_launcher_foreground,
        onClick = {}
    )
}