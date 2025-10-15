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

@Composable
@Preview(showBackground = true)
fun SetPinScreenPreview()
{
    val previewNavController = rememberNavController()

    SetPinScreen(
        navController = previewNavController
    )
}

@OptIn(ExperimentalHazeMaterialsApi::class)
@Composable
fun SetPinScreen(navController: NavHostController) {

    val navigateToRoute = "home_screen"
    val hazeState = rememberHazeState()

    var upiID by remember { mutableStateOf("") }
    val maxLenUsername = 10

    val facultyGlyphic = FontFamily.SansSerif
    val gradientColors = listOf(Yellow, Red, Cyan, Magenta, Blue)

    Box (
        modifier = Modifier
            .fillMaxSize()

    ) {

        Image(
            painterResource(R.drawable.background2),
            contentDescription = "Background",
            modifier = Modifier
                //.rotate(180f)
                .requiredSize(900.dp)
                .hazeSource(state = hazeState)
        )

        Surface(
            //shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .size(370.dp, 400.dp)
                .align(Alignment.Center)
                .offset(0.dp, 20.dp)
                .clip(shape = RoundedCornerShape(66.dp))
                .border(
                    width = 3.dp,
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color.White.copy(alpha = 0.9f),
                            Color.White.copy(alpha = 0.2f),
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
                        blurRadius = 14.dp,
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
                            tint = Color.White
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
                text = "Your UPI ID",
                style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(11, 1, 59,255),
                    fontFamily = facultyGlyphic,
                ),
                modifier = Modifier
                    .padding(15.dp, 15.dp, 15.dp, 15.dp)
                    .offset(10.dp, (-70).dp)
                    .size(340.dp, 40.dp),
                textAlign = TextAlign.Left
            )

            TextField(
                value = "username33@oksbi",
                onValueChange =  {  },
                modifier = Modifier
                    .padding(0.dp, 0.dp , 0.dp, 0.dp)
                    .size(350.dp, 60.dp)
                    .offset(0.dp, (-150).dp),
                textStyle = TextStyle(
                    fontSize = 22.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.W300,
                    color = Color(38, 37, 38, 255),
                ),
                singleLine = true,
                enabled = false,
                readOnly = true,
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
                    focusedIndicatorColor = Gray,
                    unfocusedIndicatorColor = Gray,
                    focusedPlaceholderColor = Gray,
                    unfocusedPlaceholderColor = Gray,
                    focusedTrailingIconColor = Gray,
                    unfocusedLeadingIconColor = Gray,
                    focusedLeadingIconColor = Gray,
                    unfocusedTrailingIconColor = Gray,
                    focusedSupportingTextColor = Gray,
                    unfocusedSupportingTextColor = Gray,
                    disabledTextColor = Transparent,
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
                    textSelectionColors = TextSelectionColors(handleColor = Color.Magenta, backgroundColor = Blue),
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    showKeyboardOnFocus = true),
            )

            Text(
                text = "Set MPIN",
                style = TextStyle(
                    fontSize = 25.sp,
                    fontWeight = FontWeight.W300,
                    color = Color.White,
                    fontFamily = facultyGlyphic,
                ),
                modifier = Modifier
                    .padding(15.dp, 15.dp, 15.dp, 15.dp)
                    .offset(10.dp, (-100).dp)
                    .size(340.dp, 40.dp),
                textAlign = TextAlign.Left
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .offset(0.dp, (-160).dp)
            ) {
                repeat(6) { index ->
                    PasswordChar()
                }
            }

            ElevatedButton(
                onClick = { navController.navigate("home_screen") },
                modifier = Modifier
                    .size(250.dp, 50.dp)
                    .offset(0.dp, (-170).dp)
                    .clickable { /**/ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(21, 122, 175, 255), // Light color for button background
                    contentColor = Color(212, 214, 236, 255) // Dark color for text
                ),
                shape = Shapes().small,
                content = { Text(
                    text = "Confirm",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.W300,
                        color = White,
                    )) },
            )

            Icon(
                painter = painterResource(R.drawable.bhim_upi),
                contentDescription = null,
                modifier = Modifier
                    .offset(0.dp, (-20).dp)
                    .size(100.dp),
                tint = Color.White
            )

        }
    }
}