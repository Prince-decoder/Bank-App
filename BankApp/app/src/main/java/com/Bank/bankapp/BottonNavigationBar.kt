package com.Bank.bankapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Shop
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.Bank.bankapp.Data.BottomNavigation

val items=mutableListOf(
    BottomNavigation(
        titile = "Home",
        icon= Icons.Default.Home
    ),
    BottomNavigation(
        titile = "Shop",
        icon= Icons.Default.Shop
    ),
    BottomNavigation(
        titile = "Statics",
        icon= Icons.Default.BarChart
    ),
    BottomNavigation(
        titile = "Profile",
        icon= Icons.Default.Person
    ),
)









@Composable
fun BottonNavigationBar()
{
    NavigationBar {
        Row(
            modifier = Modifier.background(MaterialTheme.colorScheme.inverseOnSurface)
        ) {
            items.forEachIndexed {
                index, item ->NavigationBarItem(selected=false,
                    onClick={},
                    icon= {
                        Icon(
                            contentDescription = item.titile,
                            imageVector = item.icon,
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    },
                    label = {
                        Text(text=item.titile,
                            color = MaterialTheme.colorScheme.onBackground)
                    }
                )
            }
        }
    }
}