package cat.mba.noactivity.features.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import cat.mba.noactivity.core.theme.NoActivityTheme
import cat.mba.noactivity.features.common.screen.MainScreen
import java.util.prefs.Preferences

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NoActivityTheme {
                MainScreen(title = "Home")
            }
        }
    }
}