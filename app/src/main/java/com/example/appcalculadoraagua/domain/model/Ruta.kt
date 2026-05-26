package com.example.appcalculadoraagua.domain.model
/*
2.1.- domain_model_Ruta
se genera para guardar los datos de cada tarjeta de ruta que se muestra en la
interfaz de usuario.
*/
data class Ruta(
    // Representa cada una de las 6 tarjetas de ruta
    val id: Int,
    val nombre: String,
    val garrafonesVendidos: String = "",
    val precioUnitario: String = "24",
    val garrafonesCargados: String = ""


){
    /* Regla de negocio:
    // Función para Calcular el total de cada tarjeta (de cada ruta) y guardar el dato para
    entregarlo al ViewModel cuando se llame a la función*/
    fun calcularTotal(): Double {
        val cantidad = garrafonesVendidos.toDoubleOrNull() ?: 0.0
        val precio = precioUnitario.toDoubleOrNull() ?: 0.0
        return cantidad * precio
    }
}
