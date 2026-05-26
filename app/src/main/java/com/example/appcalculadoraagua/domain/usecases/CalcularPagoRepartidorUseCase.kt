package com.example.appcalculadoraagua.domain.usecases

import com.example.appcalculadoraagua.domain.model.Ruta
//2.2.3 Crear clase para calcular el pago del repartidor. el objeto rutas se pasa como argumento a la función para guardar los datos de
class CalcularPagoRepartidorUseCase {       //se crea la clase para calcular el pago del repartidor
    operator fun invoke(rutas: List<Ruta>): Double { //el operador invoke convierte la clase en una función
        //el objeto rutas se pasa como argumento a la función para extraer los
        ////rutas extrae todos los datos de la lista de rutas, tales como el nombre de la ruta, los garrafones vendidos, el precio unitario y los garrafones cargados.
        //Double indica el tipo de datos de retorno, en este caso será el total en pesos


        // Calcular el total de garrafones vendidos
        val totalGarrafones = rutas.sumOf { it.garrafonesVendidos.toDoubleOrNull() ?: 0.0 } // it hace referencia a cada uno de los objetos de la lista de rutas
        return (totalGarrafones * 2.0) + 250.0

        //se crea la variable totalGarrafones para guardar el total de garrafones vendidos
        //it hace referencia a cada uno de los objetos de la lista de rutas y realiza la suma de los garrafones vendidos. El resultado se almacena en totalGarrafones.
        //si quisiera sustituir it por código se haría de la siguiente manera:
        //val totalGarrafones = rutas.sumOf { ruta -> ruta.garrafonesVendidos.toDoubleOrNull() ?: 0.0 }

    }
}