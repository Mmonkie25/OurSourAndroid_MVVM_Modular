package com.oursour.view.pantallas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oursour.view.componentes.TituloPantalla
import com.oursour.view.tema.TextoSecundario
import com.oursour.viewmodel.OurSourViewModel

@Composable
fun RecetasScreen(vm: OurSourViewModel) {
    LazyColumn(Modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        item { TituloPantalla("Recetas para ti") }
        item { Text("Sugerencias de demostración según los ingredientes seleccionados.", color = TextoSecundario) }
        items(vm.recetas.sortedByDescending { it.porcentajeCompatibilidad }) {
            TarjetaReceta(it) { vm.seleccionarReceta(it.id) }
        }
    }
}
