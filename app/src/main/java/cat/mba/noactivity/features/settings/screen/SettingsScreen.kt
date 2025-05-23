package cat.mba.noactivity.features.settings.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import cat.mba.noactivity.features.main.component.BottomMenu
import cat.mba.noactivity.features.settings.component.SettingsContent


@Composable
fun SettingsScreen()
{
    var selectedItem by remember { mutableStateOf("settings") }

    Scaffold(
        bottomBar = { BottomMenu(
            selectedItem = selectedItem,
            onItemSelected = { selectedItem = it }
        ) }
    )  {
            innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            SettingsContent()
        }
    }
}
