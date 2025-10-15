package com.farm.basics.Fundamental_concept


import android.text.Layout
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.farm.basics.R

@Composable
fun Sample_Button() {
    val context = LocalContext.current
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {
                Toast.makeText(context, "button is clicked", Toast.LENGTH_SHORT).show()  //this shows pop up at bottom with message
            }
        ) {
            Text("filled button")
        }
    }
}
@Composable
fun Sample_TonalButton()        //these are usually used for sign in and add to cart button
{
    val context=LocalContext.current
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        FilledTonalButton(
            onClick = {
                Toast.makeText(context,"button is clicked",Toast.LENGTH_SHORT).show()
            }
        ) {
            Text("Tonal button")
        }
    }
}
@Composable
fun Sample_OutlinedButton()        //these are usually used for sign in and add to cart button
{
    val context=LocalContext.current
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        OutlinedButton(
            onClick = {
                Toast.makeText(context,"button is clicked",Toast.LENGTH_SHORT).show()
            }
        ) {
            Text("Outlined button")
        }
    }
}
@Composable
fun Sample_ElivatedBotton()
{
    val context=LocalContext.current
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        ElevatedButton(
            onClick = {
                Toast.makeText(context,"button",Toast.LENGTH_SHORT).show()
            }
        ){Text("elivated botton")}
    }
}
@Composable
fun Sample_TextBotton()
{
    val context=LocalContext.current
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center)
    {
        TextButton(
            onClick = {
                Toast.makeText(context,"hello",Toast.LENGTH_SHORT).show()
            }
        ) {
            Text("how are you")
        }
    }
}
//@Preview(showSystemUi = true)
//@Composable
//fun demo()
//{
////    Sample_OutlinedButton()
////    Sample_ElivatedBotton()
//    Sample_TextBotton()
//}