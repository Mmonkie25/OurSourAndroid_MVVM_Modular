package com.oursour.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredientes_frecuentes")
data class IngredienteLocal(
    @PrimaryKey val id: Int,
    val nombre: String,
    val categoria: String
)
