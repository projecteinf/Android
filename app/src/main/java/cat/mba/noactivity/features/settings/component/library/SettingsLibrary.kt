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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import cat.mba.noactivity.R
import cat.mba.noactivity.model.Configuracio

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
        limitNotifications:MutableState<TextFieldValue>,
        kmsInicial:MutableState<TextFieldValue>,
        kmsAvis:MutableState<TextFieldValue>,
        modifier: Modifier = Modifier)
{
    val configuracio = Configuracio(0f,0f,0);
    KmsInicials(kmsInicial)
    KmsAvis(kmsAvis)
    LimitNotificacions(limitNotifications)
}

@Composable
fun LimitNotificacions(limitNotifications: MutableState<TextFieldValue>, modifier: Modifier = Modifier) {
    Row (
        verticalAlignment = Alignment.CenterVertically
    )
    {

        TextField(
            value = limitNotifications.value,
            onValueChange = { limitNotifications.value = it },
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
fun KmsAvis(kmsAvis: MutableState<TextFieldValue>, modifier: Modifier = Modifier) {
    Row (
        verticalAlignment = Alignment.CenterVertically
    )
    {

        TextField(
            value = kmsAvis.value,
            onValueChange = { kmsAvis.value = it
                                Log.d("DEBUG","Quilometres avís: "+kmsAvis.value)
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
fun KmsInicials(kmsInicial: MutableState<TextFieldValue>, modifier: Modifier = Modifier) {
    Row (
        verticalAlignment = Alignment.CenterVertically
    )
    {
        TextField(
            value = kmsInicial.value,
            onValueChange = { kmsInicial.value = it },
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
fun AfegirBtn(modifier: Modifier = Modifier) {
    Row (

    )
    {
        TextButton(
            onClick = {},
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

