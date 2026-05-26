package com.example.appcalculadoraagua.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "historial_ventas")   //genera una tabla en la base de datos
data class VentaDiariaEntity(   //se crea la clase para guardar el resultado
    @PrimaryKey(autoGenerate = true) val id: Int = 0,   //se crea la variable id para guardar el resultado. primary key indica que es la clave primaria de la tabla y autoGenerate indica que se generará automáticamente
    val fecha: Long, // Guardamos el tiempo en milisegundos
    val totalGarrafones: Int,       //se crea la variable totalGarrafones para guardar el resultado que viene de la función del caso de uso CalcularVentaTotalUseCase
    val totalVenta: Double,
    val pagoRepartidor: Double,
    val gastos: Double)
