package com.hackathon.quki.presentation.components.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hackathon.quki.R

@Composable
fun MainScreen(
    text: String,
    onTextChanged: (String) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            MainTopBar(
                modifier = Modifier.fillMaxWidth(),
                logoIcon = R.drawable.ic_launcher_background,
                optionIcon = R.drawable.ic_launcher_background,
                optionIcon2 = R.drawable.ic_launcher_background,
                text = text,
                onTextChanged = { onTextChanged(it) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen(
        text = "",
        onTextChanged = {}
    )
}