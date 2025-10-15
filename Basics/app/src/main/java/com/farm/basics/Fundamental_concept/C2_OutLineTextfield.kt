package com.farm.basics.Fundamental_concept

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
//import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.farm.basics.R
@Composable
fun SampleOutline()
{
    var text by remember {                      //remember is used to remember the state so that it can be updated
        //can also use rememberSaveable these are also used to remember the values and also the state eg if u put few value but receive a call, when u come back what u had written will be saved by this
     mutableStateOf("")
    }
    val rainbow=mutableListOf(
        colorResource( R.color.purple_700),
        colorResource( R.color.white),
        colorResource( R.color.purple_200),
        colorResource( R.color.teal_200)
    )
    var brush=remember {
        Brush.linearGradient(
            colors = rainbow
        )
    }
    Box(modifier=Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {
        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it
            },
            label = {
                Text("Name")
            },
            textStyle = TextStyle(brush)
        )
    }
}
@Composable
fun SampleTextField()
{
    var text by remember {
        mutableStateOf("")
    }
    var C=mutableListOf(
        colorResource(R.color.applered),
        colorResource(R.color.white),
        colorResource(R.color.teal_700),
        colorResource(R.color.teal_200)
    )
    val brus=remember {
        Brush.linearGradient(
            colors = C
        )
    }
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center)
    {
        TextField(
            value = text,
            onValueChange = {
                text=it
            },
            label = {
                Text("age")
            },
            textStyle = TextStyle(brush = brus)
        )
    }
}
//@Preview
//@Composable
//fun demo()
//{
//    //SampleOutline()
//    SampleTextField()
//}