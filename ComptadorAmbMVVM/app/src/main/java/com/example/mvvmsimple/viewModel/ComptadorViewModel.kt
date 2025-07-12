package com.example.mvvmsimple.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmsimple.model.ComptadorModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ComptadorViewModel(private val context: Context) : ViewModel() {
    val comptador : StateFlow<Int> = ComptadorModel.comptador

    init {
        viewModelScope.launch {
            ComptadorModel.carregar(context)
        }
    }
    fun incrementar() {
        ComptadorModel.incrementar()
        guardar()
    }
    fun guardar() {
        viewModelScope.launch {
            ComptadorModel.guardar(context)
        }
    }
}