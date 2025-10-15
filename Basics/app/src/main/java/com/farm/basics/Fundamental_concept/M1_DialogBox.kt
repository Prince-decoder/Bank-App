package com.farm.basics.Fundamental_concept

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.farm.basics.R

@Composable
fun Sample_DialogWithImage(
) {
    val context = LocalContext.current
    Dialog(onDismissRequest = {

    })
    {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier.fillMaxSize()
                    .height(350.dp)
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.devop),
                    "hulala",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.height(250.dp)
                )
                Text(
                    text = "this is the dialog",
                    modifier = Modifier.padding(16.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    TextButton(
                        onClick = {
                            Toast.makeText(context, "you can do anything", Toast.LENGTH_SHORT)
                                .show()
                        },
                        modifier = Modifier.padding(150.dp)
                    ) {
                        Text("done")
                    }
                    TextButton(
                        onClick = {
                            Toast.makeText(context, "you can do anything", Toast.LENGTH_SHORT)
                                .show()
                        },
                        modifier = Modifier.padding(150.dp)
                    ) {
                        Text("not done")
                    }
                }
            }
        }
    }
}
//@Preview(showSystemUi = true)
//@Composable
//fun demo()
//{
//    Sample_DialogWithImage()
//}