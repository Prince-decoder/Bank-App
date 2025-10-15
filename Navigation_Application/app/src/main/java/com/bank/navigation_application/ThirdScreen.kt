package com.bank.navigation_application

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ThirdScreen(navigationtoFirstScreen:()-> Unit,navigationtoSecond:()-> Unit)
{
    Column(modifier=Modifier.fillMaxSize().padding(30.dp)) {
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            Button(onClick = {
                navigationtoSecond()
            }) {
                Text("To second Screen")
            }
            Button(onClick = {
                navigationtoFirstScreen()
            }) {
                Text("To first Screen")
            }
        }
        Text("this is third page")
    }
}
@Preview(showSystemUi = true)
@Composable
fun thirdPreview()
{
//    ThirdScreen({})
}