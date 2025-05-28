package cat.mba.noactivity.features.main.screen

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
import cat.mba.noactivity.features.common.BaseScreen
import cat.mba.noactivity.features.common.menu.BottomMenu
import cat.mba.noactivity.features.main.component.MainContent

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var selectedItem by remember { mutableStateOf("home") }
    BaseScreen(
        selectedItem = selectedItem,
        onItemSelected = { selectedItem = it })
    {
       MainContent()
    }
}
