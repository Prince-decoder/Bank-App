package com.bank.navigation_application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bank.navigation_application.ui.theme.Navigation_ApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Navigation_ApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyAppk(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MyAppk(modifier: Modifier)
{
    val navController= rememberNavController()
    NavHost(navController=navController, startDestination = "first")
    {
        composable("first"){
            FirstScreen() {name,roll->
                navController.navigate("second/$name/$roll")
            }
        }
        composable("second/{name}/{rolla}"){
            val nsm=it.arguments?.getString("name")?:"no name"
            val nola=it.arguments?.getInt("rolla")?:0
            SecondScreen(name=nsm,
                roll = nola
                ,navigationtofirst = {
                navController.navigate("first")
            },
                navigationtoThird = {
                    navController.navigate("third")
                })
        }
        composable("third"){
            ThirdScreen(
                navigationtoFirstScreen = {
                    navController.navigate("first")
                },
                navigationtoSecond = {
                    navController.navigate("second/kela/36")
                }
            )
        }
    }
}