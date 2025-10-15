package com.bank.bankapp


import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Shapes
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
@Preview(showBackground = true)
fun CardNumField(
    modifier: Modifier = Modifier
){
    val pattern = remember { Regex("^[^\\t]*\$") } //to not accept the tab key as value
    var (text,setText) = remember { mutableStateOf("") }
    val maxChar = 4
    val focusManager = LocalFocusManager.current
    var passwordVisibility by remember { mutableStateOf(false) }
    val numFieldColors = listOf(Color.DarkGray, Color.Gray, Color.LightGray)


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
                .width(80.dp)
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
                    width = 2.dp,
                    brush = Brush.verticalGradient(numFieldColors),
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
                cursorColor = Color.White,
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Send
            ),
        )
        HorizontalDivider(
            Modifier
                .width(70.dp)
                .padding(bottom = 2.dp)
                .offset(0.dp, (-10).dp),
            color = Color.DarkGray,
            thickness = 1.dp
        )
    }
}


@Composable
@Preview(showBackground = true)
fun CardDateNumField(
    modifier: Modifier = Modifier
){
    val pattern = remember { Regex("^[^\\t]*\$") } //to not accept the tab key as value
    var (text,setText) = remember { mutableStateOf("") }
    val maxChar = 2
    val focusManager = LocalFocusManager.current
    var passwordVisibility by remember { mutableStateOf(false) }
    val numFieldColors = listOf(Color.DarkGray, Color.Gray, Color.LightGray)


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
                .width(80.dp)
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
                    width = 2.dp,
                    brush = Brush.verticalGradient(numFieldColors),
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
                cursorColor = Color.White,
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Send
            ),
        )
        HorizontalDivider(
            Modifier
                .width(70.dp)
                .padding(bottom = 2.dp)
                .offset(0.dp, (-10).dp),
            color = Color.DarkGray,
            thickness = 1.dp
        )
    }
}