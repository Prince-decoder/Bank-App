package com.farm.basics.Fundamental_concept

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
//these create lazy column
fun Sample_LazyColumn()
{
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center

    ) {
        Text("Lazy lkalnsf")
        LazyColumn(modifier = Modifier.fillMaxWidth().height(200.dp).background(Color.Magenta)) {
            items(100)
            {
                index->Text(text = "Ashutosh $index",
                    modifier = Modifier.fillMaxWidth().padding(16.dp).background(Color.Cyan))
            }
        }
    }
}

@Composable
fun Sample_LazyRow()
{
    Row (
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center

    ) {
        Text("Lazy lkalnsf")
        LazyRow(modifier = Modifier.fillMaxSize()
            .height(200.dp)
            .background(Color.Magenta)) {
            items(100)
            {
                    index->Text(text = "Ashutosh $index",
                modifier = Modifier.fillMaxSize()
                    .padding(16.dp)
                    .background(Color.Cyan))
            }
        }
    }
}
//@Preview(showSystemUi = true)
//@Composable
//fun demo()
//{
//    Sample_LazyRow()
//}