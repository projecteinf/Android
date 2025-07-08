package cat.mba.noactivity.features.settings.component.library

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import cat.mba.noactivity.R
import cat.mba.noactivity.features.settings.component.viewModel.NotificacioConfiguracio
import cat.mba.noactivity.features.settings.component.viewModel.SettingsViewModel

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
    state: NotificacioConfiguracio,
    viewModel: SettingsViewModel,
    modifier: Modifier = Modifier)
{
    KmsInicials(state.kmsInicial, viewModel::onKmsInicialChange)
    KmsAvis(state.kmsAvis,viewModel::onKmsAvisChange)
    LimitNotificacions(state.limitNotifications,viewModel::onLimitNotificationsChange)
}

@Composable
fun LimitNotificacions(value: TextFieldValue, onValueChange: (TextFieldValue) -> Unit, modifier: Modifier = Modifier) {
    Row (
        verticalAlignment = Alignment.CenterVertically
    )
    {
        TextField(
            value = value,
            onValueChange = onValueChange,
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
fun KmsAvis(value: TextFieldValue, onValueChange: (TextFieldValue) -> Unit, modifier: Modifier = Modifier) {
    Row (
        verticalAlignment = Alignment.CenterVertically
    )
    {

        TextField(
            value = value,
            onValueChange = onValueChange,
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
fun KmsInicials(value: TextFieldValue, onValueChange: (TextFieldValue) -> Unit, modifier: Modifier = Modifier) {
    Row (
        verticalAlignment = Alignment.CenterVertically
    )
    {
        TextField(
            value = value,
            onValueChange = onValueChange,
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
fun AfegirBtn(state: NotificacioConfiguracio,
              dadesValides: Boolean,
              modifier: Modifier = Modifier,
              onClick: () -> Unit) {
    Row (

    )
    {
        if (dadesValides) {
            SmallFloatingActionButton(
                onClick = {
                    Log.d("Info",state.kmsAvis.text+","+
                            state.kmsInicial.text+","+state.limitNotifications.text)
                    onClick()
                },
                modifier = Modifier,
                content = {
                    Icon(Icons.Filled.Add,
                        contentDescription = "Add configuration button" //,
                        //tint = if (dadesValides) Color.Unspecified else Color.Gray
                   )
                }
            )
        }
    }
}

