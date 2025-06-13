package cat.mba.noactivity.features.settings.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cat.mba.noactivity.R
import cat.mba.noactivity.features.settings.component.library.NotificationSwitch
import cat.mba.noactivity.features.settings.component.library.ParametritzacioNotificacio


@Composable
fun SettingsContent(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val notificationsEnabled = remember { mutableStateOf(false) }

        NotificationSwitch(R.string.settings_enable_notifications,notificationsEnabled)
        if (notificationsEnabled.value) {
            ParametritzacioNotificacio()
            Spacer(modifier = Modifier.height(8.dp))
        }


    }
}




