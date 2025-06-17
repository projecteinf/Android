package cat.mba.noactivity.features.settings.component.dataClass

import androidx.compose.ui.text.input.TextFieldValue


data class NotificacioConfiguracio(
    val limitNotifications: TextFieldValue = TextFieldValue(),
    val kmsInicial: TextFieldValue = TextFieldValue(),
    val kmsAvis: TextFieldValue = TextFieldValue()
)
