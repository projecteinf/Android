package cat.mba.noactivity.features.settings.component.viewModel

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.mba.noactivity.repository.ConfiguracioRepository
import cat.mba.noactivity.repository.classes.toPersistenceModel
import cat.mba.noactivity.repository.classes.toUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SettingsViewModel(private val repo: ConfiguracioRepository) : ViewModel() {
    private val _estatNotificacio = MutableStateFlow(NotificacioConfiguracio())
    val estatNotificacio: StateFlow<NotificacioConfiguracio> = _estatNotificacio.asStateFlow()

    private val _formulariValid = MutableStateFlow(false)
    val formulariValid: StateFlow<Boolean> = _formulariValid

    private val _guardatCorrectament = MutableStateFlow<Boolean?>(null)
    val guardatCorrectament: StateFlow<Boolean?> = _guardatCorrectament

    init {
        viewModelScope.launch {
            repo.configuracio.collect { configuracioPersistida ->
                _estatNotificacio.value = configuracioPersistida.toUiModel()
            }
        }
    }

    fun guardar() {
        val model = estatNotificacio.value.toPersistenceModel()
        if (model != null) {
            viewModelScope.launch {
                repo.guardarConfiguracio(model)
                _guardatCorrectament.value = true
            }
        } else {
            _guardatCorrectament.value = false
        }
    }

    fun resetGuardat() {
        _guardatCorrectament.value = null
    }

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


