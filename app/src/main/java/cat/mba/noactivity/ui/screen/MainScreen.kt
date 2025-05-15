package cat.mba.noactivity.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cat.mba.noactivity.ui.component.MainContent
import cat.mba.noactivity.ui.component.BottomMenu

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    Scaffold(
        bottomBar = { BottomMenu() }
    )  {
            innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            MainContent()
        }
    }
}
