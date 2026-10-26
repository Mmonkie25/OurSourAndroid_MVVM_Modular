package com.oursour.view.pantallas

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oursour.view.componentes.FilaResumen
import com.oursour.view.componentes.TituloPantalla
import com.oursour.view.tema.AmbarSecundario
import com.oursour.view.tema.TextoSecundario
import com.oursour.viewmodel.OurSourViewModel

@Composable
fun AdministracionScreen(vm: OurSourViewModel) {
    Column(Modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        TituloPantalla("Panel administrador")
        Text("Resumen de demostración", color = TextoSecundario)
        FilaResumen("Productos en catálogo demo", vm.productos.size.toString())
        FilaResumen("Pedidos simulados en esta sesión", vm.estado.value.pedidos.size.toString())
        FilaResumen("Recetas de ejemplo", vm.recetas.size.toString())
        Text("Las métricas reales deben venir del backend y mostrarse agregadas.", color = AmbarSecundario)
    }
}
