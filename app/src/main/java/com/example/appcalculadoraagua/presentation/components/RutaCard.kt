package com.example.appcalculadoraagua.presentation.components

import android.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appcalculadoraagua.domain.model.Ruta
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.Alignment
import androidx.compose.foundation.text.input.InputTransformation.Companion.keyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.input.InputTransformation.Companion.keyboardOptions


//Este componente maneja la entrada de datos de cada ruta.
//Función para crear la tarjeta de cada ruta en la que se maneja la entrada de datos del usuario
@Composable
fun RutaCard(
    ruta: Ruta,     //se crea el objeto ruta para recibir los datos del usuario que Hereda de la clase Ruta
    onValueChange: (String, String, String) -> Unit       //se crea la función para recibir los datos del usuario (3 strings)
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = ruta.nombre, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            // 1. Campo: Garrafones Cargados
                OutlinedTextField(
                    value = ruta.garrafonesCargados,
                    onValueChange = { onValueChange(ruta.garrafonesVendidos, ruta.precioUnitario, it) },
                    label = { Text("Garrafones Cargados") },
                    modifier = Modifier.weight(1f),  //indicamos que ocupe el 50% del espacio disponible
                singleLine = true,      //indicamos que solo se pueda escribir una sola linea,
               // maxLines = 1,
                keyboardOptions = keyboardOptions(keyboardType = KeyboardType.Number)
                )



                // 2. Campo: Garrafones vendidos
                OutlinedTextField(
                    value = ruta.garrafonesVendidos,
                    onValueChange = { onValueChange( it, ruta.precioUnitario, ruta.garrafonesCargados) },
                    label = { Text("Garrafones Vendidos") },
                    modifier = Modifier.weight(1f),
                    singleLine = true,      //indicamos que solo se pueda escribir una sola linea
                  //  maxLines = 1,
                    keyboardOptions = keyboardOptions(keyboardType = KeyboardType.Number)  //teclado numerico
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.Bottom
            ) {

            // 3. Campo: Precio Unitario
                OutlinedTextField(
                    value = ruta.precioUnitario,
                    onValueChange = { onValueChange(ruta.garrafonesVendidos, it, ruta.garrafonesCargados) },
                    label = { Text("Precio $") },
                    modifier = Modifier.weight(1f),
                    singleLine = true,      //indicamos que solo se pueda escribir una sola linea
                    //  maxLines = 1,
                    keyboardOptions = keyboardOptions(keyboardType = KeyboardType.Number)  //teclado numerico
                )

                // 4. Sección: Total por garrafones vendidos en ruta
                Text(
                    text = "Subtotal: $${ruta.calcularTotal()}",
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Start, // Alinea el texto a la derecha dentro de su 50%
                    modifier = Modifier
                        .weight(1f) // Ocupa exactamente la mitad del espacio disponible
                     //   .padding(bottom = 8.dp) // Ajuste fino para alinearlo con la base del TextField
                )
            }
        }
    }
}

private fun RowScope.keyboardOptions(keyboardType: KeyboardType): KeyboardOptions {
    return KeyboardOptions(keyboardType = KeyboardType.Number)
}
