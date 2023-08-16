package com.hackathon.quki

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.hackathon.quki.navigation.main_nav.MainNavigationGraph
import com.hackathon.quki.presentation.viewmodel.HomeViewModel
import com.hackathon.quki.presentation.viewmodel.ScanQrViewModel
import com.hackathon.quki.ui.theme.QukiTheme
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val scanQrViewModel: ScanQrViewModel by viewModels()
    private val homeViewModel: HomeViewModel by viewModels()
    private val TAG = "MainActivity"

    // 이메일 로그인 콜백
    private val mCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            Log.e(TAG, "로그인 실패 $error")
        } else if (token != null) {
            Log.e(TAG, "로그인 성공 ${token.accessToken}")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()

            // Kakao Hash
//            Log.d("Kakao_Hash", "keyhash : ${Utility.getKeyHash(this)}")
            // Kakao Hash
            KakaoSdk.init(this, BuildConfig.KAKAO_NATIVE_APP_KEY)

//            LaunchedEffect(key1 = Unit) {
//                // 카카오톡 설치 확인
//                if (UserApiClient.instance.isKakaoTalkLoginAvailable(this@MainActivity)) {
//                    // 카카오톡 로그인
//                    UserApiClient.instance.loginWithKakaoTalk(this@MainActivity) { token, error ->
//                        // 로그인 실패 부분
//                        if (error != null) {
//                            Log.e(TAG, "로그인 실패 $error")
//                            // 사용자가 취소
//                            if (error is ClientError && error.reason == ClientErrorCause.Cancelled ) {
//                                return@loginWithKakaoTalk
//                            }
//                            // 다른 오류
//                            else {
//                                UserApiClient.instance.loginWithKakaoAccount(this@MainActivity, callback = mCallback) // 카카오 이메일 로그인
//                            }
//                        }
//                        // 로그인 성공 부분
//                        else if (token != null) {
//                            Log.e(TAG, "로그인 성공 ${token.accessToken}")
//                        }
//                    }
//                } else {
//                    UserApiClient.instance.loginWithKakaoAccount(this@MainActivity, callback = mCallback) // 카카오 이메일 로그인
//                }
//            }

            QukiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val scanQrIntent = Intent(this@MainActivity, ScanQrActivity::class.java)


                    // HomeViewModel 을 넘기면 안됨 -> UiEvent 로 관리할 수 있도록 코드 리팩토링
                    MainNavigationGraph(
                        navController = navController,
                        onScanQrClick = {
                            startActivity(scanQrIntent)
                        },
                        homeViewModel = homeViewModel
                    )
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        homeViewModel.getQrCards()
    }
}