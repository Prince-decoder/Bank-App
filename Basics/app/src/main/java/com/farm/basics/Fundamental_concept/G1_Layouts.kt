package com.farm.basics.Fundamental_concept

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import kotlinx.coroutines.NonDisposableHandle.parent

//1. Column, it allows you to place it's children in a vertical sequence.
@Composable
fun Sample_Column()
{
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Blue)
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "text1"
        )
        Text(
            "text2"
        )
        Text(
            "text3"
        )
        Text(
            "text4"
        )
        Text(
            "text5"
        )
    }
}
@Composable
fun Sample_Row()
{
    Row(modifier = Modifier
        .fillMaxHeight()
        .background(Color.Cyan)
        .padding(15.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically) {
        Text("text1")
        Text("text2")
        Text("text3")
        Text("text4")
        Text("text5")
    }
}
@Composable
fun Sample_Box() {
    Box(
        modifier = Modifier
            .size(200.dp)
            .background(Color.Magenta),
        contentAlignment = Alignment.Center
    )
    {
        Box(modifier = Modifier
            .size(150.dp)
            .background(Color.DarkGray)) {
            Text("how are you")
        }
        Box(modifier = Modifier
            .size(100.dp)
            .background(Color.Green)) {
            Text("how are me")
        }
        Box(modifier = Modifier
            .size(50.dp)
            .background(Color.Yellow))
        {
            Text("how are they")
        }
    }
}
//Constaraint Layout:-use only when need and ui is complex
@Composable
fun Sample_ConstraintLayout()
{
    Column {
        //it's same like you do in xml layout, strings are attach to top, bottom,start and end
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.LightGray)
        ) {
            val (text1,text2,text3)=createRefs()
            Text(text = "Bottom left",
                modifier = Modifier.constrainAs(text1){
                bottom.linkTo(parent.bottom, margin = 8.dp)
                    start.linkTo(parent.start, margin = 8.dp)
            })
            Text(text = "center left",
                modifier = Modifier.constrainAs(text2){
                    top.linkTo(parent.top, margin = 8.dp)
                    bottom.linkTo(parent.bottom, margin = 8.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
            Text(text = " top right",
                modifier = Modifier.constrainAs(text3){
                    top.linkTo(parent.top, margin = 8.dp)
//                    bottom.linkTo(parent.bottom, margin = 8.dp)
//                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
        }
    }
}
//@Preview(showSystemUi = true)
//@Composable
//fun demo()
//{
//    Sample_ConstraintLayout()
//}

//best practice
// 1/ over nesting, don't built more then 5 box (it will create performance )