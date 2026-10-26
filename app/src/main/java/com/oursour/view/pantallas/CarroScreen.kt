package com.oursour.view.pantallas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oursour.model.ArticuloCarro
import com.oursour.view.componentes.*
import com.oursour.view.tema.*
import com.oursour.viewmodel.OurSourViewModel
import com.oursour.viewmodel.Pantalla

@Composable
fun CarroScreen(vm: OurSourViewModel, articulos: List<ArticuloCarro>) {
    LazyColumn(Modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        item { TituloPantalla("Mi carro") }
        if (articulos.isEmpty()) {
            item { Text("Tu carro está vacío. Explora los productos Our Sour.", color = TextoSecundario) }
            item { BotonPrincipal("Ver productos", { vm.navegar(Pantalla.CATALOGO) }) }
        } else {
            items(articulos, key = { it.producto.id }) { articulo ->
                Surface(color = SuperficieOscura, shape = RoundedCornerShape(14.dp)) {
                    Column(Modifier.padding(14.dp)) {
                        Text(articulo.producto.nombre, color = TextoPrincipal)
                        Text("${formatoMoneda(articulo.producto.precio)} c/u", color = TextoSecundario)
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            IconButton(onClick = { vm.cambiarCantidad(articulo.producto.id, -1) }) { Icon(Icons.Default.Remove, "Disminuir", tint = AmbarSecundario) }
                            Text("${articulo.cantidad}", color = TextoPrincipal)
                            IconButton(onClick = { vm.cambiarCantidad(articulo.producto.id, 1) }) { Icon(Icons.Default.Add, "Aumentar", tint = AmbarSecundario) }
                            Spacer(Modifier.weight(1f))
                            Text(formatoMoneda(articulo.subtotal), color = AmbarSecundario)
                            IconButton(onClick = { vm.eliminarDelCarro(articulo.producto.id) }) { Icon(Icons.Default.DeleteOutline, "Eliminar", tint = ErrorOurSour) }
                        }
                    }
                }
            }
            item { FilaResumen("Subtotal", formatoMoneda(vm.total())) }
            item { FilaResumen("Despacho estimado", formatoMoneda(vm.costoDespacho())) }
            item { FilaResumen("Total estimado", formatoMoneda(vm.total() + vm.costoDespacho()), true) }
            item { BotonPrincipal("Continuar con despacho", { vm.navegar(Pantalla.DESPACHO) }) }
        }
    }
}
