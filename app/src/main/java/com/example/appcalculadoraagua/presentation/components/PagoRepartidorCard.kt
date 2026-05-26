package com.example.appcalculadoraagua.presentation.components

// Este componente mostrará cuánto se le debe pagar al trabajador.

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SeccionPagoRepartidor(pagoTotal: Double) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "PAGO DEL REPARTIDOR",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp)
            Text(text = "Comisión ($2 por garrafón) + $250 base",
                fontSize = 10.sp)
            Text(
                text = "TOTAL A PAGAR: $${String.format("%.2f", pagoTotal)}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Black,

            )
        }
    }
}