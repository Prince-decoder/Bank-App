package com.bank.bankapp

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

// Create Navigation Items Class to Select UnSelect items
data class NavigationItems(
    val title: String,
    val selectedIcon: Painter,
    val unselectedIcon: Painter,
    val badgeCount: Int? = null
)