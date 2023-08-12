package com.hackathon.quki.presentation.components.qr_reader

import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.hackathon.quki.R
import com.hackathon.quki.core.utils.CustomRippleEffect.clickableWithoutRipple
import com.hackathon.quki.presentation.components.common.CommonTopBar
import com.hackathon.quki.ui.theme.QukiColorGray_3
import com.hackathon.quki.ui.theme.QukiColorMain

@Composable
fun ScanQrView(
    modifier: Modifier = Modifier,
    previewView: PreviewView,
    onFlashClick: () -> Unit,
    onClose: () -> Unit
) {

    Box(modifier = modifier.fillMaxSize()) {
        AndroidView(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize(),
            factory = { previewView },
        )

        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .height(80.dp)
                .background(Color.White, RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp))
        ) {
            CommonTopBar(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
                    .padding(horizontal = 26.dp),
                onClose = onClose,
                title = stringResource(id = R.string.qr_scan_title)
            )
        }

        Icon(
            modifier = Modifier
                .size(250.dp)
                .align(Alignment.Center),
            imageVector = ImageVector.vectorResource(R.drawable.ic_qr_scan_big),
            contentDescription = "ic_qr_scan",
            tint = QukiColorMain
        )

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 70.dp)
                .size(60.dp)
                .background(Color.White, RoundedCornerShape(50))
                .clickableWithoutRipple(
                    interactionSource = MutableInteractionSource(),
                    onClick = onFlashClick
                )
        ) {
            Icon(
                modifier = Modifier.align(Alignment.Center),
                imageVector = ImageVector.vectorResource(R.drawable.ic_flashlight),
                contentDescription = "ic_flash",
                tint = QukiColorGray_3
            )
        }
    }
}

//@Preview
//@Composable
//fun ScanQrViewPreview() {
//    ScanQrView()
//}