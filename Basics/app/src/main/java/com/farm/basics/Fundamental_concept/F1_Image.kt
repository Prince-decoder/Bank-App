package com.farm.basics.Fundamental_concept

import androidx.compose.animation.core.EaseOutCirc
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.farm.basics.R

@Composable
fun Sample_Image()
{
    Image(
        painter = painterResource(R.drawable.devop),
        "news",
        contentScale = ContentScale.Crop,
        modifier = Modifier.size(300.dp).clip(CircleShape)
    )
}
//@Preview(showSystemUi = true)
//@Composable
////fun demo()
////{
////    Sample_Image()
////}