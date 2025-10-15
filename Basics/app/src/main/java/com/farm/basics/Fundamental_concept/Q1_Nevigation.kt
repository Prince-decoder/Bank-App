package com.farm.basics.Fundamental_concept

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Sample_DetailedDrawer() {
    val drawer = rememberDrawerState(
        initialValue = DrawerValue.Closed
    )
    val scope = rememberCoroutineScope()
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        ModalNavigationDrawer(
            drawerContent = {
                ModalDrawerSheet{
                    Column(
                        modifier = Modifier.run {
                            padding(20.dp)
                                .verticalScroll(rememberScrollState())
                        }
                    )
                    {
                        Text("Drawer Title")
                        HorizontalDivider()
                        Text("alknalnl")
                    }
                    NavigationDrawerItem(
                        label = {
                            Text("item1")
                        },
                        selected = false,
                        onClick = {}
                    )
                    NavigationDrawerItem(
                        label = {
                            Text("item2")
                        },
                        selected = false,
                        onClick = {}
                    )
                }
            },
            drawerState = drawer
        ) {
            Scaffold(topBar = {
                TopAppBar(
                    title = {Text("navigation drawer")},
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch { if(drawer.isClosed){
                                drawer.open()
                                }
                                    else{
                                        drawer.close()
                                }
                                }
                            }
                        ) {
                            Icon(Icons.Default.Menu, contentDescription = "")
                        }
                    }
                )
            }) {innerpadding->
                Column(modifier = Modifier.padding(innerpadding)) {  }
            }
        }
    }
}
//@Preview(showSystemUi = true)
//@Composable
//fun demo()
//{
//    Sample_DetailedDrawer()
//}