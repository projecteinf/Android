package cat.mba.noactivity.features.settings.component

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cat.mba.noactivity.R
import cat.mba.noactivity.features.settings.component.library.LabelEnableNotifications
import cat.mba.noactivity.features.settings.component.library.SwitchNotifications


@Composable
fun SettingsContent(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        var notificationsEnabled by remember { mutableStateOf(false) }

        LabelEnableNotifications(R.string.settings_enable_notifications)
        SwitchNotifications(
            isEnabled = notificationsEnabled,
            onToggle =  {
                    notificationsEnabled = it
                    Log.d("Notifications enabled", notificationsEnabled.toString())
            }
        )

       Spacer(modifier = Modifier.height(8.dp))

    }
}




