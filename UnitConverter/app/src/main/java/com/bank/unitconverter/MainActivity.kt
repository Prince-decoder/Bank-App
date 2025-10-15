package com.bank.unitconverter

import android.R.id.input
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bank.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
//                    Caption()
                }
            }
        }
    }
}

@Composable
fun UnitConverter()
{
    Column(modifier = Modifier.fillMaxSize()
        ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        var leftclick by remember { mutableStateOf(false) }
        var rightclick by remember { mutableStateOf(false) }
        Row() {
            Lines("Converter")
        }
        var value by remember { mutableStateOf("") }
        var factor by remember { mutableStateOf(0.0) }
        var ofactor by remember { mutableStateOf(0.0) }
        var output by remember { mutableStateOf("") }
        fun Converter()
        {
            val input=value.toDoubleOrNull()?:0.0                       //if null then put 0.0
            val result=((input*factor)/ofactor)//.roundToInt()
            output=result.toString()
        }
        OutlinedTextField(value = value,
            onValueChange= {
                value=it
                Converter()
            },
            label = {Text("Unit")}
//            colors = {Color.Blue}
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row()
        {
            val select1 = remember {
                mutableStateOf("select")
            }
            val select2 = remember {
                mutableStateOf("select")
            }
            Box() {
                Button(
                    onClick = { leftclick = !leftclick }
                ) {
                    Text(select1.value)
                    Icon(
                        Icons.Default.ArrowDropDown,
                        "Dropdown"
                    )
                }
                DropdownMenu(expanded = leftclick, onDismissRequest = {leftclick=false}) {
                    DropdownMenuItem(
                        text = { Text("Meter") },
                        onClick = { leftclick = false
                            select1.value="meter"
                            factor=1.0
                            Converter()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Centimeter") },
                        onClick = { leftclick = false
                        select1.value="centimeter"
                        factor=0.01
                        Converter()}
                    )
                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = { leftclick = false
                            select1.value="feet"
                        factor=0.3048
                        Converter()}
                    )
                    DropdownMenuItem(
                        text = { Text("Millimeter") },
                        onClick = { leftclick = false
                            select1.value="Millimeter"
                        factor=0.001
                        Converter()}
                    )
                }
            }
            Spacer(modifier = Modifier.width(31.dp))
            Box {
                Button(onClick = { rightclick = !rightclick }) {
                    Text(select2.value)
                    Icon(
                        Icons.Default.ArrowDropDown,
                        "DropDown"
                    )
                }
                DropdownMenu(expanded = rightclick, onDismissRequest = {rightclick=false}) {
                    DropdownMenuItem(
                        text = { Text("Meter") },
                        onClick = { rightclick = false
                        select2.value="Meter"
                            ofactor=1.0
                            Converter()}
                    )
                    DropdownMenuItem(
                        text = { Text("Centimeter") },
                        onClick = { rightclick = false
                            select2.value="Centimeter"
                            ofactor=0.01
                            Converter()}
                    )
                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = { rightclick = false
                            select2.value="Feet"
                            ofactor=0.3048
                            Converter()}
                    )
                    DropdownMenuItem(
                        text = { Text("Millimeter") },
                        onClick = { rightclick = false
                            select2.value="Millimeter"
                            ofactor=0.001
                            Converter()}
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Result=    ${output}"
        , style = MaterialTheme.typography.headlineMedium
        )
    }
}
@Composable
fun Lines(name:String)
{
    Text(
        text = " $name",
        style = TextStyle(
            fontFamily = FontFamily.Monospace,
            fontSize = 26.sp,
            color = Color.Magenta
        )
    )
}
@Preview(showSystemUi = true)
@Composable
fun Demo()
{
    UnitConverter()
}











//@Preview(showBackground = true)
//@Composable
//fun Caption()
//{
//    val tresure=remember { mutableStateOf(0) }
//    val direction=remember { mutableStateOf("North") }
//    var storm by remember { mutableStateOf("") }
//    Column(modifier = Modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally) {
//        Button(onClick = {
//            direction.value="South"
//            if(Random.nextBoolean())
//            {
//                tresure.value+=1
//                storm="No storm ahead"
//            }
//            else{
//                storm="storm ahead!"
//            }
//        }) {
//            Text(text = "Sails South")
//        }
//        Button(onClick = {
//            direction.value="North"
//            if(Random.nextBoolean())
//            {
//                tresure.value+=1
//                storm="No storm ahead"
//            }
//            else{
//                storm="storm ahead!"
//            }
//        }) {
//            Text(text = "Sails North")
//        }
//        Button(onClick = {
//            direction.value="East"
//            if(Random.nextBoolean())
//            {
//                tresure.value+=1
//                storm="No storm ahead"
//            }
//            else{
//                storm="storm ahead!"
//            }
//        }) {
//            Text(text = "Sails East")
//        }
//        Button(onClick = {
//            direction.value="West"
//            if(Random.nextBoolean())
//            {
//                tresure.value+=1
//                storm="No storm ahead"
//            }
//            else{
//                storm="storm ahead!"
//            }
//        }) {
//            Text(text = "Sails East")
//        }
//        Text(text = "current direction =${direction.value}")
//        Text(text = "Tresure found =${tresure.value}")
//        Text(text = storm)
//
//    }
//}