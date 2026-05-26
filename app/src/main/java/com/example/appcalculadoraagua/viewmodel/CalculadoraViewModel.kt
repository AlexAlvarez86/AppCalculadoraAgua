package com.example.appcalculadoraagua.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcalculadoraagua.domain.model.Ruta
import com.example.appcalculadoraagua.domain.usecases.*
import com.example.appcalculadoraagua.domain.repository.VentaRepository
import kotlinx.coroutines.launch
import com.example.appcalculadoraagua.data.local.entities.VentaDiariaEntity
import com.example.appcalculadoraagua.presentation.components.RutaCard


class CalculadoraViewModel(
    private val repository: VentaRepository //8.10a Vincular los datos del repositorio (llamamos a la interfaz))
)  : ViewModel() {  //Indicamos que esta clase hereda de ViewModel

    //************************ --- CALCULOS INICIALES ---***************************************
    // 1. Estado de las 6 rutas (Lista de objetos Ruta)
    ////Inicializamos la lista con 6 objetos Ruta mediante una expresión lambda que contiene un bucle for
    var rutas by mutableStateOf(List(6) { i -> Ruta(id = i, nombre = "Ruta ${i + 1}") })

    // 2. Estado de los gastos (ajustes)
    var gastos by mutableStateOf("")  // Estado para los ajustes de gastos, inicialmente vacío

    /*// 9.2Variable para observar el historial desde la UI( Muestra 2 datos con Pair)
    var historial by mutableStateOf<List<Pair<String, Double>>>(emptyList())
*/
    //10.3  Crear Variable para observar el historial desde la UI
    var historial by mutableStateOf<List<VentaDiariaEntity>>(emptyList())

    // Función para cargar el historial
    fun cargarHistorial() {
        viewModelScope.launch {
            historial = repository.obtenerHistorial()
        }
    }


    // 3. Función para actualizar los datos cuando el usuario escribe
    fun onRutaChange(index: Int, vendidos: String, precio: String, cargados: String) {
        val listaNueva = rutas.toMutableList()  //indicamos que la lista es mutable y extrae datos de la lista original ubicada en la variable rutas

        listaNueva[index] = listaNueva[index].copy(  //creamos una copia de la ruta en la posición especificada (tarjeta correspondiente según el index)
            garrafonesVendidos = vendidos,
            precioUnitario = precio,
            garrafonesCargados = cargados

        )
        rutas = listaNueva  //actualizamos la variable rutas con la nueva lista modificada para reflejar los cambios en la tarjeta correspondiente
    }
    // --- CALCULOS FINALES USANDO LOS DATOS OBTENIDOS EN LOS CASOS DE USOS---

    // Instanciamos los casos de uso
    private val calcularVentaTotalUseCase = CalcularVentaTotalUseCase()
    private val calcularTotalFinalUseCase = CalcularTotalFinalUseCase()
  private val calcularPagoRepartidorUseCase = CalcularPagoRepartidorUseCase()
//8.10  y  811   en esta sección la función hacerCorteDeCaja le dice al repositorio que guarde el historial de la venta del día
        fun hacerCorteDeCaja() {        //8.10b crear función para hacer corte de caja.
        viewModelScope.launch {     //8.10c
            val exito = repository.guardarVentaDelDia(rutas, obtenerTotalFinal())  //ViewModel le dice a repository que guarde el historial de la venta del día
            if (exito) {
               // Aquí podrías limpiar la pantalla o mostrar un mensaje
                rutas = List(6) { i -> Ruta(id = i, nombre = "Ruta ${i + 1}") }  //se limpia la pantalla
                gastos = ""
            }
        }
    }

    fun obtenerVentaTotal(): Double {   //
        return calcularVentaTotalUseCase(rutas)  //se llama a la función para calcular la venta total entre parentesis se escribe rutas porque es el argumento de la función
    }
    fun obtenerTotalFinal(): Double {
        return calcularTotalFinalUseCase(obtenerVentaTotal(), gastos)
    }
    fun obtenerPagoRepartidor(): Double {
        return calcularPagoRepartidorUseCase(rutas)  //se llama a la función para calcular el pago del repartidor entre parentesis se escribe rutas porque es el argumento de la función
    }
    //10.7.4 Agregar función para eliminar el historial
    fun eliminarHistorial() {
        viewModelScope.launch {  //accedemos a la base de datos
            repository.borrarHistorial()   //accedemos a la función del repositorio para borrar el historial
            // Después de borrar en la DB, vaciamos la lista en el ViewModel
            // para que la UI se actualice al instante
            historial = emptyList()  //el objeto historial se actualiza con una lista vacía
        }
    }
}


/* // En CalculadoraViewModel.kt
class CalculadoraViewModel(
    private val repository: VentaRepository // Usamos la interfaz
) : ViewModel() {

    // ... tus variables de rutas y gastos ...

    fun hacerCorteDeCaja() {
        viewModelScope.launch {
            val exito = repository.guardarVentaDelDia(rutas, obtenerTotalFinal())
            if (exito) {
                // Aquí podrías limpiar la pantalla o mostrar un mensaje
            }
        }
    }
}
*/