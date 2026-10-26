package com.oursour.model

data class Ingrediente(
    val id: Int,
    val nombre: String,
    val categoria: CategoriaIngrediente,
    val esOurSour: Boolean = false
)

enum class CategoriaIngrediente(val etiqueta: String) {
    DESTILADO("Destilados"), LICOR("Licores"), FRUTA("Frutas"),
    JUGO("Jugos"), MIXER("Mixers"), OUR_SOUR("Productos Our Sour"), OTRO("Otros")
}

data class Receta(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val porcentajeCompatibilidad: Int,
    val ingredientes: List<String>,
    val ingredientesFaltantes: List<String>,
    val preparacion: List<String>,
    val productoAsociado: String? = null,
    val tiempoMinutos: Int = 5
)

data class Producto(
    val id: Int, val nombre: String, val descripcion: String, val precio: Int, val stock: Int
)

data class ArticuloCarro(val producto: Producto, val cantidad: Int = 1) {
    val subtotal: Int get() = producto.precio * cantidad
}

data class Pedido(
    val id: String, val articulos: List<ArticuloCarro>, val direccion: String,
    val costoDespacho: Int, val total: Int, val estado: String
)
