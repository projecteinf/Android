package cat.mba.noactivity.features.settings.component.library

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cat.mba.noactivity.R

@Composable
fun NotificationSwitch(labelId: Int,notificationsEnabled: MutableState<Boolean>, modifier: Modifier = Modifier)
{
    Row(
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Text(
            text = stringResource(id = labelId),
            style = MaterialTheme.typography.labelSmall,
            )
        Switch(
            checked = notificationsEnabled.value,
            onCheckedChange = { notificationsEnabled.value = it },
            modifier.padding(horizontal = 30.dp)
        )
    }
}

@Composable
fun ParametritzacioNotificacio(modifier: Modifier = Modifier) {
    Row (
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Text(
            text = stringResource(id = R.string.settings_km_inicial),
            style = MaterialTheme.typography.labelSmall )

    }
}

@Composable
fun AfegirLiniaConfiguracio(modifier: Modifier = Modifier)
{
    TextButton(
        onClick = {},
        enabled = true,
        modifier = Modifier.padding( horizontal = 2.dp),
        content = {
            Text(
                text = stringResource(id = R.string.settings_add_btn_interval),
                style = MaterialTheme.typography.labelSmall )
        }
    )
}

