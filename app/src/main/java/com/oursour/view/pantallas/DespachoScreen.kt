package com.oursour.view.pantallas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oursour.view.componentes.*
import com.oursour.view.tema.*
import com.oursour.viewmodel.OurSourViewModel
import com.oursour.viewmodel.Pantalla

@Composable
fun DespachoScreen(vm: OurSourViewModel, direccion: String) {
    Column(Modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
        TituloPantalla("Despacho y pago")
        Text("Completa una dirección ficticia para probar el flujo.", color = TextoSecundario)
        CampoTexto("Dirección de despacho", direccion, vm::actualizarDireccion, Icons.Default.LocationOn)
        Surface(color = SuperficieOscura, shape = RoundedCornerShape(14.dp)) {
            Column(Modifier.padding(16.dp)) {
                Text("Resumen de compra", color = TextoPrincipal)
                FilaResumen("Productos", formatoMoneda(vm.total()))
                FilaResumen("Despacho", formatoMoneda(vm.costoDespacho()))
                FilaResumen("Total", formatoMoneda(vm.total() + vm.costoDespacho()), true)
            }
        }
        Text("Pago académico simulado. No se procesarán cobros reales.", color = AmbarSecundario)
        BotonPrincipal("Simular pago y confirmar pedido", vm::confirmarPagoSimulado)
        TextButton(onClick = { vm.navegar(Pantalla.CARRO) }) { Text("Volver al carro", color = TextoSecundario) }
    }
}
