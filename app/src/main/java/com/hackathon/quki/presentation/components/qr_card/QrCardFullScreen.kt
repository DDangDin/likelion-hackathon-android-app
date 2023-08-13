package com.hackathon.quki.presentation.components.qr_card

import androidx.compose.foundation.layout.Arrangement
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
import com.hackathon.quki.data.source.remote.Content
import com.hackathon.quki.data.source.remote.QrCodeForApp
import com.hackathon.quki.data.source.remote.StoreId
import com.hackathon.quki.presentation.components.common.CommonTopBar
import com.hackathon.quki.ui.theme.QukiColorMain

@Composable
fun QrCardFullScreen(
    modifier: Modifier = Modifier,
    qrCodeForApp: QrCodeForApp?,
    onClose: () -> Unit,
    onFavoriteClick: () -> Unit,
    onShare: () -> Unit,
    onSave: () -> Unit,
    wasHomeScreen: Boolean = true
) {

    Column(
        modifier = Modifier.fillMaxSize(),
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
        if (qrCodeForApp == null) {
            CircularProgressIndicator(
                modifier = Modifier.size(100.dp),
                strokeWidth = 2.dp,
                color = QukiColorMain
            )
        } else {
            QrCardViewExpanded(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp),
                likeCount = 0,
                qrCodeForApp = qrCodeForApp,
                onFavoriteClick = onFavoriteClick,
            )
            if (!wasHomeScreen) {
                QrCardFullScreenBottomBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 25.dp, vertical = 30.dp),
                    onShare = onShare,
                    onDownload = onSave
                )
            } else {
                Spacer(modifier = Modifier.size(150.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QrCardFullScreenPreview() {
    QrCardFullScreen(
        modifier = Modifier.fillMaxSize(),
        qrCodeForApp = QrCodeForApp(
            title = "내 최애 메뉴",
            storeId = StoreId(
                store_id = 10,
                storeName = "메가커피"
            ),
            price = 1000,
            image = "https://api.qrserver.com/v1/create-qr-code/?size=300x300&data=qr test adsadsa",
            isFavorite = false,
            contentEntity = Content(
                id = 3,
                price = 1000,
                count = 1,
                type = "커피",
                url = "" // QrImage
            ),
            content = "옵션1, 옵션2, ..."
        ),
        onClose = {},
        onFavoriteClick = {},
        onShare = {},
        onSave = {}
    )
}