package com.example.appcalculadoraagua.domain.usecases
//2.2.2 Crear clase para calcular el total final de la venta.

class CalcularTotalFinalUseCase () {       //1.- se crea la clase para calcular el total final
       operator fun invoke(ventaTotal: Double, gastos: String): Double {  //2.- se llama al resultado de ventaTotal (usecase)y gastos como argumentos de la función
       val montoGastos = gastos.toDoubleOrNull() ?: 0.0
       val totalFinal = ventaTotal - montoGastos
           return totalFinal
    }
}

/*
2.- el operador invoke convierte la clase en una función para poder llamarla como si fuera una función
el objeto ventaTotal se pasa como argumento a la función para invocar los datos del resultado de la ventaTotal Y
el objeto gastos sera el que escribe el usuario para guardar los datos de los ajustes o gastos y se pasa como argumento a la función
y Double representa el tipo de datos de retorno es el total final
3.- Se crea la variable montoGastos para guardar los datos de los ajustes o gastos que escribe el usuario y se pasa como argumento a la función
4.- Se crea la variable totalFinal para guardar el total final de la venta menos los ajustes o gastos que escribe el usuario

//si se desea que este caso de uso tenga acceso al contrato, al repositorio de las ventas,  se pone entre paréntesis
el nombre de la variable que contendrá los datosd el contrato,
en este caso seria private val Repository: VentaRepository
 */