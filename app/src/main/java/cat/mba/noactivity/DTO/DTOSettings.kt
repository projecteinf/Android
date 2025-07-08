package cat.mba.noactivity.DTO

import androidx.compose.ui.text.input.TextFieldValue

class DTOSettings {
    fun cat.mba.noactivity.repository.classes.NotificacioConfiguracioPersitencia.toUiModel(): cat.mba.noactivity.features.settings.component.viewModel.NotificacioConfiguracio {
        return cat.mba.noactivity.features.settings.component.viewModel.NotificacioConfiguracio(
            limitNotifications = TextFieldValue(limitNotifications.toString()),
            kmsInicial = TextFieldValue(kmsInicial.toString()),
            kmsAvis = TextFieldValue(kmsAvis.toString())
        )
    }

    fun cat.mba.noactivity.features.settings.component.viewModel.NotificacioConfiguracio.toPersistenceModel(): cat.mba.noactivity.repository.classes.NotificacioConfiguracioPersitencia? {
        val limit = limitNotifications.text.toIntOrNull()
        val inicial = kmsInicial.text.toFloatOrNull()
        val avis = kmsAvis.text.toFloatOrNull()

        return if (limit != null && inicial != null && avis != null) {
            cat.mba.noactivity.repository.classes.NotificacioConfiguracioPersitencia(limit, inicial, avis)
        } else null
    }

}