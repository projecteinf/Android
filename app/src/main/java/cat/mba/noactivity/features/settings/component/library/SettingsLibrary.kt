package cat.mba.noactivity.features.settings.component.library

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.stringResource

@Composable
fun LabelEnableNotifications(labelId: kotlin.Int) {
    Text(
        text = stringResource(id = labelId),
        style = MaterialTheme.typography.labelSmall
    )
}

@Composable
fun SwitchNotifications(isEnabled: Boolean, onToggle: (Boolean) -> Unit) {

    Column {
        Switch(
            checked = isEnabled,
            onCheckedChange = { checked -> onToggle(checked) }
        )
    }
}


@Composable
fun NotificationSwitch(notificationsEnabled: MutableState<Boolean>)
{
    Column {
        Switch(
            checked = notificationsEnabled.value,
            onCheckedChange = { notificationsEnabled.value = it }
        )
    }
}