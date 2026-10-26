package com.oursour.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.oursour.view.pantallas.*
import com.oursour.view.tema.*
import com.oursour.viewmodel.*

@Composable
fun OurSourApp(viewModel: OurSourViewModel) {
    val estado by viewModel.estado.collectAsState()
    Scaffold(
        containerColor = FondoPrincipal,
        bottomBar = {
            if (estado.pantalla !in listOf(Pantalla.BIENVENIDA, Pantalla.LOGIN, Pantalla.REGISTRO)) {
                NavigationBar(containerColor = SuperficieOscura, contentColor = TextoPrincipal) {
                    ElementoNavegacion("Inicio", Icons.Default.Home, estado.pantalla == Pantalla.INICIO) { viewModel.navegar(Pantalla.INICIO) }
                    ElementoNavegacion("Ingredientes", Icons.Default.Tune, estado.pantalla == Pantalla.INGREDIENTES) { viewModel.navegar(Pantalla.INGREDIENTES) }
                    ElementoNavegacion("Productos", Icons.Default.LocalBar, estado.pantalla == Pantalla.CATALOGO) { viewModel.navegar(Pantalla.CATALOGO) }
                    ElementoNavegacion("Carro", Icons.Default.ShoppingCart, estado.pantalla == Pantalla.CARRO) { viewModel.navegar(Pantalla.CARRO) }
                    ElementoNavegacion("Pedidos", Icons.Default.ReceiptLong, estado.pantalla == Pantalla.PEDIDOS) { viewModel.navegar(Pantalla.PEDIDOS) }
                }
            }
        }
    ) { padding ->
        Column(Modifier.fillMaxSize().padding(padding)) {
            estado.mensaje?.let { mensaje ->
                Surface(color = SuperficieOscura, modifier = Modifier.fillMaxWidth().clickable { viewModel.limpiarMensaje() }) {
                    Text(mensaje, Modifier.padding(12.dp), color = AmbarSecundario, fontSize = 13.sp)
                }
            }
            when (estado.pantalla) {
                Pantalla.BIENVENIDA -> BienvenidaScreen({ viewModel.navegar(Pantalla.LOGIN) }, { viewModel.navegar(Pantalla.REGISTRO) })
                Pantalla.LOGIN -> AccesoScreen(
                    registro = false, nombre = estado.usuario, correo = estado.correo, contrasena = estado.contrasena,
                    cambiarNombre = viewModel::actualizarNombre, cambiarCorreo = viewModel::actualizarCorreo,
                    cambiarContrasena = viewModel::actualizarContrasena, enviar = viewModel::ingresar,
                    cambiarModo = { viewModel.navegar(Pantalla.REGISTRO) }
                )
                Pantalla.REGISTRO -> AccesoScreen(
                    registro = true, nombre = estado.usuario, correo = estado.correo, contrasena = estado.contrasena,
                    cambiarNombre = viewModel::actualizarNombre, cambiarCorreo = viewModel::actualizarCorreo,
                    cambiarContrasena = viewModel::actualizarContrasena, enviar = viewModel::registrar,
                    cambiarModo = { viewModel.navegar(Pantalla.LOGIN) }
                )
                Pantalla.INICIO -> InicioScreen(viewModel)
                Pantalla.INGREDIENTES -> IngredientesScreen(viewModel, estado.ingredientesSeleccionados)
                Pantalla.RECETAS -> RecetasScreen(viewModel)
                Pantalla.DETALLE_RECETA -> DetalleRecetaScreen(viewModel, estado.recetaSeleccionada)
                Pantalla.CATALOGO -> CatalogoScreen(viewModel)
                Pantalla.CARRO -> CarroScreen(viewModel, estado.carrito)
                Pantalla.DESPACHO -> DespachoScreen(viewModel, estado.direccion)
                Pantalla.PEDIDOS -> PedidosScreen(viewModel, estado.pedidos)
                Pantalla.ADMIN -> AdministracionScreen(viewModel)
            }
        }
    }
}

@Composable
private fun ElementoNavegacion(
    titulo: String, icono: androidx.compose.ui.graphics.vector.ImageVector,
    seleccionado: Boolean, alPulsar: () -> Unit
) {
    NavigationBarItem(
        selected = seleccionado, onClick = alPulsar,
        icon = { Icon(icono, contentDescription = titulo) },
        label = { Text(titulo, fontSize = 10.sp) },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = NaranjaPrimario, selectedTextColor = NaranjaPrimario,
            indicatorColor = FondoPrincipal, unselectedIconColor = TextoSecundario,
            unselectedTextColor = TextoSecundario
        )
    )
}
