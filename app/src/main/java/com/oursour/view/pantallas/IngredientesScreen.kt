package com.oursour.view.pantallas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oursour.model.Ingrediente
import com.oursour.view.componentes.BotonPrincipal
import com.oursour.view.componentes.TituloPantalla
import com.oursour.view.tema.*
import com.oursour.viewmodel.OurSourViewModel

@Composable
fun IngredientesScreen(vm: OurSourViewModel, seleccionados: Set<Int>) {
    LazyColumn(Modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        item { TituloPantalla("Mis ingredientes") }
        item { Text("Marca los ingredientes que tienes disponibles.", color = TextoSecundario) }
        items(vm.ingredientes.groupBy { it.categoria }.toList()) { (categoria, lista) ->
            Text(categoria.etiqueta, color = AmbarSecundario)
            lista.forEach { ingrediente: Ingrediente ->
                Surface(color = SuperficieOscura, shape = RoundedCornerShape(12.dp)) {
                    Row(Modifier.fillMaxWidth().clickable { vm.alternarIngrediente(ingrediente.id) }.padding(horizontal = 12.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(seleccionados.contains(ingrediente.id), { vm.alternarIngrediente(ingrediente.id) },
                            colors = CheckboxDefaults.colors(checkedColor = NaranjaPrimario))
                        Text(ingrediente.nombre, color = TextoPrincipal, modifier = Modifier.weight(1f))
                        if (ingrediente.esOurSour) Text("OUR SOUR", color = AmbarSecundario)
                    }
                }
            }
        }
        item { BotonPrincipal("Buscar recetas compatibles", vm::buscarRecetas) }
    }
}
