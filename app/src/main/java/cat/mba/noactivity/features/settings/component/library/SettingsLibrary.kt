package cat.mba.noactivity.features.settings.component.library

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.stringResource

@Composable
fun NotificationSwitch(labelId: kotlin.Int,notificationsEnabled: MutableState<Boolean>)
{
    Column {
        Text(
            text = stringResource(id = labelId),
            style = MaterialTheme.typography.labelSmall
        )
        Switch(
            checked = notificationsEnabled.value,
            onCheckedChange = { notificationsEnabled.value = it }
        )
    }
}