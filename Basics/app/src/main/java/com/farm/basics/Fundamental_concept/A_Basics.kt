package com.farm.basics.Fundamental_concept

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun Greeting( s: String)
{
    Text(
        text="hello jetpack compose by $s"
    )

}
//@Preview(showSystemUi = true)
//@Composable
//fun Greetingpre()
//{
//    Greeting()
//}