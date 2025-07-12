package com.example.mvvmsimple.model

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map

object ComptadorModel {
    const val DATASTORENAME = "comptador"
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name=DATASTORENAME)
    private val COMPTADOR_KEY = intPreferencesKey("comptador")

    private val _comptador = MutableStateFlow(0)
    val comptador: StateFlow<Int> = _comptador

    suspend fun carregar(context: Context) {
        context.dataStore.data
            .map { it[COMPTADOR_KEY] ?: 0 }
            .collect { _comptador.value = it }
    }
    suspend fun guardar(context: Context) {
        context.dataStore.edit {
            data -> data[COMPTADOR_KEY] = _comptador.value
        }
    }

    fun incrementar() {
        _comptador.value += 1
    }

}