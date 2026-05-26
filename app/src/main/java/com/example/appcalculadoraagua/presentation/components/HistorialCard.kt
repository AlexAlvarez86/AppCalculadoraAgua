package com.example.appcalculadoraagua.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.appcalculadoraagua.data.local.entities.VentaDiariaEntity

//10.4  Actualizar la interfaz del historial para que muestre 4 columnas.
@Composable
fun HistorialItem(venta: VentaDiariaEntity) {   //crear el objeto venta para recibir los datos del historial de ventas del repositorio ventaDiariaEntity
    val fechaFormat = java.text.SimpleDateFormat("dd/MM/yyyy", java.util.Locale.getDefault())
    val fechaTexto = fechaFormat.format(venta.fecha)

    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Columna 1: FECHA
            Text(text = fechaTexto, modifier = Modifier.weight(1f))

            // Columna 2: GARRAFONES (NUEVA)
            Text(text = "${venta.totalGarrafones} G.", modifier = Modifier.weight(1f))

            // Columna 3: PAGO REP (NUEVA)
            Text(text = "$${venta.pagoRepartidor}", modifier = Modifier.weight(1f))

            // Columna 4: TOTAL
            Text(text = "$${venta.totalVenta}", modifier = Modifier.weight(1f))
        }
    }
}

/*
//9.1    FUNCIÓN PARA MOSTRAR EL HISTORIAL DE VENTAS CON LA FECHA Y EL TOTAL DE LA VENTA
@Composable
fun HistorialItem(fecha: String, totalVenta: Double) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = "Fecha", fontSize = 12.sp)
                Text(text = fecha, fontWeight = FontWeight.Bold)
            }
            Column(horizontalAlignment = androidx.compose.ui.Alignment.End) {
                Text(text = "Venta Total", fontSize = 12.sp)
                Text(
                    text = "$${String.format("%.2f", totalVenta)}",
                    fontWeight = FontWeight.Black,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

*/