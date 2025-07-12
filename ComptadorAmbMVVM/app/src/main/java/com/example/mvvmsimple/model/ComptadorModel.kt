package com.example.mvvmsimple.model

import android.content.Context
import android.icu.text.IDNA
import android.util.Log
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlin.collections.plusAssign

const val DATASTORENAME = "comptador"
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name=DATASTORENAME)

object DataToStore {
    val COMPTADOR = intPreferencesKey("comptador")
}


object ComptadorModel {
    private val _comptador = MutableStateFlow(0)
    val comptador: StateFlow<Int> = _comptador

    fun incrementar() {
        _comptador.value += 1
    }

    fun guardar() {
        Log.d("INFO", "guardar: "+comptador.value.toString())
    }

}