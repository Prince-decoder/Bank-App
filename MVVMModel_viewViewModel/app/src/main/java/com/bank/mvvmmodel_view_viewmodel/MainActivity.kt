package com.bank.mvvmmodel_view_viewmodel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bank.mvvmmodel_view_viewmodel.ui.theme.MVVMModel_viewViewModelTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MVVMModel_viewViewModelTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val x: ViewMModel= viewModel()
                    View(modifier = Modifier.padding(innerPadding), x)
                }
            }
        }
    }
}
@Composable
fun View(modifier: Modifier,
         Model: ViewMModel= ViewMModel()
)
{
    Column(modifier= Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Count= ${Model.Cvvm.value}",
            fontSize = 30.sp)
        Row(modifier= Modifier.fillMaxWidth(),
            ) {
            Button(onClick = {
                Model.increase()
            }) {
                Text("Increase")
            }
            Button(onClick = {
                Model.decrease()
            }) {
                Text("Decrease")
            }
        }
    }
}