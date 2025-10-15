package com.bank.bankapp

import android.graphics.drawable.Icon
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.bank_app.R

@Composable
@Preview(showBackground = true)
fun PaymentAnimationPreview(){
    val previewNavController = rememberNavController()

    PaymentAnimation(previewNavController)
}
@Composable
fun PaymentAnimation(navController: NavHostController)
{
    Box(modifier = Modifier.fillMaxSize().background(Color.Black), contentAlignment = Alignment.Center) {
        AnimationBackground(getAnimationUri())

        Row(
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.Bottom
        ){
            IconButton(
                onClick = {},
                modifier = Modifier
                    .size(170.dp,50.dp)
                    .offset(0.dp, (-80).dp),
                colors = IconButtonColors(
                    containerColor = Color(24, 79, 173, 255),
                    contentColor = Color.White,
                    disabledContainerColor = Color.Gray,
                    disabledContentColor = Color.LightGray,
                ),
                enabled = true,
                interactionSource = null,
            ) {
                Row(){
                    Image(
                        imageVector = Icons.Default.Share,
                        contentDescription = null,
                        modifier = Modifier.size(25.dp)
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(text = "Share", color = Color.White, fontSize = 15.sp, modifier = Modifier.offset(0.dp, (2).dp))
                }
            }
            IconButton(
                onClick = {navController.navigate("home_screen")},
                modifier = Modifier
                    .size(170.dp,50.dp)
                    .offset(0.dp, (-80).dp),
                colors = IconButtonColors(
                    containerColor = Color(24, 79, 173, 255),
                    contentColor = Color.White,
                    disabledContainerColor = Color.Gray,
                    disabledContentColor = Color.LightGray,
                ),
                enabled = true,
                interactionSource = null,
            ) {
                Row(){
                    Image(
                        imageVector = Icons.Default.Home,
                        contentDescription = null,
                        modifier = Modifier.size(25.dp)
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(text = "Home", color = Color.White, fontSize = 15.sp, modifier = Modifier.offset(0.dp, (2).dp))
                }
            }
        }
    }

}

@Composable
fun getAnimationUri(): Uri {
    val context = LocalContext.current
    val videoUri = "android.resource://${context.packageName}/${R.raw.payment_animation}".toUri()
    return videoUri
}

@Composable
fun AnimationBackground(videoUri: Uri) {
    val context = LocalContext.current
    val exoPlayer = remember { context.buildExoPlayer(videoUri) }

    DisposableEffect(
        AndroidView(
            factory = { it.buildPlayerView(exoPlayer) },
            modifier = Modifier.size(300.dp).clip(CircleShape)
        )
    ) {
        onDispose {
            exoPlayer.release()
        }
    }
}