package cat.mba.noactivity.features.settings.component.library

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@Composable
fun LabelEnableNotifications(labelId: kotlin.Int) {
    Text(
        text = stringResource(id = labelId),
        style = MaterialTheme.typography.labelSmall
    )
}