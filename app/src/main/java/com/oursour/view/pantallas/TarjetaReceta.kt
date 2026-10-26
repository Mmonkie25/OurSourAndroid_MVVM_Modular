package com.oursour.view.pantallas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.oursour.model.Receta
import com.oursour.view.tema.*

@Composable
fun TarjetaReceta(receta: Receta, abrir: () -> Unit) {
    Surface(color = SuperficieOscura, shape = RoundedCornerShape(18.dp),
        modifier = Modifier.fillMaxWidth().clickable(onClick = abrir)) {
        Column(Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(Modifier.weight(1f)) {
                    Text(receta.nombre, color = TextoPrincipal, style = MaterialTheme.typography.titleMedium)
                    Text(receta.descripcion, color = TextoSecundario, fontSize = 13.sp)
                }
                Text("${receta.porcentajeCompatibilidad}%", color = AmbarSecundario)
            }
            Spacer(Modifier.height(10.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Schedule, null, tint = AmbarSecundario, modifier = Modifier.size(16.dp))
                Spacer(Modifier.width(5.dp))
                Text("${receta.tiempoMinutos} min", color = TextoSecundario, fontSize = 12.sp)
                Spacer(Modifier.weight(1f))
                Text("Ver receta  ›", color = NaranjaPrimario)
            }
        }
    }
}
