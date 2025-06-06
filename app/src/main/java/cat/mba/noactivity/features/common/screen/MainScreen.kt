package cat.mba.noactivity.features.common.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import cat.mba.noactivity.features.common.menu.BottomMenu
import cat.mba.noactivity.features.common.menu.bottomMenuMainItems
import cat.mba.noactivity.features.common.screen.library.getTopBarTitle
import cat.mba.noactivity.features.main.component.MainContent
import cat.mba.noactivity.features.settings.component.SettingsContent


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(title:String) {
    val navController = rememberNavController()

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    // Determinem el títol segons la ruta
    val title = getTopBarTitle(currentRoute)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title.toString()) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        bottomBar = {
            BottomMenu(navController = navController, bottomMenuMainItems)
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier.Companion
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