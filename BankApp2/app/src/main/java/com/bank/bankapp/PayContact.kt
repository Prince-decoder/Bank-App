package com.bank.bankapp

import androidx.compose.foundation.Image
import androidx.compose.animation.slideInVertically
import androidx.compose.ui.res.painterResource
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CurrencyRupee
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.HazeTint
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource
import dev.chrisbanes.haze.rememberHazeState
import com.example.bank_app.R
import com.example.bank_app.logTextFieldBehavior


@Composable
@Preview(showBackground = true)
fun PayToContactPreview(){
    val previewNavController = rememberNavController()

    PayToContact(previewNavController)
}

@Composable
fun PayToContact(navController: NavHostController){

    val navigateToHome = "home_screen"
    val hazeState = rememberHazeState()

    var amount by remember { mutableStateOf("") }
    var lastChangeTime by remember { mutableStateOf(System.currentTimeMillis()) }

    val gradientColors: List<Color> = listOf(
        Color(9, 15, 99, 255),
        Color(73, 30, 138, 255),
        Color(12, 122, 86, 255),
        Color(14, 63, 176, 255),
        Color(46, 121, 191, 255),

        )

    val facultyGlyphic = FontFamily(
        Font(R.font.faculty_glyphic_regular)
    )

    Surface(
        modifier = Modifier
            .fillMaxSize(),

        ) {

        Image(
            painterResource(R.drawable.background1),
            contentDescription = "Background",
            modifier = Modifier
                .requiredSize(900.dp)
                .hazeSource(state = hazeState)
        )

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .hazeEffect(
                    state = hazeState,
                    style = HazeStyle(
                        backgroundColor = White.copy(0.2f),
                        tint = HazeTint(
                            Color(128, 128, 128, 50),
                            BlendMode.Hardlight
                        ),
                        blurRadius = 8.dp,
                        noiseFactor = 0f
                    )
                ),
            color = Transparent
        ) {  }

        Surface(
            modifier = Modifier
                .requiredSize(370.dp, 400.dp)
                .offset(0.dp, (-90).dp)
                .clip(shape = RoundedCornerShape(66.dp))
                .border(
                    width = 2.dp,
                    brush = Brush.verticalGradient(
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
                        White,
                        tint = HazeTint(
                            Color(128, 128, 128, 170),
                            BlendMode.Luminosity
                        ),
                        blurRadius = 2.dp,
                        noiseFactor = 0f
                    )
                ),
            color = Transparent
        ) {  }

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            NavigationBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(75.dp),
                containerColor = Color.Transparent,
                contentColor = White,
                windowInsets = WindowInsets(0.dp, 30.dp, 0.dp, 0.dp)
            ) {
                NavigationBarItem(
                    selected = true,
                    onClick = { /*navController.navigate(navigateToRoute)*/ },
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
                        .background(Color.Transparent)
                        .offset((-170).dp, 0.dp),
                    enabled = true,
                    alwaysShowLabel = false,
                    colors = NavigationBarItemColors(
                        selectedIconColor = Color(12, 122, 86, 255),
                        unselectedIconColor = Gray,
                        selectedTextColor = White,
                        unselectedTextColor = Gray,
                        selectedIndicatorColor = Color.Transparent,
                        disabledIconColor = Gray,
                        disabledTextColor = Gray
                    ),
                    interactionSource = null
                )
            }

            Text(
                "Paying to ContactName",
                Modifier
                    .padding(30.dp, 20.dp, 10.dp, 40.dp)
                    .offset((-40).dp, 0.dp),
                style = TextStyle(
                    fontSize = 25.sp,
                    fontFamily = facultyGlyphic,
                    fontWeight = FontWeight.W100,
                    color = White,
                    textAlign = TextAlign.Start,
                ),

                )

            Text(
                "Enter amount",
                Modifier
                    .padding(30.dp, 0.dp, 10.dp, 5.dp)
                    .offset((-100).dp, 30.dp),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = facultyGlyphic,
                    fontWeight = FontWeight.W100,
                    color = White,
                    textAlign = TextAlign.Start,
                ),

                )

            TextField(
                value = amount,
                onValueChange = { newText ->
                    val oldText = amount
                    lastChangeTime = logTextFieldBehavior(
                        label = "Password",
                        oldText = oldText,
                        newText = newText,
                        lastTimestamp = lastChangeTime
                    )
                    amount = newText
                },
                modifier = Modifier
                    .padding(0.dp, 0.dp , 10.dp, 0.dp)
                    .size(300.dp, 60.dp)
                    .offset(0.dp, 20.dp),
                textStyle = TextStyle(
                    fontSize = 22.sp,
                    fontFamily = facultyGlyphic,
                    //fontWeight = FontWeight.W100,
                    color = White,
                ),
                singleLine = true,
                placeholder = { Text("0.00", fontSize = 24.sp, color = Color.LightGray) },
                shape = Shapes().large,
                leadingIcon = {Image(
                    imageVector = Icons.Default.CurrencyRupee,
                    "Rupees"
                )},
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
                    keyboardType = KeyboardType.NumberPassword,
                    showKeyboardOnFocus = true),

                )

            Button(
                onClick = { navController.navigate("payment_success") },
                modifier = Modifier
                    .padding(16.dp)
                    .size(300.dp, 55.dp)
                    .offset(0.dp, 120.dp)
                    .clickable(onClick = { /**/ })
                    .clip(shape = RoundedCornerShape(66.dp))
                    .border(
                        width = 2.dp,
                        brush = Brush.verticalGradient(
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
                                Color(128, 128, 128, 200),
                                BlendMode.Luminosity
                            ),
                            blurRadius = 2.dp,
                            noiseFactor = 0f
                        )
                    ),
                content = { Text(
                    text = "Pay",
                    fontSize = 22.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.W300) },
                colors = ButtonColors(
                    contentColor = Color.White,
                    containerColor = Color.Transparent,
                    disabledContentColor = Gray,
                    disabledContainerColor = Color.Gray),
                shape = ShapeDefaults.Medium
            )

        }
    }
}
