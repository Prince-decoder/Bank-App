package com.farm.basics.Fundamental_concept


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults

import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview

import com.farm.basics.R
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Sample_ElivatedCard()
{
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center)
    {
        ElevatedCard(
            elevation = CardDefaults.cardElevation(50.dp),
            modifier = Modifier.background(Color.White)
        ) {
//            Image(painter = painterResource(R.drawable.devop),
//                contentDescription = "devop image",
//                modifier = Modifier.size(200.dp))
            Text(text = "helow",
                fontStyle = FontStyle.Italic,
                fontSize = 20.sp,
                modifier = Modifier.basicMarquee()
                )
    }
}
}
//@Preview(showSystemUi = true)
//@Composable
//fun demo() {
//    Sample_ElivatedCard()
//}
