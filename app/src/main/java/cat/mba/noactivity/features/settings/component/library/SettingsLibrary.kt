package cat.mba.noactivity.features.settings.component.library

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cat.mba.noactivity.R
import cat.mba.noactivity.features.settings.component.dataClass.NotificacioConfiguracio

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
fun ParametritzacioNotificacio(
        configuracio:MutableState<NotificacioConfiguracio>,
        modifier: Modifier = Modifier)
{
    KmsInicials(configuracio)
    KmsAvis(configuracio)
    LimitNotificacions(configuracio)
}

@Composable
fun LimitNotificacions(configuracio: MutableState<NotificacioConfiguracio>, modifier: Modifier = Modifier) {
    Row (
        verticalAlignment = Alignment.CenterVertically
    )
    {
        TextField(
            value = configuracio.value.limitNotifications,
            onValueChange = { input ->
                if (input.text.isNotEmpty() && input.text.toIntOrNull()!= null) {
                    configuracio.value = configuracio.value.copy(limitNotifications = input)
                }
            },
            label = {
                Text(
                    text = stringResource(id = R.string.settings_limit_notificacions)
                )
            },
            modifier = Modifier
        )
    }
}

@Composable
fun KmsAvis(configuracio: MutableState<NotificacioConfiguracio>, modifier: Modifier = Modifier) {
    Row (
        verticalAlignment = Alignment.CenterVertically
    )
    {

        TextField(
            value = configuracio.value.kmsAvis,
            onValueChange = { input ->
                if (input.text.isNotEmpty() && input.text.toFloatOrNull()!= null) {
                    configuracio.value = configuracio.value.copy(kmsAvis = input)
                }
            },
            label = {
                Text(
                    text = stringResource(id = R.string.settings_km_avis)
                )
            },
            modifier = Modifier
        )
    }
}

@Composable
fun KmsInicials(configuracio: MutableState<NotificacioConfiguracio>, modifier: Modifier = Modifier) {
    Row (
        verticalAlignment = Alignment.CenterVertically
    )
    {
        TextField(
            value = configuracio.value.kmsInicial,
            onValueChange = { input ->
                if (input.text.isNotEmpty() && input.text.toFloatOrNull() != null) {
                    configuracio.value = configuracio.value.copy(kmsInicial = input)
                }
            },
            label = {
                Text(
                    text = stringResource(id = R.string.settings_km_start)
                )
            },
            modifier = Modifier
        )
    }
}

@Composable
fun AfegirBtn(configuracio: MutableState<NotificacioConfiguracio>, modifier: Modifier = Modifier) {
    Row (

    )
    {
        TextButton(
            onClick = {
                Log.d("Info",configuracio.value.kmsAvis.text+","+
                        configuracio.value.kmsInicial.text+","+configuracio.value.limitNotifications.text)
            },
            enabled = true,
            modifier = Modifier,
            content = {
                Text(
                    text = stringResource(id = R.string.settings_add_btn_interval),
                    style = MaterialTheme.typography.labelSmall )
            }
        )
    }
}

