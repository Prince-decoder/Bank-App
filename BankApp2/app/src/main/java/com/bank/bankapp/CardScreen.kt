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
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.draw.shadow
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
fun CardsScreenPreview()
{
    val previewNavController = rememberNavController()

    CardsScreen(
        navController = previewNavController
    )
}

@OptIn(ExperimentalHazeMaterialsApi::class)
@Composable
fun CardsScreen(navController: NavHostController){
    val hazeState = rememberHazeState()
    var CVVVisibility by remember { mutableStateOf(false) }

    val icon = if (CVVVisibility){
        Icons.Default.Visibility
    }
    else
        Icons.Default.VisibilityOff

    Box (
        modifier = Modifier
            .fillMaxSize()

    ) {

        Image(
            painterResource(R.drawable.background3),
            contentDescription = "Background",
            modifier = Modifier
                //.rotate(180f)
                .requiredSize(900.dp)
                .hazeSource(state = hazeState)
        )

        Column(
            modifier = Modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
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

                Spacer(
                    Modifier
                        .size(50.dp)
                        .weight(5f)
                )
            }

            Text(
                text = "Your Cards",
                style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(11, 1, 59, 255),
                    fontFamily = facultyGlyphic,
                ),
                modifier = Modifier
                    .padding(15.dp, 15.dp, 15.dp, 15.dp)
                    .offset(10.dp, (0).dp)
                    .size(340.dp, 40.dp),
                textAlign = TextAlign.Left
            )

            Image(
                imageVector = Icons.Default.CreditCard,
                contentDescription = "Credit Card",
                modifier = Modifier
                    .size(360.dp, 230.dp)
                    .shadow(
                        elevation = 10.dp,
                        shape = RoundedCornerShape(10.dp),
                        ambientColor = Color.Black,
                        spotColor = Color.Black
                    )
            )

            Text(
                text = "CVV",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(11, 1, 59, 255),
                    fontFamily = facultyGlyphic,
                ),
                modifier = Modifier
                    .padding(15.dp, 15.dp, 15.dp, 15.dp)
                    .offset(10.dp, (20).dp)
                    .size(340.dp, 40.dp),
                textAlign = TextAlign.Left
            )

            TextField(
                value = "123",
                onValueChange = {  },
                textStyle = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W300,
                    color = Color.LightGray,
                    fontFamily = FontFamily.SansSerif,
                ),
                modifier = Modifier
                    .padding(15.dp, 0.dp, 15.dp, 0.dp)
                    .size(340.dp, 60.dp)
                    .offset(0.dp, (10).dp)
                    .clip(RoundedCornerShape(10.dp))
                    .border(
                        BorderStroke(2.dp, Color.Gray),
                        shape = RoundedCornerShape(10.dp)
                    ),
                enabled = false,
                readOnly = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(255, 255, 255, 150),
                    unfocusedContainerColor = Color(255, 255, 255, 150),
                    focusedIndicatorColor = Color.Gray,
                    unfocusedIndicatorColor = Color.LightGray,
                    disabledIndicatorColor = Color.Gray,
                    focusedTextColor = Color(11, 1, 59, 255),
                    unfocusedTextColor = Color(11, 1, 59, 255),
                    disabledTextColor = Color.White,
                    disabledContainerColor = Color.DarkGray
                ),
                trailingIcon = {
                    IconButton(onClick = { CVVVisibility = !CVVVisibility}) {
                        Icon(
                            imageVector = icon,
                            contentDescription = "Password Icon",
                            modifier = Modifier
                                .padding(5.dp)
                                .size(80.dp),
                            tint = Color(111, 221, 159, 255)
                        )
                    }
                },
                visualTransformation = if (CVVVisibility) VisualTransformation.None
                else PasswordVisualTransformation( mask = 0xFE61.toChar())

            )
        }
    }
}