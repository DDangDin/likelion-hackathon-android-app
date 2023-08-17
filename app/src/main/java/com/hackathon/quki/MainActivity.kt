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
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.hackathon.quki.core.common.Constants
import com.hackathon.quki.core.common.Constants.LOGIN_TOKEN
import com.hackathon.quki.core.common.Constants.PROFILE_NAME
import com.hackathon.quki.core.common.Constants.PROFILE_THUMBNAIL
import com.hackathon.quki.core.utils.CustomSharedPreference
import com.hackathon.quki.navigation.main_nav.MainNavigationGraph
import com.hackathon.quki.presentation.viewmodel.HomeViewModel
import com.hackathon.quki.presentation.viewmodel.LoginViewModel
import com.hackathon.quki.ui.theme.QukiTheme
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.AndroidEntryPoint
import java.util.UUID
import kotlin.system.exitProcess

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val homeViewModel: HomeViewModel by viewModels()
    private val loginViewModel: LoginViewModel by viewModels()
    private val TAG = "MainActivity"

    // 이메일 로그인 콜백
    private val mCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            Log.e(TAG + "mCallback", "로그인 실패 $error")
        } else if (token != null) {
            Log.e(TAG + "mCallback", "로그인 성공 ${token.accessToken}")
            UserApiClient.instance.me { user, error ->
                user?.let {
                    val uuid = UUID.randomUUID()
                    Log.d("kakao_login_log", "로그인 성공 ${it.id}")
                    loginViewModel.login(it.id.toString(), it.properties?.get("nickname") ?: uuid.toString())
                    CustomSharedPreference(this).setUserPrefs(Constants.LOGIN_TOKEN, it.id.toString())
                    CustomSharedPreference(this).setUserPrefs(Constants.PROFILE_THUMBNAIL, it.properties?.get("profile_image").toString())
                    CustomSharedPreference(this).setUserPrefs(Constants.PROFILE_NAME, it.properties?.get("nickname").toString())
                }
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        CustomSharedPreference(this).deleteUserPrefs(LOGIN_TOKEN)

        setContent {

            val navController = rememberNavController()
            val context = LocalContext.current

            // Kakao Hash
//            Log.d("Kakao_Hash", "keyhash : ${Utility.getKeyHash(this)}")

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
                        homeViewModel = homeViewModel,
                        loginWithKakao = { loginWithKakao() },
                        loginViewModel = loginViewModel,
                        onLogout = { safeKillApp() }
                    )
                }
            }
        }
    }

    fun safeKillApp() {

        CustomSharedPreference(this).deleteUserPrefs(LOGIN_TOKEN)
        CustomSharedPreference(this).deleteUserPrefs(PROFILE_NAME)
        CustomSharedPreference(this).deleteUserPrefs(PROFILE_THUMBNAIL)

        moveTaskToBack(true); // 태스크를 백그라운드로 이동
        finishAndRemoveTask() // 액티비티 종료 + 태스크 리스트에서 지우기
        exitProcess(0)
    }

    override fun onResume() {
        super.onResume()
        if (CustomSharedPreference(this).isContain(LOGIN_TOKEN)) {
            val userId = CustomSharedPreference(this).getUserPrefs(LOGIN_TOKEN)
            homeViewModel.getQrCardsFromServer(userId)
        }
    }

    fun loginWithKakao() {
        //     카카오톡 설치 확인
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this@MainActivity)) {
            // 카카오톡 로그인
            UserApiClient.instance.loginWithKakaoTalk(this@MainActivity) { token, error ->
                // 로그인 실패 부분
                if (error != null) {
                    Log.e(TAG + "카카오톡 로그인 실패", "로그인 실패 $error")
                    // 사용자가 취소
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }
                    // 다른 오류
                    else {
                        UserApiClient.instance.loginWithKakaoAccount(
                            this@MainActivity,
                            callback = mCallback
                        ) // 카카오 이메일 로그인
                    }
                }
                // 로그인 성공 부분
                else if (token != null) {
                    Log.d("kakao_login_log", "로그인 성공 ${token.accessToken}")
                    UserApiClient.instance.me { user, error ->
                        user?.let {
                            val uuid = UUID.randomUUID()
                            Log.d("kakao_login_log", "로그인 성공 ${it.id}")
                            loginViewModel.login(it.id.toString(), it.properties?.get("nickname") ?: uuid.toString())
                            CustomSharedPreference(this).setUserPrefs(Constants.LOGIN_TOKEN, it.id.toString())
                            CustomSharedPreference(this).setUserPrefs(Constants.PROFILE_THUMBNAIL, it.properties?.get("profile_image").toString())
                            CustomSharedPreference(this).setUserPrefs(Constants.PROFILE_NAME, it.properties?.get("nickname").toString())
                        }
                    }
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(
                this@MainActivity,
                callback = mCallback
            ) // 카카오 이메일 로그인
        }
    }
}