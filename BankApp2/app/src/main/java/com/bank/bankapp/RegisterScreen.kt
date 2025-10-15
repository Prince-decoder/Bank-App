package com.bank.bankapp


import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
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
fun RegisterScreenPreview(){
    val previewNavController = rememberNavController()

    RegisterScreen(previewNavController)
}

@Composable
fun RegisterScreen(navController: NavHostController) {

    val navigateToIntro = "intro_screen"

    var lastChangeTime by remember { mutableStateOf(System.currentTimeMillis()) }

    val hazeState = rememberHazeState()

    var name by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val maxLenName = 12
    val maxLenUsername = 10

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

    Box(
        modifier = Modifier
            .fillMaxSize(),

        ) {

        Image(
            painterResource(R.drawable.background3),
            contentDescription = "Background",
            modifier = Modifier
                //.rotate(180f)
                .requiredSize(900.dp)
                .hazeSource(state = hazeState)
        )

        Surface(
            modifier = Modifier
                .size(370.dp, 330.dp)
                .align(Alignment.Center)
                .offset(0.dp, 8.dp)
                .clip(shape = RoundedCornerShape(66.dp))
                .border(
                    width = 2.dp,
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.White.copy(alpha = 0.7f),
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
                    .size(85.dp),
                containerColor = Color.Transparent,
                contentColor = White,
                windowInsets = WindowInsets(0.dp, 20.dp, 0.dp, 0.dp)
            ) {
                NavigationBarItem(
                    selected = true,
                    onClick = { navController.navigate(navigateToIntro)},
                    icon = {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back to Home",
                            modifier = Modifier.size(24.dp),
                            tint = Color.White
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

            Icon(
                painterResource(R.drawable.symbol),
                contentDescription = "App logo",
                modifier = Modifier
                    .size(200.dp)
                    .align(Alignment.CenterHorizontally),
                tint = Color(200, 230, 122, 255)
            )

            TextField(
                value = name,
                onValueChange =  { newText ->
                    val oldText = name
                    lastChangeTime = logTextFieldBehavior(
                        label = "Name",
                        oldText = oldText,
                        newText = newText,
                        lastTimestamp = lastChangeTime
                    )
                    name = newText
                },
                modifier = Modifier
                    .padding(0.dp, 0.dp , 0.dp, 0.dp)
                    .size(300.dp, 60.dp)
                    .offset(0.dp, 20.dp),
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = facultyGlyphic,
                    fontWeight = FontWeight.W300,
                    color = White,
                ),
                singleLine = true,
                label = { Text("Name", modifier = Modifier.offset((-8).dp, 0.dp)) },
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
                    keyboardType = KeyboardType.Password,
                    showKeyboardOnFocus = true),
            )

            Spacer(
                Modifier
                    .size(0.dp, 30.dp)
            )

            TextField(
                value = userName,
                onValueChange = { newText ->
                    val oldText = userName
                    lastChangeTime = logTextFieldBehavior(
                        label = "Username",
                        oldText = oldText,
                        newText = newText,
                        lastTimestamp = lastChangeTime
                    )
                    userName = newText
                },
                modifier = Modifier
                    .padding(0.dp, 0.dp , 10.dp, 0.dp)
                    .size(300.dp, 60.dp)
                    .offset(0.dp, 20.dp),
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = facultyGlyphic,
                    fontWeight = FontWeight.W100,
                    color = White,
                ),
                singleLine = true,
                label = { Text("Username", modifier = Modifier.offset((-8).dp, 0.dp)) },
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
                    keyboardType = KeyboardType.Password,
                    showKeyboardOnFocus = true),
            )

            Spacer(
                Modifier
                    .size(0.dp, 30.dp)
            )

            TextField(
                value = password,
                onValueChange = { newText ->
                    if (newText.length <= 6) {
                        val oldText = password
                        lastChangeTime = logTextFieldBehavior(
                            label = "Password",
                            oldText = oldText,
                            newText = newText,
                            lastTimestamp = lastChangeTime
                        )
                        password = newText
                    }
                },
                modifier = Modifier
                    .padding(0.dp, 0.dp , 10.dp, 0.dp)
                    .size(300.dp, 60.dp)
                    .offset(0.dp, 20.dp),
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = facultyGlyphic,
                    fontWeight = FontWeight.W100,
                    color = White,
                ),
                singleLine = true,
                label = { Text("Password", modifier = Modifier.offset((-8).dp, 0.dp)) },
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
                    keyboardType = KeyboardType.Password,
                    showKeyboardOnFocus = true),
                visualTransformation = PasswordVisualTransformation(0x204E.toChar())
            )

            Button(
                onClick = {
                    if (userName.isNotBlank() && password.length == 6 && name.isNotBlank()) {
                        navController.navigate("home_screen")
                    }
                },
                modifier = Modifier
                    .padding(16.dp)
                    .size(300.dp, 55.dp)
                    .offset(0.dp, 150.dp)
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
                    text = "Register",
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
