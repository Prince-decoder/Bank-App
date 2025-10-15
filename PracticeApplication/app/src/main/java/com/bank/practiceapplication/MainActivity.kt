package com.bank.practiceapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bank.practiceapplication.ui.theme.PracticeApplicationTheme
import androidx.lifecycle.viewmodel.compose.viewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticeApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {innerPadding ->
                    //ShoppingList(modifier = Modifier.padding(innerPadding))
                    var cvm: CounterViewModel = viewModel()
                    CounterApp(modifier = Modifier.padding(innerPadding),cvm)
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun CounterApp(modifier: Modifier= Modifier,
               Model: CounterViewModel= CounterViewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text("Count= ${Model.count.value}",
            fontSize = 18.sp)
        Row()
        {
            Button(modifier = Modifier.padding(8.dp), onClick = {
                Model.increment()
            }) {
                Text("Increment")
            }
            Button(
                modifier = Modifier.padding(8.dp),
                onClick = {
                    Model.decrement()
                }) {
                Text("Decrement")
            }
        }
    }
}