package cat.mba.noactivity.features.settings.component.library

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import cat.mba.noactivity.R

@Composable
fun LabelEnableNotifications() {
    Text(
        text = stringResource(id = R.string.settings_enable_notifications),
        style = MaterialTheme.typography.labelSmall
    )
}