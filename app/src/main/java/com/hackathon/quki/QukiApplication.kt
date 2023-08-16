package com.hackathon.quki

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class QukiApplication: Application() {

    private val TAG = "QuikApplication"

    override fun onCreate() {
        super.onCreate()

        // Kakao Hash
        KakaoSdk.init(this, BuildConfig.KAKAO_NATIVE_APP_KEY)
    }
}