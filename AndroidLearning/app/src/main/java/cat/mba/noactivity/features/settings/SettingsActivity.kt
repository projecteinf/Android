package cat.mba.noactivity.features.settings

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import cat.mba.noactivity.core.theme.NoActivityTheme
import cat.mba.noactivity.features.common.screen.MainScreen


class SettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NoActivityTheme {
                MainScreen("Settings");
            }
        }
    }
}


