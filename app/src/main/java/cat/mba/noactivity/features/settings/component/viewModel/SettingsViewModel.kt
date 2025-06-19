package cat.mba.noactivity.features.settings.component.viewModel

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SettingsViewModel : ViewModel() {
    private val _estatNotificacio = MutableStateFlow(NotificacioConfiguracio())
    val estatNotificacio: StateFlow<NotificacioConfiguracio> = _estatNotificacio

    fun onLimitNotificationsChange(value: TextFieldValue) {
        if (value.text.toIntOrNull() != null) {
            _estatNotificacio.value = _estatNotificacio.value.copy(limitNotifications = value)
        }
    }

    fun onKmsInicialChange(value: TextFieldValue) {
        if (value.text.toFloatOrNull() != null) {
            _estatNotificacio.value = _estatNotificacio.value.copy(kmsInicial = value)
        }
    }

    fun onKmsAvisChange(value: TextFieldValue) {
        if (value.text.toFloatOrNull() != null) {
            _estatNotificacio.value = _estatNotificacio.value.copy(kmsAvis = value)
        }
    }
}