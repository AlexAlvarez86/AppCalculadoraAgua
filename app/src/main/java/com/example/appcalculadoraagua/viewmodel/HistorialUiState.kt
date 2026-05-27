package com.example.appcalculadoraagua.viewmodel

import com.example.appcalculadoraagua.data.local.entities.VentaDiariaEntity

// Este objeto representa todos los estados posibles del Historial
    sealed class HistorialUiState {
        object Loading : HistorialUiState()
        data class Success(val lista: List<VentaDiariaEntity>) : HistorialUiState()
        data class Error(val mensaje: String) : HistorialUiState()
}
