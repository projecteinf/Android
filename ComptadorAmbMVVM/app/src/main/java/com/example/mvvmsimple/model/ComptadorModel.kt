package com.example.mvvmsimple.model

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.intPreferencesKey

const val DATASTORENAME = "comptador"
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name=DATASTORENAME)

object DataToStore {
    val COMPTADOR = intPreferencesKey("comptador")
}
class ComptadorModel {
    companion object {
        fun guardar() {
            
        }
    }

}