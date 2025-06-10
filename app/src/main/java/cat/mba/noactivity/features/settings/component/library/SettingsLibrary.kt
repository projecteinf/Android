package cat.mba.noactivity.features.settings.component.library

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cat.mba.noactivity.R

@Composable
fun NotificationSwitch(labelId: kotlin.Int,notificationsEnabled: MutableState<Boolean>, modifier: Modifier = Modifier)
{
    Row {

        Text(
            text = stringResource(id = labelId),
            style = MaterialTheme.typography.labelSmall,
            textAlign = TextAlign.Center,
            modifier =  modifier.padding(vertical = 40.dp)
        )
        Switch(
            checked = notificationsEnabled.value,
            onCheckedChange = { notificationsEnabled.value = it }
        )
    }
}

@Composable
fun ParametritzacioNotificacio(modifier: Modifier = Modifier) {
    Row {
        Button(
            onClick = {},
            enabled = true,
            modifier = Modifier,
            content = {
                Text(
                    text = stringResource(id = R.string.settings_add_btn_interval),
                    style = MaterialTheme.typography.labelLarge )
            }
        )
    }
}

