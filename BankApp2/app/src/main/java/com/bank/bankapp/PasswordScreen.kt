package com.bank.bankapp

import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Magenta
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
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
import kotlin.collections.listOf
import com.example.bank_app.R


@Composable
@Preview(showBackground = true)
fun PasswordScreenPreview(){
    val previewNavController = rememberNavController()

    PasswordScreen(previewNavController, activity = ComponentActivity())
}

@Composable
fun PasswordScreen(navController: NavHostController, activity: ComponentActivity) {

    val navigateToRoute = "home_screen"
    val hazeState = rememberHazeState()

    val facultyGlyphic = FontFamily(
        Font(R.font.faculty_glyphic_regular)
    )
    val gradientColors = listOf(Yellow, Red, Cyan, Magenta, Blue)

    Box(
        modifier = Modifier.fillMaxSize()
            .background(
                brush = Brush.linearGradient(listOf(Color.Black,
                Color.Blue)))
    ) {

//        Image(
//            painterResource(R.drawable.symbol),
//            contentDescription = "Background",
//            modifier = Modifier
//                //.rotate(180f)
//                .requiredSize(300.dp)
//                .hazeSource(state = hazeState)
//        )

        Surface(
            //shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .size(370.dp, 400.dp)
                .align(Alignment.Center)
                .offset(0.dp, 50.dp)
                .clip(shape = RoundedCornerShape(66.dp))
                .border(
                    width = 2.dp,
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
                        White.copy(alpha = 0.1f),
                        tint = HazeTint(
                            Color(128, 128, 128, 0),
                            BlendMode.Luminosity
                        ),
                        blurRadius = 30.dp,
                        noiseFactor = 0f
                    )
                ),
            color = Transparent
        ) {  }

        Column(
            modifier = Modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Icon(
                painter = painterResource(R.drawable.symbol),
                contentDescription = null,
                tint = Color(139, 8, 168, 255),
                modifier = Modifier
                    .size(200.dp)
            )

            Spacer(modifier = Modifier.size(0.dp, 20.dp))

            Text(
                text = buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            color = Color.White,
                            fontSize = 25.sp,
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight.Bold,
                        )
                    ) {
                        append("Enter Password")
                    }
                },
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .offset(0.dp, (-130).dp)
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .offset(0.dp, (-160).dp)
            ) {
                repeat(6) { index ->
                    PasswordChar()
                }
            }
            Button(
                onClick = { navController.navigate(navigateToRoute) },
                modifier = Modifier
                    .padding(16.dp)
                    .size(300.dp, 55.dp)
                    .offset(0.dp, 60.dp)
                    .clickable(onClick = { /**/ })
                    .clip(shape = RoundedCornerShape(66.dp))
                    .border(
                        width = 2.dp,
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Companion.Unspecified,
                                Color.Unspecified,
                            ),
                        ),
                        shape = RoundedCornerShape(66.dp)
                    )
                    .hazeEffect(
                        state = hazeState,
                        style = HazeStyle(
                            White.copy(alpha = 0.1f),
                            tint = HazeTint(
                                Color(128, 128, 128, 40),
                                BlendMode.Luminosity
                            ),
                            blurRadius = 15.dp,
                            noiseFactor = 0f
                        )
                    ),
                content = { Text(
                    text = "Login",
                    fontSize = 22.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.W300) },
                colors = ButtonColors(
                    contentColor = Color.Blue,
                    containerColor = Color.Gray,
                    disabledContentColor = Gray,
                    disabledContainerColor = Color.Gray),
                shape = ShapeDefaults.Medium
            )
            LinearProgressIndicator(
                modifier = Modifier
                    .offset(0.dp, 114.dp)
                    .fillMaxWidth()
            )
        }
    }
}


@Composable
fun PasswordChar(
    modifier: Modifier = Modifier
){
    val pattern = remember { Regex("^[^\\t]*\$") } //to not accept the tab key as value
    var (text,setText) = remember { mutableStateOf("") }
    val maxChar = 1
    val focusManager = LocalFocusManager.current
    var passwordVisibility by remember { mutableStateOf(false) }
    val gradientColors = listOf(Yellow, Red, Cyan, Magenta, Blue)


    LaunchedEffect(
        key1 = text,
    ) {
        if (text.length == maxChar) {
            focusManager.moveFocus(
                focusDirection = FocusDirection.Next,
            )
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        TextField(
            value = text,
            onValueChange = {
                if (it.length <= maxChar &&
                    ((it.isEmpty() || it.matches(pattern)))
                )
                    setText(it)
            },
            modifier = modifier
                .width(50.dp)
                .onKeyEvent {
                    if (it.key == Key.Tab) {
                        focusManager.moveFocus(FocusDirection.Next)
                        true
                    }
                    if (text.isEmpty() && it.key == Key.Backspace) {
                        focusManager.moveFocus(FocusDirection.Previous)
                    }
                    false
                }
                .border(
                    width = 1.dp,
                    brush = Brush.linearGradient(gradientColors),
                    shape = Shapes().small
                ),
            textStyle = LocalTextStyle.current.copy(
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                color = White
            ),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Transparent,
                unfocusedIndicatorColor = Transparent,
                focusedContainerColor = Transparent,
                unfocusedContainerColor = Transparent,
                cursorColor = Cyan,
            ),
            visualTransformation = if (passwordVisibility) VisualTransformation.None
            else PasswordVisualTransformation(mask = 0xFE61.toChar()),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Send
            ),
        )
        HorizontalDivider(
            Modifier
                .width(28.dp)
                .padding(bottom = 2.dp)
                .offset(0.dp, (-10).dp),
            color = Color.Blue,
            thickness = 1.dp
        )
    }
}