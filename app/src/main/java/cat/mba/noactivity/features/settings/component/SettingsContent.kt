package cat.mba.noactivity.features.settings.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cat.mba.noactivity.features.settings.component.library.LabelEnableNotifications

@Composable
fun SettingsContent(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LabelEnableNotifications()

        Spacer(modifier = Modifier.height(8.dp))

    }
}




