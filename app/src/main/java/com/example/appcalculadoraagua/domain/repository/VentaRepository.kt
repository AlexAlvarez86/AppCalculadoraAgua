package com.example.appcalculadoraagua.domain.repository
import com.example.appcalculadoraagua.domain.model.Ruta
import com.example.appcalculadoraagua.data.local.entities.VentaDiariaEntity

interface VentaRepository {
    suspend fun guardarVentaDelDia(rutas: List<Ruta>, totalFinal: Double): Boolean
   // Cambiamos el tipo de retorno aquí también:
    suspend fun obtenerHistorial(): List<VentaDiariaEntity>
    //10.7.2 Agregar función para Borrar el historial
    suspend fun borrarHistorial()
}









