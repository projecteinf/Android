package cat.mba.noactivity.features.common

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cat.mba.noactivity.features.common.menu.BottomMenu
import cat.mba.noactivity.features.common.menu.bottomMenuMainItems
import cat.mba.noactivity.features.main.component.MainContent
import cat.mba.noactivity.features.settings.component.SettingsContent


@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomMenu(navController = navController, bottomMenuMainItems)
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            NavHost(
                navController = navController,
                startDestination = "home"
            ) {
                composable("home") { MainContent() }
                composable("settings") { SettingsContent() }
            }
        }
    }
}

