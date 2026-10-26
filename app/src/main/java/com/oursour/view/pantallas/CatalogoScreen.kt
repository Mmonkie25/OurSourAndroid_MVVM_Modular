package com.oursour.view.pantallas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oursour.view.componentes.BotonPrincipal
import com.oursour.view.componentes.TituloPantalla
import com.oursour.view.componentes.formatoMoneda
import com.oursour.view.tema.*
import com.oursour.viewmodel.OurSourViewModel

@Composable
fun CatalogoScreen(vm: OurSourViewModel) {
    LazyColumn(Modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        item { TituloPantalla("Productos Our Sour") }
        item { Text("Productos de demostración para el MVP.", color = TextoSecundario) }
        items(vm.productos) { producto ->
            Surface(color = SuperficieOscura, shape = RoundedCornerShape(16.dp)) {
                Column(Modifier.padding(16.dp)) {
                    Text(producto.nombre, color = TextoPrincipal, style = MaterialTheme.typography.titleMedium)
                    Text(producto.descripcion, color = TextoSecundario)
                    Spacer(Modifier.height(10.dp))
                    Text(formatoMoneda(producto.precio), color = AmbarSecundario, style = MaterialTheme.typography.titleMedium)
                    Text("Stock demo: ${producto.stock}", color = TextoSecundario)
                    Spacer(Modifier.height(10.dp))
                    BotonPrincipal("Agregar al carro", { vm.agregarAlCarro(producto) })
                }
            }
        }
    }
}
