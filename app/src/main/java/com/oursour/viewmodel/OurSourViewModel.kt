package com.oursour.viewmodel

import androidx.lifecycle.ViewModel
import com.oursour.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

enum class Pantalla {
    BIENVENIDA, LOGIN, REGISTRO, INICIO, INGREDIENTES, RECETAS,
    DETALLE_RECETA, CATALOGO, CARRO, DESPACHO, PEDIDOS, ADMIN
}

data class EstadoOurSour(
    val pantalla: Pantalla = Pantalla.BIENVENIDA,
    val usuario: String = "", val correo: String = "", val contrasena: String = "",
    val ingredientesSeleccionados: Set<Int> = setOf(1, 3, 4),
    val recetaSeleccionada: Int = 1,
    val carrito: List<ArticuloCarro> = emptyList(),
    val direccion: String = "", val mensaje: String? = null,
    val pedidos: List<Pedido> = emptyList()
)

class OurSourViewModel : ViewModel() {
    private val _estado = MutableStateFlow(EstadoOurSour())
    val estado: StateFlow<EstadoOurSour> = _estado

    val ingredientes = listOf(
        Ingrediente(1, "Gin", CategoriaIngrediente.DESTILADO),
        Ingrediente(2, "Vodka", CategoriaIngrediente.DESTILADO),
        Ingrediente(3, "Limón", CategoriaIngrediente.FRUTA),
        Ingrediente(4, "Agua tónica", CategoriaIngrediente.MIXER),
        Ingrediente(5, "Frutilla", CategoriaIngrediente.FRUTA),
        Ingrediente(6, "Our Sour Limón", CategoriaIngrediente.OUR_SOUR, true),
        Ingrediente(7, "Our Sour Maracuyá", CategoriaIngrediente.OUR_SOUR, true),
        Ingrediente(8, "Jugo de naranja", CategoriaIngrediente.JUGO)
    )

    val recetas = listOf(
        Receta(1, "Gin Sour", "Cítrico, equilibrado y refrescante.", 90,
            listOf("Gin", "Limón", "Our Sour Limón", "Hielo"),
            listOf("Our Sour Limón", "Hielo"),
            listOf("Agrega los ingredientes a una coctelera.", "Agita con hielo.", "Sirve en un vaso frío."),
            "Our Sour Limón", 5),
        Receta(2, "Gin Tonic", "Un clásico fresco y simple.", 100,
            listOf("Gin", "Agua tónica", "Limón"), emptyList(),
            listOf("Llena un vaso con hielo.", "Agrega gin y agua tónica.", "Decora con limón."),
            null, 3),
        Receta(3, "Sour de Maracuyá", "Frutal con un toque ácido.", 75,
            listOf("Gin", "Our Sour Maracuyá", "Hielo"), listOf("Our Sour Maracuyá"),
            listOf("Agrega los ingredientes a una coctelera.", "Agita enérgicamente.", "Sirve y disfruta."),
            "Our Sour Maracuyá", 5)
    )

    val productos = listOf(
        Producto(1, "Our Sour Limón", "Base cítrica para preparar cócteles.", 7990, 20),
        Producto(2, "Our Sour Maracuyá", "Mezcla frutal lista para servir.", 8490, 15),
        Producto(3, "Our Sour Botánicos", "Perfil aromático para tus preparaciones.", 8990, 12)
    )

    fun navegar(pantalla: Pantalla) = _estado.update { it.copy(pantalla = pantalla, mensaje = null) }
    fun actualizarCorreo(valor: String) = _estado.update { it.copy(correo = valor) }
    fun actualizarContrasena(valor: String) = _estado.update { it.copy(contrasena = valor) }
    fun actualizarNombre(valor: String) = _estado.update { it.copy(usuario = valor) }
    fun actualizarDireccion(valor: String) = _estado.update { it.copy(direccion = valor) }

    fun ingresar() {
        val e = _estado.value
        if (!e.correo.contains("@") || e.contrasena.length < 4) {
            _estado.update { it.copy(mensaje = "Ingresa un correo válido y contraseña de al menos 4 caracteres.") }
        } else navegar(Pantalla.INICIO)
    }

    fun registrar() {
        val e = _estado.value
        if (e.usuario.isBlank() || !e.correo.contains("@") || e.contrasena.length < 4) {
            _estado.update { it.copy(mensaje = "Completa nombre, correo válido y contraseña de al menos 4 caracteres.") }
        } else navegar(Pantalla.INICIO)
    }

    fun alternarIngrediente(id: Int) = _estado.update {
        val nueva = it.ingredientesSeleccionados.toMutableSet()
        if (!nueva.add(id)) nueva.remove(id)
        it.copy(ingredientesSeleccionados = nueva)
    }

    fun buscarRecetas() = navegar(Pantalla.RECETAS)
    fun seleccionarReceta(id: Int) = _estado.update { it.copy(recetaSeleccionada = id, pantalla = Pantalla.DETALLE_RECETA) }

    fun agregarAlCarro(producto: Producto) = _estado.update { e ->
        val previo = e.carrito.find { it.producto.id == producto.id }
        val nuevo = if (previo == null) e.carrito + ArticuloCarro(producto)
        else e.carrito.map { if (it.producto.id == producto.id) it.copy(cantidad = it.cantidad + 1) else it }
        e.copy(carrito = nuevo, mensaje = "${producto.nombre} agregado al carro.")
    }

    fun cambiarCantidad(id: Int, delta: Int) = _estado.update { e ->
        val nuevo = e.carrito.mapNotNull { a ->
            if (a.producto.id != id) a else a.copy(cantidad = a.cantidad + delta).takeIf { it.cantidad > 0 }
        }
        e.copy(carrito = nuevo)
    }

    fun eliminarDelCarro(id: Int) = _estado.update { it.copy(carrito = it.carrito.filterNot { a -> a.producto.id == id }) }
    fun total(): Int = _estado.value.carrito.sumOf { it.subtotal }
    fun costoDespacho(): Int = if (_estado.value.carrito.isEmpty() || total() >= 50000) 0 else 2990

    fun confirmarPagoSimulado() {
        val e = _estado.value
        if (e.carrito.isEmpty()) {
            _estado.update { it.copy(mensaje = "Agrega al menos un producto antes de continuar.") }; return
        }
        if (e.direccion.isBlank()) {
            _estado.update { it.copy(mensaje = "Ingresa una dirección de despacho.") }; return
        }
        val pedido = Pedido(
            "OS-${(e.pedidos.size + 1).toString().padStart(4, '0')}", e.carrito,
            e.direccion, costoDespacho(), total() + costoDespacho(), "Pago simulado aprobado"
        )
        _estado.update {
            it.copy(pedidos = listOf(pedido) + it.pedidos, carrito = emptyList(),
                pantalla = Pantalla.PEDIDOS, mensaje = "Pago de prueba aprobado. No se realizó ningún cobro real.")
        }
    }
    fun limpiarMensaje() = _estado.update { it.copy(mensaje = null) }
}
