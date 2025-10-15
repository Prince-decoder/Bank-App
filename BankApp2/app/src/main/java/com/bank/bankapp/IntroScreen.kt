package com.bank.bankapp

import androidx.activity.ComponentActivity
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.HazeTint
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.rememberHazeState
import kotlinx.coroutines.delay
import com.example.bank_app.R


@Composable
@Preview(showBackground = true)
fun IntroScreenPreview() {
    val previewNavController = rememberNavController()
    IntroScreen(
        previewNavController,
        activity = ComponentActivity(),
        startLockdown = true
    )
}

@Composable
fun IntroScreen(navController: NavHostController,
                activity: ComponentActivity,
                startLockdown: Boolean = false ) {

    var buttonsEnabled by remember { mutableStateOf(!startLockdown) }
    var countdown by remember { mutableStateOf(if (startLockdown) 10 else 0) }
    val navigateToPassword = "password_screen"
    val navigateToRegister = "register_screen"

    val hazeState = rememberHazeState()
    val gradientColors = listOf(Color.Cyan, Color.Gray, Color.Magenta)
    val facultyGlyphic = FontFamily.Monospace

    // --- Animation States ---
    var currentProgress by remember { mutableStateOf(0f) }
    val animatedProgress by animateFloatAsState(
        targetValue = currentProgress,
        animationSpec = tween(durationMillis = 4000),
        label = "IntroProgressAnimation"
    )

    var logoAndTextVisible by remember { mutableStateOf(false) }
    var buttonsVisible by remember { mutableStateOf(false) }

    val targetLogoOffsetY = (-100).dp
    var currentLogoOffsetY by remember { mutableStateOf(0.dp) }
    val animatedLogoOffsetY by animateDpAsState(
        targetValue = currentLogoOffsetY,
        animationSpec = tween(durationMillis = 700, easing = FastOutSlowInEasing),
        label = "LogoOffsetAnimation"
    )

    LaunchedEffect(Unit) {
        currentProgress = 1f
        logoAndTextVisible = true
    }

    LaunchedEffect(animatedProgress) {
        if (animatedProgress == 1f) {
            currentLogoOffsetY = targetLogoOffsetY
            delay(200)
            buttonsVisible = true
        }
    }

    LaunchedEffect(key1 = startLockdown) {
        if (startLockdown) {
            buttonsEnabled = false
            countdown = 10
            while (countdown > 0 && activity.lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                delay(1000L)
                countdown--
            }
            buttonsEnabled = true
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {

        VideoBackground(videoUri = getVideoUri(R.raw.mybackground2))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(modifier = Modifier.offset(y = animatedLogoOffsetY)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    AnimatedVisibility(
                        visible = logoAndTextVisible,
                        enter = slideInVertically(
                            initialOffsetY = { fullHeight -> -fullHeight / 2 },
                            animationSpec = tween(durationMillis = 1200, delayMillis = 200, easing = LinearOutSlowInEasing)
                        ) + fadeIn(animationSpec = tween(durationMillis = 1200, delayMillis = 200)),
                        label = "LogoSlideIn",
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.symbol),
                            contentDescription = null,
                            modifier = Modifier.size(100.dp),
                            tint = Color(180, 87, 229, 255)
                        )
                    }

                    AnimatedVisibility(
                        visible = logoAndTextVisible,
                        enter = slideInVertically(
                            initialOffsetY = { fullHeight -> -fullHeight },
                            animationSpec = tween(durationMillis = 2000, easing = LinearOutSlowInEasing)
                        ) + fadeIn(animationSpec = tween(durationMillis = 2000)),
                        label = "AppNameSlideIn",
                    ) {
                        Text(
                            text = buildAnnotatedString {
                                withStyle(
                                    SpanStyle(
                                        brush = Brush.linearGradient(colors = gradientColors),
                                        fontSize = 50.sp,
                                        fontFamily = facultyGlyphic,
                                        fontWeight = FontWeight.W200
                                    )
                                ) { append("Sky High") }
                            }
                        )
                    }
                }
            }

            Spacer(Modifier.height(100.dp)) // Space for logo to move up

            if (startLockdown && countdown > 0) {
                Text(
                    text = "Access will be enabled in $countdown seconds",
                    color = Color.LightGray,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }

            // Buttons remain here, will be pushed up by the main column's verticalArrangement
            // and the padding at the bottom of the Column
            AnimatedVisibility(
                visible = buttonsVisible,
                enter = slideInVertically(
                    initialOffsetY = { fullHeight -> fullHeight / 2 },
                    animationSpec = tween(durationMillis = 800, easing = LinearOutSlowInEasing)
                ) + fadeIn(animationSpec = tween(durationMillis = 800)),
                label = "RegisterButtonSlideIn"
            ) {
                Button(
                    onClick = { navController.navigate(navigateToRegister) },
                    enabled = buttonsEnabled,
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .fillMaxWidth(0.85f)
                        .height(55.dp)
                        .clip(shape = RoundedCornerShape(66.dp))
                        .border(
                            width = 1.dp,
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.White.copy(alpha = 0.5f),
                                    Color.White.copy(alpha = 0.2f),
                                ),
                            ),
                            shape = RoundedCornerShape(66.dp)
                        )
                        .hazeEffect(
                            state = hazeState,
                            style = HazeStyle(
                                Color.White.copy(alpha = 0.1f),
                                tint = HazeTint(Color(128, 128, 128, 200), BlendMode.Luminosity),
                                blurRadius = 2.dp,
                                noiseFactor = 0f
                            )
                        ),
                    content = { Text("Register", fontSize = 22.sp, fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.W300) },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        containerColor = Color.Unspecified,
                        disabledContentColor = DarkGray
                    ),
                    shape = ShapeDefaults.Medium
                )
            }

            AnimatedVisibility(
                visible = buttonsVisible,
                enter = slideInVertically(
                    initialOffsetY = { fullHeight -> fullHeight / 2 },
                    animationSpec = tween(durationMillis = 800, delayMillis = 150, easing = LinearOutSlowInEasing)
                ) + fadeIn(animationSpec = tween(durationMillis = 800, delayMillis = 150)),
                label = "LoginButtonSlideIn"
            ) {
                Button(
                    onClick = { navController.navigate(navigateToPassword) },
                    enabled = buttonsEnabled,
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .height(55.dp)
                        .clip(shape = RoundedCornerShape(66.dp))
                        .border(
                            width = 1.dp,
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.White.copy(alpha = 0.5f),
                                    Color.White.copy(alpha = 0.2f),
                                ),
                            ),
                            shape = RoundedCornerShape(66.dp)
                        )
                        .hazeEffect(
                            state = hazeState,
                            style = HazeStyle(
                                Color.White.copy(alpha = 0.1f),
                                tint = HazeTint(Color(128, 128, 128, 200), BlendMode.Luminosity),
                                blurRadius = 2.dp,
                                noiseFactor = 0f
                            )
                        ),
                    content = { Text("Log In", fontSize = 22.sp, fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.W300) },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        containerColor = Color.Unspecified,
                        disabledContentColor = DarkGray
                    ),
                    shape = ShapeDefaults.Medium
                )
            }
        }

        if (animatedProgress < 1f) {
            LinearProgressIndicator(
                progress = { animatedProgress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(15.dp)
                    .align(Alignment.BottomCenter),
                color = Color(46, 2, 112, 255),
                trackColor = Color(229, 167, 230, 150),
                strokeCap = StrokeCap.Square
            )
        }
        // If you want to keep the space occupied by the progress bar even when it's hidden:
        // else if (buttonsVisible) { // Only add spacer if buttons are visible to avoid initial large gap
        //     Spacer(Modifier.fillMaxWidth().height(13.dp).padding(bottom = 16.dp).align(Alignment.BottomCenter))
        // }
    }
}
