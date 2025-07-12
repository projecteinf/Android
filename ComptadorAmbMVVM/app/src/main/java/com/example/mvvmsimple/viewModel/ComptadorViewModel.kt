package com.example.mvvmsimple.viewModel

import androidx.lifecycle.ViewModel
import com.example.mvvmsimple.model.ComptadorModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow

class ComptadorViewModel : ViewModel() {
    private var _comptador = MutableStateFlow<Int>(0)
    val comptador: StateFlow<Int> = _comptador

    fun incrementar() {
        _comptador.value += 1
    }
    fun guardar() {
        ComptadorModel.guardar()
    }
}