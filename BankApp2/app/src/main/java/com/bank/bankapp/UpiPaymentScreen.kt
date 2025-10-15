package com.bank.bankapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.graphics.Brush
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.material3.TextField
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AlternateEmail
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.Magenta
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.chrisbanes.haze.HazeDefaults
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.HazeTint
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource
import dev.chrisbanes.haze.materials.ExperimentalHazeMaterialsApi
import dev.chrisbanes.haze.materials.HazeMaterials
import dev.chrisbanes.haze.rememberHazeState
import kotlin.collections.listOf
import com.example.bank_app.R
import com.example.bank_app.logTextFieldBehavior

@Composable
@Preview(showBackground = true)
fun UPIPayScreenPreview()
{
    val previewNavController = rememberNavController()

    UPIPayScreen(
        navController = previewNavController
    )
}


@OptIn(ExperimentalHazeMaterialsApi::class)
@Composable
fun UPIPayScreen(navController: NavHostController) {

    var lastChangeTime by remember { mutableStateOf(System.currentTimeMillis()) }

    val navigateToHome = "home_screen"
    val hazeState = rememberHazeState()

    var upi_id by remember { mutableStateOf("") }
    val facultyGlyphic = FontFamily.Monospace
    val gradientColors = listOf(Yellow, Red, Cyan, Magenta, Blue)

    Box (
        modifier = Modifier
            .fillMaxSize()

    ) {

        Image(
            painterResource(R.drawable.background6),
            contentDescription = "Background",
            modifier = Modifier
                //.rotate(180f)
                .requiredSize(900.dp)
                .hazeSource(state = hazeState)
        )

        Surface(
            modifier = Modifier
                .size(370.dp, 400.dp)
                .align(Alignment.Center)
                .offset(0.dp, (-140).dp)
                .clip(shape = RoundedCornerShape(66.dp))
                .border(
                    width = 2.dp,
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            White.copy(alpha = 0.9f),
                            White.copy(alpha = 0.2f),
                        ),
                    ),
                    shape = RoundedCornerShape(66.dp)
                )
                .hazeEffect(
                    state = hazeState,
                    style = HazeStyle(
                        White.copy(alpha = 0.1f),
                        tint = HazeTint(
                            Color(128, 128, 128, 50),
                            BlendMode.Luminosity
                        ),
                        blurRadius = 12.dp,
                        noiseFactor = 0f
                    )
                ),
            color = Transparent
        ) {  }

        Column(
            modifier = Modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            NavigationBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(75.dp),
                containerColor = Transparent,
                contentColor = White,
                windowInsets = WindowInsets(0.dp, 30.dp, 0.dp, 0.dp)
            ) {
                NavigationBarItem(
                    selected = true,
                    onClick = { navController.navigate("home_screen") },
                    icon = {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back to Home",
                            modifier = Modifier.size(33.dp),
                            tint = White
                        )
                    },
                    modifier = Modifier
                        .clickable(null, LocalIndication.current, true, null) { /* Handle click */ }
                        .background(Transparent)
                        .weight(1f),
                    //.offset((-170).dp, 0.dp),
                    enabled = true,
                    alwaysShowLabel = false,
                    colors = NavigationBarItemColors(
                        selectedIconColor = Color(12, 122, 86, 255),
                        unselectedIconColor = Gray,
                        selectedTextColor = White,
                        unselectedTextColor = Gray,
                        selectedIndicatorColor = Transparent,
                        disabledIconColor = Gray,
                        disabledTextColor = Gray
                    ),
                    interactionSource = null
                )

                Spacer(Modifier
                    .size(50.dp)
                    .weight(5f))
            }

            Text(
                text = buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            color = White,
                            fontSize = 22.sp,
                            fontFamily = facultyGlyphic,
                            fontWeight = FontWeight.Bold,
                        )
                    ) {
                        append("Enter UPI ID")
                    }
                },
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .offset((-100).dp, 20.dp)
            )

            Row(
                modifier = Modifier
                    .size(355.dp, 75.dp)
                    .offset(0.dp, 30.dp)
            ) {
                TextField(
                    value = upi_id,
                    onValueChange = { newText ->
                        val oldText = upi_id
                        lastChangeTime = logTextFieldBehavior(
                            label = "UPI ID",
                            oldText = oldText,
                            newText = newText,
                            lastTimestamp = lastChangeTime
                        )
                        upi_id = newText
                    },
                    modifier = Modifier
                        .padding(0.dp, 0.dp , 10.dp, 0.dp)
                        .size(100.dp, 60.dp)
                        .offset(0.dp, (-90).dp)
                        .weight(5f),
                    textStyle = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = facultyGlyphic,
                        fontWeight = FontWeight.W300,
                        color = White,
                    ),
                    singleLine = true,
                    label = { Text("UID", modifier = Modifier.offset((0).dp, 4.dp)) },
                    shape = Shapes().large,
                    colors = TextFieldColors(
                        focusedLabelColor = LightGray,
                        unfocusedLabelColor = LightGray,
                        disabledLabelColor = Color.Black,
                        errorLabelColor = Gray,
                        focusedTextColor = White,
                        unfocusedTextColor = Blue,
                        focusedContainerColor = Transparent,
                        unfocusedContainerColor = Transparent,
                        unfocusedPrefixColor = White,
                        focusedPrefixColor = Gray,
                        unfocusedSuffixColor = Gray,
                        focusedSuffixColor = Gray,
                        focusedIndicatorColor = LightGray,
                        unfocusedIndicatorColor = LightGray,
                        focusedPlaceholderColor = Gray,
                        unfocusedPlaceholderColor = Gray,
                        focusedTrailingIconColor = Gray,
                        unfocusedLeadingIconColor = Gray,
                        focusedLeadingIconColor = Gray,
                        unfocusedTrailingIconColor = Gray,
                        focusedSupportingTextColor = Gray,
                        unfocusedSupportingTextColor = Gray,
                        disabledTextColor = Gray,
                        errorTextColor = Gray,
                        disabledContainerColor = Gray,
                        cursorColor = Transparent,
                        errorCursorColor = Gray,
                        disabledIndicatorColor = LightGray,
                        errorIndicatorColor = Gray,
                        disabledLeadingIconColor = Gray,
                        errorLeadingIconColor = Gray,
                        disabledTrailingIconColor = Gray,
                        errorTrailingIconColor = Gray,
                        disabledPlaceholderColor = Gray,
                        errorPlaceholderColor = Gray,
                        disabledSupportingTextColor = Gray,
                        errorSupportingTextColor = Gray,
                        disabledPrefixColor = Gray,
                        errorPrefixColor = Gray,
                        disabledSuffixColor = Gray,
                        errorSuffixColor = Gray,
                        errorContainerColor = Gray,
                        textSelectionColors = TextSelectionColors(handleColor = Magenta, backgroundColor = Blue),
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        showKeyboardOnFocus = true),
                )
                TextField(
                    value = "sbi",
                    onValueChange = {if(it.length <= 12)
                    {
                        upi_id = it
                    } },
                    enabled = false,
                    modifier = Modifier
                        .padding(0.dp, 0.dp , 0.dp, 0.dp)
                        .size(60.dp, 60.dp)
                        .offset(0.dp, (-90).dp)
                        .weight(2f),
                    textStyle = TextStyle(
                        fontSize = 22.sp,
                        fontFamily = facultyGlyphic,
                        fontWeight = FontWeight.W300,
                        color = Gray,
                    ),
                    singleLine = true,
                    readOnly = true,
                    shape = Shapes().large,
                    colors = TextFieldColors(
                        focusedLabelColor = LightGray,
                        unfocusedLabelColor = LightGray,
                        disabledLabelColor = Gray,
                        errorLabelColor = Gray,
                        focusedTextColor = White,
                        unfocusedTextColor = Blue,
                        focusedContainerColor = Transparent,
                        unfocusedContainerColor = Transparent,
                        unfocusedPrefixColor = White,
                        focusedPrefixColor = Gray,
                        unfocusedSuffixColor = Gray,
                        focusedSuffixColor = Gray,
                        focusedIndicatorColor = LightGray,
                        unfocusedIndicatorColor = LightGray,
                        focusedPlaceholderColor = Gray,
                        unfocusedPlaceholderColor = Gray,
                        focusedTrailingIconColor = Gray,
                        unfocusedLeadingIconColor = Gray,
                        focusedLeadingIconColor = Gray,
                        unfocusedTrailingIconColor = Gray,
                        focusedSupportingTextColor = Gray,
                        unfocusedSupportingTextColor = Gray,
                        disabledTextColor = Gray,
                        errorTextColor = Gray,
                        disabledContainerColor = Transparent,
                        cursorColor = Transparent,
                        errorCursorColor = Gray,
                        disabledIndicatorColor = Gray,
                        errorIndicatorColor = Gray,
                        disabledLeadingIconColor = Gray,
                        errorLeadingIconColor = Gray,
                        disabledTrailingIconColor = Gray,
                        errorTrailingIconColor = Gray,
                        disabledPlaceholderColor = Gray,
                        errorPlaceholderColor = Gray,
                        disabledSupportingTextColor = Gray,
                        errorSupportingTextColor = Gray,
                        disabledPrefixColor = Gray,
                        errorPrefixColor = Gray,
                        disabledSuffixColor = Gray,
                        errorSuffixColor = Gray,
                        errorContainerColor = Gray,
                        textSelectionColors = TextSelectionColors(handleColor = Magenta, backgroundColor = Blue),
                    ),
                    leadingIcon = {
                        Image(
                            imageVector = Icons.Default.AlternateEmail,
                            contentDescription = null,
                            modifier = Modifier.size(25.dp).padding(0.dp),
                            contentScale = ContentScale.Inside)
                    }
                )

            }
            Spacer(
                Modifier
                    .size(0.dp, 30.dp)
            )

            Text(
                text = buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            color = Color(214, 217, 229, 255),
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = facultyGlyphic
                        )
                    ) {
                        append("Enter MPIN")
                    }
                },
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .padding(15.dp, 15.dp, 15.dp, 35.dp)
                    .offset((-100).dp, (-170).dp),
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .offset(0.dp, (-230).dp)
            ) {
                repeat(6) { index ->
                    PasswordChar()
                }
            }

            Button(
                onClick = {
                    if (upi_id.isNotEmpty()) {
                        navController.navigate("payment_success")
                    }
                },
                modifier = Modifier
                    .padding(16.dp)
                    .size(300.dp, 55.dp)
                    .offset(0.dp, (-15).dp)
                    .clickable(onClick = { /**/ })
                    .clip(shape = RoundedCornerShape(66.dp))
                    .border(
                        width = 2.dp,
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                White.copy(alpha = 0.9f),
                                White.copy(alpha = 0.2f),
                            ),
                        ),
                        shape = RoundedCornerShape(66.dp)
                    )
                    .hazeEffect(
                        state = hazeState,
                        style = HazeStyle(
                            White.copy(alpha = 0.1f),
                            tint = HazeTint(
                                Color(128, 128, 128, 90),
                                BlendMode.Luminosity
                            ),
                            blurRadius = 7.dp,
                            noiseFactor = 0f
                        )
                    ),
                content = { Text(
                    text = "Confirm",
                    fontSize = 22.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.W300) },
                colors = ButtonColors(
                    contentColor = White,
                    containerColor = Transparent,
                    disabledContentColor = Gray,
                    disabledContainerColor = Gray
                ),
                shape = ShapeDefaults.Medium
            )

            Image(
                painterResource(R.drawable.bhim_upi),
                contentDescription = "BHIM UPI Icon",
                modifier = Modifier
                    .size(80.dp)
                    .offset(0.dp, (-20.dp))
            )
        }
    }
}