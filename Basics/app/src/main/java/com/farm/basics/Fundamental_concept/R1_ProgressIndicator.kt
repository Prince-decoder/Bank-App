package com.farm.basics.Fundamental_concept

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Sample_Indicator()
{
    var loading by remember {
        mutableStateOf(false)
    }
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start){
    Button(
        onClick = {loading=true}, enabled = !loading
    ) { Text("Start loading") }
        Spacer(modifier = Modifier.height(25.dp))
    if (!loading) return CircularProgressIndicator(
        modifier = Modifier.width(64.dp),
        trackColor = MaterialTheme.colorScheme.surfaceVariant
    )
}
}
//@Preview(showSystemUi = true)
//@Composable
//fun demo()
//{
//    Sample_Indicator()
//}