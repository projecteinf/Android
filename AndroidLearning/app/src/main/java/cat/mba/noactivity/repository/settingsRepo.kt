package cat.mba.noactivity.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import cat.mba.noactivity.repository.classes.NotificacioConfiguracioPersitencia
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

const val DATASTORENAME = "settings"
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATASTORENAME)

object ConfiguracioKeys {
    val NOTIFACTIV = booleanPreferencesKey("NOTIFACTIVES")
    val LIMIT = intPreferencesKey("limit_notifications")
    val INICIAL = floatPreferencesKey("kms_inicial")
    val AVIS = floatPreferencesKey("kms_avis")
}

class ConfiguracioRepository(private val context: Context) {
    val configuracio: Flow<NotificacioConfiguracioPersitencia> = context.dataStore.data
        .map { prefs ->
            NotificacioConfiguracioPersitencia(
                limitNotifications = prefs[ConfiguracioKeys.LIMIT] ?: 0,
                kmsInicial = prefs[ConfiguracioKeys.INICIAL] ?: 0f,
                kmsAvis = prefs[ConfiguracioKeys.AVIS] ?: 0f
            )
        }

    suspend fun guardarConfiguracio(config: NotificacioConfiguracioPersitencia) {
        context.dataStore.edit { prefs ->
            prefs[ConfiguracioKeys.LIMIT] = config.limitNotifications
            prefs[ConfiguracioKeys.INICIAL] = config.kmsInicial
            prefs[ConfiguracioKeys.AVIS] = config.kmsAvis
        }
    }
}