package cat.mba.noactivity.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cat.mba.noactivity.ui.component.MainContent
import cat.mba.noactivity.ui.component.BottomMenu

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        MainContent()
        BottomMenu()
    }
}
