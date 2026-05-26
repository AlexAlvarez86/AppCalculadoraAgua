package com.example.appcalculadoraagua.data.repository

import com.example.appcalculadoraagua.domain.model.Ruta
import com.example.appcalculadoraagua.domain.repository.VentaRepository
import com.example.appcalculadoraagua.data.local.dao.VentaDao
import com.example.appcalculadoraagua.data.local.entities.VentaDiariaEntity

class VentaRepositoryImp ( // Quitamos 'abstract'
    private val ventaDao: VentaDao
): VentaRepository {

    override suspend fun guardarVentaDelDia(rutas: List<Ruta>, totalFinal: Double): Boolean {
        return try {
            val totalGarrafones = rutas.sumOf { it.garrafonesVendidos.toIntOrNull() ?: 0 }

            val registroDiario = VentaDiariaEntity(
                fecha = System.currentTimeMillis(),
                totalGarrafones = totalGarrafones,
                totalVenta = totalFinal,
                pagoRepartidor = (totalGarrafones * 2.0) + 250.0,
                gastos = 0.0 // Aquí puedes pasar los gastos reales después
            )

            ventaDao.insertarVentaDiaria(registroDiario)
            true
        } catch (e: Exception) {
            false
        }
    }
    /*
    override suspend fun obtenerHistorial(): List<Pair<String, Double>> {  // Aquí cambiamos el tipo de retorno. List<Pair devuelve una lista de pares de valores (En este caso, fecha y total))
        val entidades = ventaDao.obtenerTodoElHistorial()
        // Convertimos las entidades a un formato simple para la UI por ahora
        return entidades.map {
            val fechaLegible = java.text.SimpleDateFormat("dd/MM/yyyy").format(it.fecha)
            fechaLegible to it.totalVenta
        }
    }
    */

    // 10.1  IMPLEMENTACIÓN REAL DEL HISTORIAL CON LAS 4 COLUMNAS SOLICITADAS
    //fecha totalGarrafones = totalGarrafones, pagoRepartidor, totalFinal,
    //
    override suspend fun obtenerHistorial(): List<VentaDiariaEntity> {
        // Simplemente pedimos todo a la base de datos y lo entregamos
        return ventaDao.obtenerTodoElHistorial()
    }

        //10.7.3 Agregar función para Borrar el historial
    override suspend fun borrarHistorial() {  //override sirve para sobreescribir una función de la clase padre que en este caso es VentaRepository del paquete domain.repository
        ventaDao.borrarTodoElHistorial() //conectamos con la función del dao para borrar el historial
    }
}

