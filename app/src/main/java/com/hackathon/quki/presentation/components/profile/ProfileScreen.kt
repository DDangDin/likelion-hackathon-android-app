package com.hackathon.quki.presentation.components.profile

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.hackathon.quki.R
import com.hackathon.quki.core.utils.CustomRippleEffect.clickableWithoutRipple
import com.hackathon.quki.ui.theme.QukiColorBlack
import com.hackathon.quki.ui.theme.QukiColorGray_2

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    image: String,
    name: String,
    onLogout: () -> Unit
) {

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .height(80.dp)
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(id = R.string.profile_title),
                fontSize = 16.sp,
                fontWeight = FontWeight(400),
                color = QukiColorBlack,
                textAlign = TextAlign.Center
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp, top = 20.dp, bottom = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                22.dp,
                alignment = Alignment.Start
            )
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(50)),
                model = image,
                contentDescription = "profile_image",
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    20.dp,
                    alignment = Alignment.CenterVertically
                ),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "$name 님",
                    fontSize = 20.sp,
                    fontWeight = FontWeight(700),
                    color = QukiColorBlack,
                    textAlign = TextAlign.Center,
                    maxLines = 1
                )

                Image(
                    modifier = Modifier.size(25.dp),
                    painter = painterResource(id = R.drawable.ic_kakao_logo),
                    contentDescription = "image description",
                    contentScale = ContentScale.None
                )
            }
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp),
            color = QukiColorGray_2
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 60.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProfileScreenItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                title = "계정",
                option1 = "개인정보 관리",
                option2 = "비밀번호 변경"
            )
            ProfileScreenItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                title = "QR 코드",
                option1 = "저장된 QR 코드",
                option2 = "코드 스캔"
            )
            ProfileScreenItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                title = "앱 설",
                option1 = "다크 모드",
                option2 = "알림 설정",
                isOptionThree = true,
                option3 = "암호 잠금"
            )
            ProfileScreenItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                title = "기타",
                option1 = "회원 탈퇴",
                option2 = "로그아웃",
                option2Click = onLogout
            )
        }
    }

}


// uiEvent 방식으로 처리해서 구분 기능 별 구분 필요
@Composable
fun ProfileScreenItem(
    modifier: Modifier = Modifier,
    title: String,
    option1: String,
    option1Click: () -> Unit = {},
    option2: String,
    option2Click: () -> Unit = {},
    isOptionThree: Boolean = false,
    option3: String = "",
    option3Click: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 13.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(13.dp)
    ) {
        Text(
            text = title,
            fontSize = 14.sp,
            lineHeight = 18.sp,
            fontWeight = FontWeight(700),
            color = QukiColorBlack,
        )
        Text(
            modifier = Modifier
                .padding(start = 30.dp)
                .clickableWithoutRipple(
                    interactionSource = MutableInteractionSource(),
                    onClick = option1Click
                ),
            text = option1,
            fontSize = 14.sp,
            lineHeight = 18.sp,
            fontWeight = FontWeight(500),
            color = QukiColorBlack,
        )
        Text(
            modifier = Modifier
                .padding(start = 30.dp, top = 5.dp)
                .clickableWithoutRipple(
                    interactionSource = MutableInteractionSource(),
                    onClick = option2Click
                ),
            text = option2,
            fontSize = 14.sp,
            lineHeight = 18.sp,
            fontWeight = FontWeight(500),
            color = QukiColorBlack,
        )
        if (isOptionThree) {
            Text(
                modifier = Modifier
                    .padding(start = 30.dp, top = 5.dp)
                    .clickableWithoutRipple(
                        interactionSource = MutableInteractionSource(),
                        onClick = option3Click
                    ),
                text = option3,
                fontSize = 14.sp,
                lineHeight = 18.sp,
                fontWeight = FontWeight(500),
                color = QukiColorBlack,
            )
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp),
            color = QukiColorGray_2
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun ProfileScreenItemPreview() {
//    ProfileScreenItem()
//}


@Preview(showBackground = true, heightDp = 300)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(
        image = "",
        name = "상진",
        onLogout = {}
    )
}