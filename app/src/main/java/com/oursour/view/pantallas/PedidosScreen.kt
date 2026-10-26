package com.oursour.view.pantallas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oursour.model.Pedido
import com.oursour.view.componentes.*
import com.oursour.view.tema.*
import com.oursour.viewmodel.OurSourViewModel
import com.oursour.viewmodel.Pantalla

@Composable
fun PedidosScreen(vm: OurSourViewModel, pedidos: List<Pedido>) {
    LazyColumn(Modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        item { TituloPantalla("Mis pedidos") }
        if (pedidos.isEmpty()) item { Text("Todavía no tienes pedidos de demostración.", color = TextoSecundario) }
        items(pedidos, key = { it.id }) { pedido ->
            Surface(color = SuperficieOscura, shape = RoundedCornerShape(16.dp)) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(pedido.id, color = AmbarSecundario)
                    Text(pedido.estado, color = ExitoOurSour)
                    Text("Despacho: ${pedido.direccion}", color = TextoSecundario)
                    Text("${pedido.articulos.sumOf { it.cantidad }} unidades · ${formatoMoneda(pedido.total)}", color = TextoPrincipal)
                }
            }
        }
        item { BotonSecundario("Volver al inicio", { vm.navegar(Pantalla.INICIO) }) }
    }
}
