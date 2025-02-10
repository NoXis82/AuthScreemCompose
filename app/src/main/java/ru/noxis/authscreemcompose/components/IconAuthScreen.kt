package ru.noxis.authscreemcompose.components

import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

@PreviewLightDark
@Composable
fun IconAuthScreen() {
    var moved by remember { mutableStateOf(false) }

    val width = LocalConfiguration.current.screenWidthDp
    val size = width / 3
    val height = LocalConfiguration.current.screenHeightDp
    val density = LocalDensity.current.density
    val positionMovX = width / 2
    val positionMovY = height / 4


    LaunchedEffect(true) {
        delay(1000)
        moved = !moved
    }

    val offsetChange by animateIntOffsetAsState(
        targetValue = if (moved) {
            IntOffset(
                (positionMovX * density).roundToInt() - (size * density).roundToInt() / 2,
                (positionMovY * density).roundToInt() - (size * density).roundToInt() / 2
            )
        } else {
            IntOffset(
                (positionMovX * density).roundToInt() - (size * density).roundToInt() / 2,
                -(size * density).roundToInt()
            )
        },
        label = "offset"
    )

    Icon(
        modifier = Modifier
            .size(size.dp)
            .offset { offsetChange },
        imageVector = Icons.Default.AccountCircle,
        contentDescription = null,
        tint = Color.White
    )
}
