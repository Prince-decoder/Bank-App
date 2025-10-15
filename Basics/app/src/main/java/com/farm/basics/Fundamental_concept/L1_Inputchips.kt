package com.farm.basics.Fundamental_concept



import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Sample_InputChip()
{
    var enable by remember {
        mutableStateOf(true)
    }
    if(enable)
    {
        InputChip(
            onClick = {
                enable=!enable
            },
            label = {
                Text("helow")
            },
            selected = enable,
            avatar = {
                Icon(Icons.Filled.Person,
                    "helow")
                Modifier.size(InputChipDefaults.AvatarSize)
            },
            trailingIcon = {
                Icon(Icons.Filled.Close,
                    "hwjcna",
                    Modifier.size(InputChipDefaults.AvatarSize))
            }
        )
    }
}
//@Preview(showSystemUi = true)
//@Composable
//fun demo()
//{
//    Sample_InputChip()
//}