package com.farm.basics.Fundamental_concept

import android.graphics.drawable.Icon
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.contracts.contract

@Composable
fun Sample_Mainu()
{
    var contant= LocalContext.current
    var expandable by remember {
        mutableStateOf(false)
    }
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center){
    Box(modifier = Modifier
        .padding(16.dp),

        )
    {
        IconButton(
            onClick = {expandable=!expandable}
        ) {
            Icon(Icons.Default.MoreVert, contentDescription = "thsi is 3 idot")
        }
        DropdownMenu(
            expanded = expandable,
            onDismissRequest = {expandable=false}
        ) {
            DropdownMenuItem(
                text = { Text("profile") },
                trailingIcon = {Icon(Icons.Outlined.Person,"profile",)},
                onClick = {
                    Toast.makeText(contant,"akcjnakcn", Toast.LENGTH_SHORT).show() }
            )
            DropdownMenuItem(
                text = { Text("Setting") },
                trailingIcon = {Icon(Icons.Outlined.Person,"profile",)},
                onClick = {
                    Toast.makeText(contant,"akcjnakcn", Toast.LENGTH_SHORT).show() }
            )
            HorizontalDivider()
        }

    }

}}
//@Preview(showSystemUi = true)
//@Composable
//fun demo()
//{
//    Sample_Mainu()
//}

