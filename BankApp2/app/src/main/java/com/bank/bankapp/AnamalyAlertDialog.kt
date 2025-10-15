package com.bank.bankapp


import androidx.compose.foundation.border
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.HazeTint
import dev.chrisbanes.haze.hazeEffect
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.navigation.NavController
import dev.chrisbanes.haze.HazeDefaults
import dev.chrisbanes.haze.haze
import androidx.compose.ui.unit.dp
import dev.chrisbanes.haze.hazeSource

@Composable
fun HighAnomalyAlertDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    navController: NavController,
    hazeState: HazeState,
    anomalyScore: Float
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                onDismiss()
            },
            title = { Text("⚠️ High Anomaly Detected") },
            text = {
                Text(
                    "A high-risk activity pattern was detected (Score: ${"%.2f".format(anomalyScore)}).\n" +
                            "For your security, please re-verify your identity."
                )
            },
            confirmButton = {
                Button(onClick = {
                    onDismiss() // Dismiss the dialog first
                    navController.navigate("password_screen") { // Navigate to Password.kt screen
                        // popUpTo("current_screen_route_before_password") { inclusive = true }
                        launchSingleTop = true
                    }
                }) {
                    Text("OK (Re-Verify)")
                }
            },
            dismissButton = {
                Button(onClick = {
                    onDismiss()
                }) {
                    Text("Cancel")
                }
            },
            modifier = Modifier.hazeSource(
                state = hazeState
            )
        )
    }
}

@Composable
fun ExtremeAnomalyAlertDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    navController: NavController,
    hazeState: HazeState,
    anomalyScore: Float
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
            },
            title = { Text("🚨 Extreme Anomaly Detected") },
            text = {
                Text(
                    "A critical security event was detected (Score: ${"%.2f".format(anomalyScore)}).\n" +
                            "You will be redirected to the main screen. Access will be temporarily limited."
                )
            },
            confirmButton = {
                Button(onClick = {
                    onDismiss() // Dismiss the dialog
                    navController.navigate("intro_screen?lockdown=true") { // Navigate to IntroScreen with timer
                        popUpTo("intro_screen") { inclusive = true } // Clear back stack to intro
                        launchSingleTop = true
                    }
                }) {
                    Text("OK")
                }
            },
            modifier = Modifier.hazeSource(
                state = hazeState
            )
        )
    }
}