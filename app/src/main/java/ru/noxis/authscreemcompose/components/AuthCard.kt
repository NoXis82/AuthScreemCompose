package ru.noxis.authscreemcompose.components

import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

@PreviewLightDark
@Composable
fun AuthCard() {
    val width = LocalConfiguration.current.screenWidthDp
    val height = LocalConfiguration.current.screenHeightDp
    val density = LocalDensity.current.density
    var moved by remember { mutableStateOf(false) }
    val positionMovY = height / 2

    // Remember the text entered in the TextField
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LaunchedEffect(true) {
        delay(1000)
        moved = !moved
    }

    val offsetChangeCard by animateIntOffsetAsState(
        targetValue = if (moved) {
            IntOffset(0, (positionMovY * density).roundToInt())
        } else {
            IntOffset(0, (height * density).roundToInt())
        },
        label = "offset"
    )

    val offsetChangeButton by animateIntOffsetAsState(
        targetValue = if (moved) {
            IntOffset(
                0,
                (positionMovY * density).roundToInt() + ((height / 3) * density).roundToInt() - (28 * density).roundToInt()
            )
        } else {
            IntOffset(
                0,
                (height * density).roundToInt() + ((height / 3) * density).roundToInt()
            )
        },
        label = "offset"
    )

    Box {
        Card(
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .offset { offsetChangeCard }
                .size(
                    width = width.dp,
                    height = (height / 3).dp
                )
                .clip(RoundedCornerShape(15.dp)),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "LOGIN")
                // TextField for user input
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("EMAIL") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(16.dp))
                // TextField for user input
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    visualTransformation = PasswordVisualTransformation(),
                    label = { Text("PASSWORD") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(16.dp))
            }
        }
        FilledTonalButton(
            modifier = Modifier
                .padding(horizontal = 64.dp)
                .offset { offsetChangeButton }
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = Color(0xFF6C9DF3)
            ),
            onClick = {}
        ) {
            Text(modifier = Modifier.padding(vertical = 8.dp), text = "Login")
        }
    }
}
