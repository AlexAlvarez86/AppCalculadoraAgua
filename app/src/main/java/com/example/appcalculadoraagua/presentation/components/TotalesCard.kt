package com.example.appcalculadoraagua.presentation.components

//Este componente manejará la parte de los gastos y el total final del negocio.

        import androidx.compose.foundation.layout.*
                import androidx.compose.material3.*
                import androidx.compose.runtime.Composable
                import androidx.compose.ui.Modifier
                import androidx.compose.ui.graphics.Color
                import androidx.compose.ui.text.font.FontWeight
                import androidx.compose.ui.text.input.KeyboardType
                import androidx.compose.ui.unit.dp
                import androidx.compose.ui.unit.sp
                import androidx.compose.foundation.text.input.InputTransformation.Companion.keyboardOptions
                import androidx.compose.foundation.text.input.InputTransformation.Companion.keyboardOptions
                import androidx.compose.foundation.text.input.InputTransformation.Companion.keyboardOptions
                @Composable

                fun SeccionTotales(
                    gastos: String,
                    onGastosChange: (String) -> Unit,
                    ventaBruta: Double,
                    totalFinal: Double
                ) {
                    Column {
                        Text(text = "RESUMEN DE VENTAS", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        OutlinedTextField(
                            value = gastos,
                            onValueChange = onGastosChange,  //se llama a la función para recibir los datos del usuario
                            label = { Text("Ajustes o Gastos realizados ($)") },
                            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                            singleLine = true,
                            //keyboardOptions = keyboardOptions(keyboardType = KeyboardType.Number)
                        )
                        Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Venta Bruta: $$ventaBruta")
                Text(text = "TOTAL FINAL (Venta - Gastos): $${String.format("%.2f", totalFinal)}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color(0xFF4AC446)
                    )
            }
        }
    }
}