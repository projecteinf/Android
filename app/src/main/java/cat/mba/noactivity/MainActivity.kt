package cat.mba.noactivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import cat.mba.noactivity.main.screen.MainScreen
import cat.mba.noactivity.main.ui.theme.NoActivityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NoActivityTheme {
                    MainScreen()
            }
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}
*/