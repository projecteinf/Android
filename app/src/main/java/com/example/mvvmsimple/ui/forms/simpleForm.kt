package com.example.mvvmsimple.ui.forms

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mvvmsimple.viewModel.ComptadorViewModel

@Composable
fun simpleForm(viewModel: ComptadorViewModel = viewModel()) {
    val comptador by viewModel.comptador.collectAsState()

    Column(Modifier.Companion.padding(16.dp)) {
        TextField(
            value = comptador.toString(),
            onValueChange = {}
        )

        IncrementarBtn() {
            viewModel.incrementar()
        }
    }
}

@Composable
fun IncrementarBtn(onClick: () -> Unit) {
    FloatingActionButton(onClick = onClick) {
        Icon(Icons.Filled.Add, contentDescription = "Incrementar valor")
    }
}