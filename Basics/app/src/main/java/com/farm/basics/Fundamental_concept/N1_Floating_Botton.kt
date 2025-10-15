package com.farm.basics.Fundamental_concept

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.MotionScene

@Composable
fun Sample_FloatingBotton() {
    val context = LocalContext.current
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        ExtendedFloatingActionButton(
            onClick = {
                Toast.makeText(context, "hey", Toast.LENGTH_SHORT)
            },
            content = {
                Text(text = "this is floating botton")
            }
        )
    }
}
//@Preview(showSystemUi = true)
//@Composable
//fun demo()
//{
//    Sample_FloatingBotton()
//}