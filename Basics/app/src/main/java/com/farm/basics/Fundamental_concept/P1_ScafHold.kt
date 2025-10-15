package com.farm.basics.Fundamental_concept

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Sample_Scaffold()
{
    var presses by remember {
        mutableStateOf(0)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                    scrolledContainerColor = MaterialTheme.colorScheme.error,
                    navigationIconContentColor = MaterialTheme.colorScheme.scrim,
                    actionIconContentColor = MaterialTheme.colorScheme.errorContainer,
                ),
                title = { Text("text bar") }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
            ){
                Text(
                    text = "bottom ",
                    modifier = Modifier.padding(16.dp)

                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {presses++}
            ) {
                Text("i'v been $presses clicked")
            }
        }
    ) {innerPading->
        Column(modifier = Modifier.fillMaxSize().padding(innerPading)
            .background(Color.Blue)) {
        Text("this is the scaffold contant", color = Color.Cyan)
    }
    }
}
//@Preview(showSystemUi = true)
//@Composable
//fun demo()
//{
//    Sample_Scaffold()
//}