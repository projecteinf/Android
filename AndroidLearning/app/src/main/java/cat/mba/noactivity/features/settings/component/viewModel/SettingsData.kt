package cat.mba.noactivity.features.settings.component.viewModel

import androidx.compose.ui.text.input.TextFieldValue

/*
Beneficis d'utilitzar el data class
    L'estat és més fàcil de passar a components.
    El codi és més net i coherent.
    Fer canvis o afegir nous camps és molt més fàcil.
*/

data class NotificacioConfiguracio(
    val limitNotifications: TextFieldValue = TextFieldValue(),
    val kmsInicial: TextFieldValue = TextFieldValue(),
    val kmsAvis: TextFieldValue = TextFieldValue()
)
