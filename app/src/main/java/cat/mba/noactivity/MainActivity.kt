package cat.mba.noactivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cat.mba.noactivity.ui.theme.NoActivityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NoActivityTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
    ){
        Main();
        Bottom();
    }

}

@Composable
fun Main(modifier: Modifier = Modifier) {
    Row(

    ) {
        Text(
            text = "Main Window"
        )
    }
}

@Composable
fun Bottom(modifier: Modifier = Modifier) {
    Row(

    ){
        Text(
            text = "Menu Bottom"
        )
    }
}


@Preview(showBackground = true)
@Composable
fun MainPreview() {
    NoActivityTheme {
        MainScreen()
    }
}