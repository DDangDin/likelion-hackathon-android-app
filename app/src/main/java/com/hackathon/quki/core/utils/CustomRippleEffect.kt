package com.hackathon.quki.core.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

object CustomRippleEffect {

    fun Modifier.clickableWithoutRipple(
        interactionSource: MutableInteractionSource,
        onClick: () -> Unit
    ) = composed(
        factory = {
            this.then(
                Modifier.clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = { onClick() }
                )
            )
        }
    )
}