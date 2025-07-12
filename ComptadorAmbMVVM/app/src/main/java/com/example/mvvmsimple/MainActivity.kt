package com.example.mvvmsimple

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.mvvmsimple.ui.forms.simpleForm
import com.example.mvvmsimple.ui.theme.MVVMSimpleTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MVVMSimpleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {

                    simpleForm(context = applicationContext)
                }
            }
        }
    }
}

