package com.example.appcalculadoraagua.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.appcalculadoraagua.data.local.entities.VentaDiariaEntity

@Dao
interface VentaDao {    //se crea la interfaz que implementa la base de datos para guardar el resultado de la venta

    @Insert
    suspend fun insertarVentaDiaria(venta: VentaDiariaEntity)  //el objeto venta extrae los datos de la venta que se guardará en la base de datos VentaDiariaEntity ubicada en la carpeta entities

    @Query("SELECT * FROM historial_ventas ORDER BY fecha DESC")
    suspend fun obtenerTodoElHistorial(): List<VentaDiariaEntity>

    // Esta consulta es la que te servirá para el reporte semanal
    @Query("SELECT * FROM historial_ventas WHERE fecha >= :inicio AND fecha <= :fin")
    suspend fun obtenerVentasPorRango(inicio: Long, fin: Long): List<VentaDiariaEntity>

    //10.7 Agregar función para BORRAR EL HISTORIAL (darle a Room la instrucción SQL para vaciar la tabla.)
    @Query("DELETE FROM historial_ventas") // indica que se borrará la tabla de la base de datos
    suspend fun borrarTodoElHistorial()   //suspend indica que es una función de suspensión, es decir, la función se ejecutará en un hilo separado, en segundo plano
}