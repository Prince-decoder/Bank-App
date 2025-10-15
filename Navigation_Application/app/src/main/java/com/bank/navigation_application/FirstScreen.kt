package com.bank.navigation_application

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun FirstScreen(nevigationtosecond: (String, Int) -> Unit)
{
    var Name by remember{ mutableStateOf("") }
    var Roll by remember{mutableStateOf("")}
    Column(modifier=Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment=Alignment.CenterHorizontally) {
        OutlinedTextField(
            value = Name,
            onValueChange = {
                Name=it
            },
            label = {Text("Name")}
        )
        OutlinedTextField(
            value = Roll,
            onValueChange = {
                Roll=it
            },
            label = {Text("Roll no.")}
        )
        Row {
            Button(onClick = {
                nevigationtosecond(Name,Roll.toInt())
            }) {
                Text("Save")
            }
            Button(onClick = {}) {
                Text("Cancel")
            }
        }
    }
}
@Preview(showSystemUi = true)
@Composable
fun Demo()
{
    //firstScreen(modifier = Modifier.padding())
}