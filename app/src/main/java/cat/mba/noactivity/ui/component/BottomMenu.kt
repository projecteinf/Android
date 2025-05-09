package cat.mba.noactivity.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text

@Composable
fun BottomMenu(modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Text(text = "Menu Bottom")
    }
}
