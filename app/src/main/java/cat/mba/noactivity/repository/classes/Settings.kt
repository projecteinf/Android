package cat.mba.noactivity.repository.classes

import androidx.compose.ui.text.input.TextFieldValue
import cat.mba.noactivity.features.settings.component.viewModel.NotificacioConfiguracio

data class NotificacioConfiguracioPersitencia(
    val limitNotifications: Int = 0,
    val kmsInicial: Float = 0f,
    val kmsAvis: Float = 0f
)

fun NotificacioConfiguracioPersitencia.toUiModel(): NotificacioConfiguracio {
    return NotificacioConfiguracio(
        limitNotifications = TextFieldValue(limitNotifications.toString()),
        kmsInicial = TextFieldValue(kmsInicial.toString()),
        kmsAvis = TextFieldValue(kmsAvis.toString())
    )
}

fun NotificacioConfiguracio.toPersistenceModel(): NotificacioConfiguracioPersitencia? {
    val limit = limitNotifications.text.toIntOrNull()
    val inicial = kmsInicial.text.toFloatOrNull()
    val avis = kmsAvis.text.toFloatOrNull()

    return if (limit != null && inicial != null && avis != null) {
        NotificacioConfiguracioPersitencia(limit, inicial, avis)
    } else null

}