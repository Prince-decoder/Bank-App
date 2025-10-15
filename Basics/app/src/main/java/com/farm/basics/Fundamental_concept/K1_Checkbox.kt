package com.farm.basics.Fundamental_concept

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.tooling.preview.Preview
import java.nio.file.WatchEvent


@Composable
fun Sample_Checkbox() {
    val childChecked = remember {
        mutableListOf(false, false, false)
    }
    val parentState = when {
        childChecked.all { it } -> ToggleableState.On
        childChecked.none() -> ToggleableState.Off
        else -> ToggleableState.Indeterminate
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("select all")
                TriStateCheckbox(
                    state = parentState,
                    onClick = {
                        val newState = parentState != ToggleableState.On
                        childChecked.forEachIndexed { index, bool ->
                            childChecked[index] = newState
                        }
                    }
                )
            }
            childChecked.forEachIndexed { index, bool ->
                Row {
                    Text("option ${index + 1}")
                    Checkbox(
                        checked = bool,
                        onCheckedChange = {
                            childChecked[index] = it
                        }
                    )
                }
            }
        }
        if (childChecked.all { it }) {
            Text("all options are selected")
        }
    }
}
//@Preview(showSystemUi = true)
//@Composable
//fun demo()
//{
//    Sample_Checkbox()
//}
