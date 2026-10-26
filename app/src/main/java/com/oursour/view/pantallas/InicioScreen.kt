package com.oursour.view.pantallas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Kitchen
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.oursour.view.componentes.BotonPrincipal
import com.oursour.view.componentes.BotonSecundario
import com.oursour.view.componentes.TituloPantalla
import com.oursour.view.tema.*
import com.oursour.viewmodel.OurSourViewModel
import com.oursour.viewmodel.Pantalla

@Composable
fun InicioScreen(vm: OurSourViewModel) {
    LazyColumn(Modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
        item { Text("OUR SOUR", color = AmbarSecundario, letterSpacing = 3.sp) }
        item { TituloPantalla("¿Qué preparamos hoy?") }
        item { Text("Encuentra recetas según lo que tienes en casa.", color = TextoSecundario) }
        item {
            Surface(color = SuperficieOscura, shape = RoundedCornerShape(18.dp),
                modifier = Modifier.fillMaxWidth().clickable { vm.navegar(Pantalla.INGREDIENTES) }) {
                Row(Modifier.padding(18.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Kitchen, null, tint = NaranjaPrimario, modifier = Modifier.size(38.dp))
                    Spacer(Modifier.width(14.dp))
                    Column(Modifier.weight(1f)) {
                        Text("Mis ingredientes", color = TextoPrincipal)
                        Text("Selecciona lo que tienes disponible", color = TextoSecundario)
                    }
                    Icon(Icons.Default.ChevronRight, null, tint = AmbarSecundario)
                }
            }
        }
        item { BotonPrincipal("Buscar recetas", vm::buscarRecetas) }
        item { TituloPantalla("Recetas recomendadas") }
        items(vm.recetas.take(3)) { receta -> TarjetaReceta(receta) { vm.seleccionarReceta(receta.id) } }
        item { BotonSecundario("Explorar productos", { vm.navegar(Pantalla.CATALOGO) }) }
        item { BotonSecundario("Panel administrador (demo)", { vm.navegar(Pantalla.ADMIN) }) }
    }
}
