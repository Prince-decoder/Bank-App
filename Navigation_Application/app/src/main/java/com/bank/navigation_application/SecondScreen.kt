package com.bank.navigation_application

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SecondScreen(name: String, roll: Int, navigationtofirst:()-> Unit,
                 navigationtoThird:()-> Unit)
{
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Hello!$name\n your roll number is$roll")
    }
    Row(modifier = Modifier.fillMaxSize().padding(30.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween) {
        Button(onClick = {
            navigationtofirst()
        }) {
            Text("prev")
        }
        Button(onClick = {
            navigationtoThird()
        }) {
            Text("Next")
        }
    }
}
@Preview(showSystemUi = true)
@Composable
fun SecondPreview()
{
    //SecondScreen()
}