package com.hackathon.quki.presentation.components.qr_card

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hackathon.quki.R
import com.hackathon.quki.data.source.remote.QrCodeForApp
import com.hackathon.quki.data.source.remote.api_server.StoreId
import com.hackathon.quki.data.source.remote.kiosk.KioskQrCode
import com.hackathon.quki.data.source.remote.kiosk.Options
import com.hackathon.quki.presentation.components.common.CommonTopBar
import com.hackathon.quki.presentation.state.HomeQrUiEvent
import com.hackathon.quki.presentation.state.QrCardState
import com.hackathon.quki.ui.theme.QukiColorMain

@Composable
fun QrCardFullScreen(
    modifier: Modifier = Modifier,
    qrCardState: QrCardState,
    onClose: () -> Unit,
    wasHomeScreen: Boolean = true,
    onHomeQrUiEvent: (HomeQrUiEvent.CheckFavorite) -> Unit,
    enabledFavorite: Boolean,
    text: String  = "",
    onTextChanged: (String) -> Unit = {}
) {

    val qrCodeForApp = qrCardState.qrCard

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        CommonTopBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp, vertical = 32.dp),
            onClose = onClose,
            title = stringResource(id = R.string.qr_card_full_screen_title)
        )
        if (qrCardState.status) {
            QrCardViewExpanded(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp, end = 25.dp, bottom = 25.dp),
                likeCount = 0,
                qrCodeForApp = qrCodeForApp ?: QrCodeForApp(
                    id = 0,
                    title = "내 최애 메뉴",
                    storeId = StoreId(
                        storeId = 10,
                        storeName = "메가커피"
                    ),
                    price = 1000,
                    imageUrl = "https://api.qrserver.com/v1/create-qr-code/?size=300x300&data=qr test adsadsa",
                    isFavorite = false,
                    kioskEntity = KioskQrCode(
                        id = 3,
                        price = 1000,
                        count = 1,
                        type = "커피",
                        url = "", // QrImage
                        options = Options("", "", ""),
                        ice = false,
                        cream = false,
                        information = 1
                    ),
                    options = "옵션1, 옵션2, ...",
                    menus = "",
                    count = 0,
                    category = ""
                ),
                onHomeQrUiEvent = { onHomeQrUiEvent(it) },
                enabledFavorite = enabledFavorite,
                text = text,
                onTextChanged = { onTextChanged(it) }
            )
            Log.d("wasHomeScreen", "wasHomeScreen: ${wasHomeScreen}")
            if (!wasHomeScreen) {
//                QrCardFullScreenBottomBar(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(50.dp),
////                        .padding(horizontal = 25.dp, vertical = 30.dp),
//                    onShare = onShare,
//                    onDownload = onSave
//                )
                Spacer(modifier = Modifier.size(150.dp))
            } else {
                Spacer(modifier = Modifier.size(150.dp))
            }
        } else {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(100.dp),
                    strokeWidth = 2.dp,
                    color = QukiColorMain
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QrCardFullScreenPreview() {
    QrCardFullScreen(
        modifier = Modifier.fillMaxSize(),
        qrCardState = QrCardState(
            qrCard = QrCodeForApp(
                id = 0,
                title = "내 최애 메뉴",
                storeId = StoreId(
                    storeId = 10,
                    storeName = "메가커피"
                ),
                price = 1000,
                imageUrl = "https://api.qrserver.com/v1/create-qr-code/?size=300x300&data=qr test adsadsa",
                isFavorite = false,
                kioskEntity = KioskQrCode(
                    id = 3,
                    price = 1000,
                    count = 1,
                    type = "커피",
                    url = "", // QrImage
                    options = Options("", "", ""),
                    ice = false,
                    cream = false,
                    information = 1
                ),
                options = "옵션1, 옵션2, ...",
                menus = "",
                count = 0,
                category = ""
            )
        ),
        onClose = {},
        onHomeQrUiEvent = {},
        enabledFavorite = false
    )
}