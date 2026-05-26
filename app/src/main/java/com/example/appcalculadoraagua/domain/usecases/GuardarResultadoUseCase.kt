package com.example.appcalculadoraagua.domain.usecases
import com.example.appcalculadoraagua.domain.repository.VentaRepository
import com.example.appcalculadoraagua.domain.model.Ruta

class GuardarResultadoUseCase(          // se crea la clase para guardar el resultado

    private val repository: VentaRepository ){  // se crea la variable repository para solicitar "el contrato" VentaRepository
    // crear la función para guardar el resultado en la que el nombre de la función es invoke y llama a la clase como si fuera una función
    suspend operator fun invoke(rutas: List<Ruta>, total: Double): Boolean {
        // Aquí podrías poner lógica extra, por ejemplo:
        // "Solo guardar si el total es mayor a cero"
        if (total <= 0) return false

        return repository.guardarVentaDelDia(rutas, total)
    }
}