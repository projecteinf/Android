package cat.mba.noactivity.features.settings.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cat.mba.noactivity.R
import cat.mba.noactivity.factory.SettingsViewModelFactory
import cat.mba.noactivity.features.settings.component.library.AfegirBtn
import cat.mba.noactivity.features.settings.component.library.NotificationSwitch
import cat.mba.noactivity.features.settings.component.library.ParametritzacioNotificacio
import cat.mba.noactivity.features.settings.component.viewModel.SettingsViewModel
import cat.mba.noactivity.repository.ConfiguracioRepository

@Composable
fun SettingsContent(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val repo = remember { ConfiguracioRepository(context) }
    val factory = remember { SettingsViewModelFactory(repo) }
    val viewModel: SettingsViewModel = viewModel(factory = factory)


    val notificationsEnabled = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        NotificationSwitch(R.string.settings_enable_notifications, notificationsEnabled)

        if (notificationsEnabled.value) {
            val state by viewModel.estatNotificacio.collectAsState()
            val dadesValides by viewModel.formulariValid.collectAsState()

            ParametritzacioNotificacio(state, viewModel)
            AfegirBtn(state, dadesValides) {
                viewModel.guardar()
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}