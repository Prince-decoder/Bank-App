package com.Bank.bankapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold

import androidx.compose.material3.Surface

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.Bank.bankapp.ui.theme.BankAppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BankAppTheme {
                SetBarColor(color = MaterialTheme.colorScheme.background)
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen()
                }
                }
            }
        }
    }

@Composable
private fun SetBarColor(color: Color) {
    var systemUiController=rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(
            color=color
        )
    }
}
@Preview(showSystemUi = true)
@Composable
fun HomeScreen()
{
    Scaffold(
        bottomBar = {
            BottonNavigationBar()
        },
    ) {
        padding->
        Column(modifier = Modifier.fillMaxSize().padding(padding)) {  }
        WalletSection()
    }
}