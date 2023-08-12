package com.hackathon.quki.presentation.components.qr_card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import com.hackathon.quki.data.source.remote.QrCode
import com.hackathon.quki.presentation.components.common.CommonTopBar
import com.hackathon.quki.ui.theme.QukiColorMain

@Composable
fun QrCardFullScreen(
    modifier: Modifier = Modifier,
    qrCode: QrCode?,
    onClose: () -> Unit,
    onFavoriteClick: () -> Unit,
    onShare: () -> Unit,
    onDownload: () -> Unit
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
        if (qrCode == null) {
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
                qrCode = qrCode,
                onFavoriteClick = onFavoriteClick,
            )
            QrCardFullScreenBottomBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp, vertical = 30.dp),
                onShare = onShare,
                onDownload = onDownload
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QrCardFullScreenPreview() {
    QrCardFullScreen(
        modifier = Modifier.fillMaxSize(),
        qrCode = QrCode(
            userId = 1,
            title = "내 최애 메뉴",
            storeId = 10,
            price = 1000,
            image = "https://images.dog.ceo/breeds/hound-plott/hhh_plott002.jpg",
            isFavorite = false,
            content = Content(
                id = 3,
                price = 1000,
                count = 1,
                type = "커피",
                url = "" // QrImage
            ),
            id = 7
        ),
        onClose = {},
        onFavoriteClick = {},
        onShare = {},
        onDownload = {}
    )
}