package com.farm.basics.Fundamental_concept

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Sample_PartialBottomSheet() {
    var showbuttom by remember {
        mutableStateOf(false)
    }
    var sheetstate = rememberModalBottomSheetState(
        skipPartiallyExpanded = false
    )
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = {
                    showbuttom = true
                }
            ) {
                Text("open the bottom sheet")
            }
            if (showbuttom) {
                ModalBottomSheet(
                    modifier = Modifier.fillMaxHeight(),
                    sheetState = sheetstate,
                    onDismissRequest = {
                        showbuttom = false
                    }) {
                    Text(
                        text = "naslfkna kjbalkvbjaS VBSAKLDBNJVLKSDB ",
                        modifier = Modifier.padding(15.dp)
                    )
                }
            }
        }
    }
}
//@Preview(showSystemUi = true)
//@Composable
//fun demo()
//{
//    Sample_PartialBottomSheet()
//}