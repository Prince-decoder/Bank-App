package com.bank.bankapp


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.rounded.AccountBalance
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.ManageAccounts
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarTab(val title: String, val icon: ImageVector, val color: Color) {
    data object Profile : BottomBarTab(
        title = "Profile",
        icon = Icons.Rounded.Home,
        color = Color(0xFFFFA574)
    )
    data object Home : BottomBarTab(
        title = "Home",
        icon = Icons.Rounded.ManageAccounts,
        color = Color(0xFFFA6FFF)
    )
    data object Settings : BottomBarTab(
        title = "Settings",
        icon = Icons.Rounded.Settings,
        color = Color(0xFFADFF64)
    )

    data object Account : BottomBarTab(
        title = "Account",
        icon = Icons.Rounded.AccountBalance,
        color = Color(0xFFADFF64)
    )
}

val tabs = listOf(
    BottomBarTab.Profile,
    BottomBarTab.Account,
    BottomBarTab.Home,
    BottomBarTab.Settings,
)