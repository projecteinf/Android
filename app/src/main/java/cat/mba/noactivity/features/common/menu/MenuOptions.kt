package cat.mba.noactivity.features.common.menu

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

data class MenuOption(
    val route: String,
    val label: String,
    val icon: ImageVector
)

val bottomMenuMainItems = listOf(
    MenuOption("home", "Home", Icons.Default.Home),
    MenuOption("settings", "Settings", Icons.Default.Settings)
)
