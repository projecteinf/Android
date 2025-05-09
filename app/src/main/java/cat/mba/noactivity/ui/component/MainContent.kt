package cat.mba.noactivity.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text

@Composable
fun MainContent(modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Text(text = "Main Window")
    }
}
