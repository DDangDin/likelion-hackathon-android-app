package com.hackathon.quki.presentation.components.login

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hackathon.quki.R
import com.hackathon.quki.core.common.Constants.LOGIN_TOKEN
import com.hackathon.quki.core.utils.CustomSharedPreference
import com.hackathon.quki.presentation.state.LoginState
import com.hackathon.quki.ui.theme.QukiColorBlack
import com.hackathon.quki.ui.theme.QukiColorGray_3
import com.hackathon.quki.ui.theme.QukiColorMain

@Composable
fun LoginScreen(
    loginState: LoginState,
    onNavigateMain: () -> Unit,
    loginWithKakao: () -> Unit,
    showLoginButton: () -> Unit
) {

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        Log.d("sp_log", CustomSharedPreference(context).getUserPrefs(LOGIN_TOKEN))
        if (loginState.login) {
            onNavigateMain()
        } else if (CustomSharedPreference(context).isContain(LOGIN_TOKEN)) {
            onNavigateMain()
        } else {
            showLoginButton()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                58.dp,
                alignment = Alignment.CenterVertically
            )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(
                    15.dp,
                    alignment = Alignment.CenterVertically
                )
            ) {
                Text(
                    text = stringResource(id = R.string.login_screen_text_title),
                    fontSize = 16.sp,
                    fontWeight = FontWeight(400),
                    color = QukiColorBlack
                )
                Text(
                    modifier = Modifier,
                    text = stringResource(id = R.string.app_name),
                    fontSize = 40.sp,
                    fontWeight = FontWeight(700),
                    color = QukiColorBlack
                )
            }
            Image(
                modifier = Modifier
                    .width(298.dp)
                    .height(218.dp),
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = "logo"
            )
            Column(
                modifier = Modifier
                    .height(115.dp)
                    .padding(horizontal = 53.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(
                    15.dp,
                    alignment = Alignment.CenterVertically
                )
            ) {
                if (loginState.loading) {
                    CircularProgressIndicator(
                        modifier = Modifier,
                        strokeWidth = 1.dp,
                        color = QukiColorMain
                    )
                } else {
                    if (loginState.login) {
                        LaunchedEffect(key1 = Unit) {
                            onNavigateMain()
                        }
                    } else {
                        CustomLoginButton(
                            text = R.string.login_btn_title_kakao,
                            icon = R.drawable.ic_kakao_logo,
                            onClick = loginWithKakao
                        )
//                        CustomLoginButton(
//                            text = R.string.login_btn_title_google,
//                            icon = R.drawable.ic_google_logo,
//                            onClick = {}
//                        )
                    }
                }
            }
        }

        Text(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 50.dp),
            text = stringResource(id = R.string.login_screen_text_bottom_title),
            fontSize = 12.sp,
            fontWeight = FontWeight(700),
            color = QukiColorGray_3
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardScreenPreview() {
    LoginScreen(
        loginState = LoginState(),
        onNavigateMain = {},
        loginWithKakao = {},
        showLoginButton = {}
    )
}