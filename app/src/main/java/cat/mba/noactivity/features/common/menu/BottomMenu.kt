package cat.mba.noactivity.features.common.menu

import android.content.Intent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import cat.mba.noactivity.MainActivity
import cat.mba.noactivity.features.settings.SettingsActivity

@Composable
fun BottomMenu(selectedItem: String,
               onItemSelected: (String) -> Unit,
               modifier: Modifier = Modifier.Companion
) {
    NavigationBar(
        modifier = Modifier.Companion
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        val context = LocalContext.current

        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = selectedItem == "home",
            onClick = {
                onItemSelected("home")
                context.startActivity(Intent(context, MainActivity::class.java))
            }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Settings, contentDescription = "Settings") },
            label = { Text("Settings") },
            selected = selectedItem == "settings",
            onClick = {
                onItemSelected("settings")
                context.startActivity(Intent(context, SettingsActivity::class.java))
            }
        )
    }
}