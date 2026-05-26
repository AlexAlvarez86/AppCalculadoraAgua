package com.example.appcalculadoraagua.domain.usecases

//2.2.1 esta función permite calcular el total de la venta de todas las rutas
import com.example.appcalculadoraagua.domain.model.Ruta

class CalcularVentaTotalUseCase{
    // La función 'invoke' permite llamar a la clase como si fuera una función
    operator fun invoke(rutas: List<Ruta>): Double {
        return rutas.sumOf { it.calcularTotal()}
        }
}
/*it hace referencia a cada uno de los objetos de la lista de rutas y llama a la función calcularTotal()
si quisieramos sustituir it por código sería de la siguiente manera:
return rutas.sumOf { ruta -> ruta.calcularTotal() }

la función calcularTotal() se encarga de calcular el total de cada tarjeta (de cada ruta)
y guardar el dato para entregarlo al ViewModel cuando se llame a la función.
la función calcularTotal se localizada en domain_model_Ruta
*/