package com.example.appcalculadoraagua

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.appcalculadoraagua.ui.theme.AppCalculadoraAguaTheme
// Importamos la pantalla y el viewmodel
import com.example.appcalculadoraagua.presentation.screens.CalculadoraScreen
import com.example.appcalculadoraagua.viewmodel.CalculadoraViewModel

import com.example.appcalculadoraagua.data.local.DatabaseProvider  //8.11a importamos la base de datos Provisional
import com.example.appcalculadoraagua.data.repository.VentaRepositoryImp  //8.11b importamos el repositorio real

//import androidx.activity.compose.setContentimport androidx.activity.ComponentActivity

import androidx.activity.compose.setContent


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 8.11c. Inicializamos DB y Repo
        val db = DatabaseProvider.getDatabase(this)
        val repository = VentaRepositoryImp(db.ventaDao())
        // 8.11d. Pasamos el repo al ViewModel
        val viewModel = CalculadoraViewModel(repository)

        //enableEdgeToEdge()  // Habilita que la app use toda la pantalla (opcional)
        setContent {
            AppCalculadoraAguaTheme {  // Aplicamos el tema de tu aplicación para que los colores se vean bien
                // Surface es el "lienzo" base sobre el que pintamos
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CalculadoraScreen(viewModel)
                }
            }
        }
    }

}
