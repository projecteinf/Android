package cat.mba.noactivity.features.settings.component.viewModel

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SettingsViewModel : ViewModel() {
    private val _estatNotificacio = MutableStateFlow(NotificacioConfiguracio())
    val estatNotificacio: StateFlow<NotificacioConfiguracio> = _estatNotificacio

    private val _formulariValid = MutableStateFlow(false) // El formulari inicialment no és vàlid
    val formulariValid: StateFlow<Boolean> = _formulariValid

    fun onLimitNotificationsChange(value: TextFieldValue) {
        _estatNotificacio.value = _estatNotificacio.value.copy(limitNotifications = value)
        dadesValides()
    }

    fun onKmsInicialChange(value: TextFieldValue) {
        _estatNotificacio.value = _estatNotificacio.value.copy(kmsInicial = value)
        dadesValides()
    }

    fun onKmsAvisChange(value: TextFieldValue) {
        _estatNotificacio.value = _estatNotificacio.value.copy(kmsAvis = value)
        dadesValides()
    }

    private fun dadesValides() {
        val estat = _estatNotificacio.value
        val limitOk = estat.limitNotifications.text.toIntOrNull() != null
        val kmsAvisOK = estat.kmsAvis.text.toFloatOrNull() != null
        val kmsInicialOk = estat.kmsInicial.text.toFloatOrNull() != null

        _formulariValid.value = limitOk && kmsAvisOK && kmsInicialOk
    }
}