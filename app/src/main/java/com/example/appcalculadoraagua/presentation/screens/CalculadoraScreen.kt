package com.example.appcalculadoraagua.presentation.screens

// sección que se encarga de organizar los componentes en la interfaz de usuario y
// conectar los datos del ViewModel.

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appcalculadoraagua.viewmodel.CalculadoraViewModel
// Importamos nuestros componentes
import com.example.appcalculadoraagua.presentation.components.*

@OptIn(ExperimentalMaterial3Api::class) // Necesario para TopAppBar
@Composable
fun CalculadoraScreen(viewModel: CalculadoraViewModel) {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Calculadora AguaField",
                        fontWeight = FontWeight.Black,
                        color = MaterialTheme.colorScheme.onPrimaryContainer  // Cambia el color del texto

                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(    // Cambia el color de fondo del TopAppBar
                    containerColor = MaterialTheme.colorScheme.primaryContainer //
                )
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(scrollState)
        ) {
            // 1. Tarjetas de Rutas

            viewModel.rutas.forEachIndexed { index, ruta ->
                RutaCard(
                    ruta = ruta,
                    onValueChange = { vendidos, precio, cargados ->
                        viewModel.onRutaChange(index, vendidos, precio, cargados)
                    }
                )
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

            // 2. Sección de Totales
            SeccionTotales(
                gastos = viewModel.gastos,
                onGastosChange = { viewModel.gastos = it },
                ventaBruta = viewModel.obtenerVentaTotal(),
                totalFinal = viewModel.obtenerTotalFinal(),

            )

            Spacer(modifier = Modifier.height(16.dp))

            // 3. Sección Pago Repartidor
            SeccionPagoRepartidor(pagoTotal = viewModel.obtenerPagoRepartidor())

            Spacer(modifier = Modifier.height(32.dp))

           //9.3 Pantalla para Hitorial con Botón para cargarlo

            Button(
                onClick = { viewModel.cargarHistorial() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("ACTUALIZAR HISTORIAL")
            }

            Text(
                text = "HISTORIAL RECIENTE",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            // ENCABEZADO DE TABLA
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "FECHA",
                    modifier = Modifier.weight(1f),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
                Text(
                    text = "GARRAFONES VENDIDOS.",
                    modifier = Modifier.weight(1f),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
                Text(
                    text = "PAGO DEL REPARTIDOR.",
                    modifier = Modifier.weight(1f),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
                Text(
                    text = "TOTAL VENTA",
                    modifier = Modifier.weight(1f),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
            }

/*// Mostramos la lista de historial  con FECHA Y TOTAL  (2 ELEMENTOS)
            viewModel.historial.forEach { item ->
                HistorialItem(fecha = item.first, totalVenta = item.second)
            }
*/
         //10.5 MOSTRAR EL HISTORIAL CON 4 ELEMENTOS
            // Mostramos la lista de historial
            viewModel.historial.forEach { registro ->
                // Ahora le pasamos el objeto completo 'registro'
                // que es de tipo VentaDiariaEntity
                HistorialItem(venta = registro)
            }

// Botón para hacer el "GUARDAR Corte de Caja" y guardar
            Button(
                onClick = { viewModel.hacerCorteDeCaja() },
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
            ) {
                Text("GUARDAR CORTE DE HOY")
            }


 //10.7.5 Generar el botón en la interfaz para borrar el historial
            Spacer(modifier = Modifier.height(32.dp)) // Espacio extra

            Button(
                onClick = { viewModel.eliminarHistorial() },  // Llamamos a la función para borrar el historial
                modifier = Modifier.fillMaxWidth(),  // Ocupa todo el ancho disponible
                colors = ButtonDefaults.buttonColors(  // Cambiamos el color del botón
                    containerColor = MaterialTheme.colorScheme.error // Color rojo de alerta
                )
            ) {
                Text("ELIMINAR TODO EL HISTORIAL", color = MaterialTheme.colorScheme.onError)
            }

            Spacer(modifier = Modifier.height(50.dp)) // Espacio al final para que no tape el botón el sistema
        }
    }
}
