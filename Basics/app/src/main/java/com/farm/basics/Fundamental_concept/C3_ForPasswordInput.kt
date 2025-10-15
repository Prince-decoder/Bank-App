package com.farm.basics.Fundamental_concept

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SamplePassword()
{
    var text by rememberSaveable {
        mutableStateOf("")
    }
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center){
    OutlinedTextField(
        value="",
        onValueChange = {
            text=it
        },
        label = {
            Text("enter password")
        },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        )
    )
}
}
//@Preview
//@Composable
//fun demo()
//{
//    SamplePassword()
//}