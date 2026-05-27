package com.example.appcalculadoraagua.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcalculadoraagua.domain.model.Ruta
import com.example.appcalculadoraagua.domain.usecases.*
import com.example.appcalculadoraagua.domain.repository.VentaRepository
import kotlinx.coroutines.launch
import com.example.appcalculadoraagua.data.local.entities.VentaDiariaEntity

//11.1.-cambio a stateFlow - Importar .flow.MutableStateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class CalculadoraViewModel(
    private val repository: VentaRepository //8.10a Vincular los datos del repositorio (llamamos a la interfaz))
)  : ViewModel() {  //Indicamos que esta clase hereda de ViewModel

    //11.2.- Cambio a StateFlow Modificar las variables en CalculadoraViewModel.kt

    //************************ --- CALCULOS INICIALES ---***************************************
    // 1. Estado de las 6 rutas (Lista de objetos Ruta)
    ////Inicializamos la lista con 6 objetos Ruta mediante una expresión lambda que contiene un bucle for
    //11.2a - desabilitamos var rutas by mutableStateOf(List(6) { i -> Ruta(id = i, nombre = "Ruta ${i + 1}") })
    //11.2b - cambiamos por mutableStateFlow
    private val _rutas = MutableStateFlow(List(6) { i -> Ruta(id = i, nombre = "Ruta ${i + 1}") })
    val rutas: StateFlow<List<Ruta>> = _rutas.asStateFlow()

    // 2. Estado de los gastos (ajustes)
   //11.2c - desabilitamos var gastos by mutableStateOf("")  // Estado para los ajustes de gastos, inicialmente vacío
    private val _gastos = MutableStateFlow("")
    val gastos: StateFlow<String> = _gastos.asStateFlow()
    /*// 9.2Variable para observar el historial desde la UI( Muestra 2 datos con Pair)
    var historial by mutableStateOf<List<Pair<String, Double>>>(emptyList())
*/
    //10.3  Crear Variable para observar el historial desde la UI
    //11.2d.- deshabuilitamos var historial by mutableStateOf<List<VentaDiariaEntity>>(emptyList())
    private val _historial = MutableStateFlow<List<VentaDiariaEntity>>(emptyList())
    var historial: StateFlow<List<VentaDiariaEntity>> = _historial.asStateFlow()
    // Función para cargar el historial


    /////////////////////////****************CAMBIO A MutableStateFlow*******************////////////////////
   /* 11.2e .- deshabilitamos  fun cargarHistorial() {
        viewModelScope.launch {
            historial = repository.obtenerHistorial()
        }
    }
    */
    fun cargarHistorial() {viewModelScope.launch {
        _historial.value = repository.obtenerHistorial()
    }
    }

    // 3. Función para actualizar los datos cuando el usuario escribe
  /*  11.2f deshabilitamos   fun onRutaChange(index: Int, vendidos: String, precio: String, cargados: String) {
        val listaNueva = rutas.toMutableList()  //indicamos que la lista es mutable y extrae datos de la lista original ubicada en la variable rutas

        listaNueva[index] = listaNueva[index].copy(  //creamos una copia de la ruta en la posición especificada (tarjeta correspondiente según el index)
            garrafonesVendidos = vendidos,
            precioUnitario = precio,
            garrafonesCargados = cargados

        )
        rutas = listaNueva  //actualizamos la variable rutas con la nueva lista modificada para reflejar los cambios en la tarjeta correspondiente
    }
*/
    fun onRutaChange(index: Int, vendidos: String, precio: String, cargados: String) {
        _rutas.update { listaActual ->
            val listaNueva = listaActual.toMutableList()
            listaNueva[index] = listaNueva[index].copy(
                garrafonesVendidos = vendidos,
                precioUnitario = precio,
                garrafonesCargados = cargados
            )
            listaNueva // Devolvemos la lista nueva al flujo
        }
    }
    // --- CALCULOS FINALES USANDO LOS DATOS OBTENIDOS EN LOS CASOS DE USOS---

    // Instanciamos los casos de uso
    private val calcularVentaTotalUseCase = CalcularVentaTotalUseCase()
    private val calcularTotalFinalUseCase = CalcularTotalFinalUseCase()
  private val calcularPagoRepartidorUseCase = CalcularPagoRepartidorUseCase()





    //8.10  y  811   en esta sección la función hacerCorteDeCaja le dice al repositorio que guarde el historial de la venta del día
    /*  11.2g deshabilitamos    fun hacerCorteDeCaja() {        //8.10b crear función para hacer corte de caja.
        viewModelScope.launch {     //8.10c
            val exito = repository.guardarVentaDelDia(rutas, obtenerTotalFinal())  //ViewModel le dice a repository que guarde el historial de la venta del día
            if (exito) {
               // Aquí podrías limpiar la pantalla o mostrar un mensaje
                rutas = List(6) { i -> Ruta(id = i, nombre = "Ruta ${i + 1}") }  //se limpia la pantalla
                gastos = ""
            }
        }
    }*/
    fun hacerCorteDeCaja() {
        viewModelScope.launch {val exito = repository.guardarVentaDelDia(_rutas.value, obtenerTotalFinal())
            if (exito) {
                _rutas.value = List(6) { i -> Ruta(id = i, nombre = "Ruta ${i + 1}") }
                _gastos.value = ""
            }
        }
    }
    fun obtenerVentaTotal(): Double {   // 11.2f agregar _rutas.value para extraer la lista del tubo de viewModel (rutas ya no es una lista, si no un tubo por donde viaja la lista)
        return calcularVentaTotalUseCase(_rutas.value)  //se llama a la función para calcular la venta total entre parentesis se escribe rutas porque es el argumento de la función
    }

    fun obtenerTotalFinal(): Double {   //
        // 11.2 g Extraemos el valor String del StateFlow y lo convertimos a Double
        // Usamos _gastos.value porque estamos dentro del ViewModel
        val montoGastos = _gastos.value.toDoubleOrNull() ?: 0.0

        // 2. Le pasamos solo números al Caso de Uso
        return calcularTotalFinalUseCase(
            ventaTotal = obtenerVentaTotal(),
            montoGastos = montoGastos
        )
    }
    //11.2g Función para actualizar el estado de los gastos
    fun onGastosChange(nuevoGasto: String) {
        _gastos.value = nuevoGasto
    }
    fun obtenerPagoRepartidor(): Double {
        return calcularPagoRepartidorUseCase(_rutas.value)  //se llama a la función para calcular el pago del repartidor entre parentesis se escribe rutas porque es el argumento de la función
    }
    //10.7.4 Agregar función para eliminar el historial
    fun eliminarHistorial() {
        viewModelScope.launch {  //accedemos a la base de datos
            repository.borrarHistorial()   //accedemos a la función del repositorio para borrar el historial
            // Después de borrar en la DB, vaciamos la lista en el ViewModel, para que la UI se actualice al instante
            // Accedemos al privado con guion bajo para actualizar el valor
            _historial.value = emptyList()
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