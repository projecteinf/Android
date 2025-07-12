package com.example.mvvmsimple.viewModel

import androidx.lifecycle.ViewModel
import com.example.mvvmsimple.model.ComptadorModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow

class ComptadorViewModel : ViewModel() {
    val comptador : StateFlow<Int> = ComptadorModel.comptador

    fun incrementar() {
        ComptadorModel.incrementar()
        guardar()
    }
    fun guardar() {
        ComptadorModel.guardar()
    }
}