package com.oursour.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.oursour.model.IngredienteLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface IngredienteDao {
    @Query("SELECT * FROM ingredientes_frecuentes ORDER BY nombre")
    fun observarFrecuentes(): Flow<List<IngredienteLocal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun guardar(ingrediente: IngredienteLocal)

    @Query("DELETE FROM ingredientes_frecuentes WHERE id = :id")
    suspend fun eliminar(id: Int)

    @Query("DELETE FROM ingredientes_frecuentes")
    suspend fun eliminarTodos()
}
