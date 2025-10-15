package com.farm.basics.Fundamental_concept

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
/*modifiers are used to modify the layout
* .fillMaxSize():- this take full display of phone
* .background():- can fill color of background*/
fun Sample_Modifiers()
{
    Box(
        modifier = Modifier.fillMaxSize()
            .background(color = Color.Green),
        contentAlignment = Alignment.Center
    ){
        Text("Helow world"
            ,color = Color.Magenta,
            fontStyle = FontStyle.Italic
        ,)
    }
}
//@Preview(showSystemUi = true)
//@Composable
//fun demo()
//{
//    Sample_Modifiers()
//}