package com.oursour.view.pantallas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oursour.view.componentes.BotonPrincipal
import com.oursour.view.componentes.TituloPantalla
import com.oursour.view.tema.*
import com.oursour.viewmodel.OurSourViewModel
import com.oursour.viewmodel.Pantalla

@Composable
fun DetalleRecetaScreen(vm: OurSourViewModel, idReceta: Int) {
    val receta = vm.recetas.firstOrNull { it.id == idReceta } ?: return
    LazyColumn(Modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        item { TextButton(onClick = { vm.navegar(Pantalla.RECETAS) }) { Text("‹ Volver a recetas", color = AmbarSecundario) } }
        item { TituloPantalla(receta.nombre) }
        item { Text(receta.descripcion, color = TextoSecundario) }
        item { Text("Compatibilidad: ${receta.porcentajeCompatibilidad}%", color = AmbarSecundario) }
        item { TituloPantalla("Ingredientes") }
        items(receta.ingredientes) { Text("•  $it", color = TextoPrincipal) }
        if (receta.ingredientesFaltantes.isNotEmpty()) {
            item { Text("Te podrían faltar", color = AmbarSecundario) }
            items(receta.ingredientesFaltantes) { Text("＋  $it", color = TextoSecundario) }
        }
        item { TituloPantalla("Preparación") }
        items(receta.preparacion) {
            androidx.compose.material3.Surface(color = SuperficieOscura, shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)) {
                Text(it, color = TextoPrincipal, modifier = Modifier.padding(14.dp))
            }
        }
        if (receta.productoAsociado != null) item {
            BotonPrincipal("Ver productos Our Sour", { vm.navegar(Pantalla.CATALOGO) })
        }
    }
}
