package com.oursour.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.oursour.dao.IngredienteDao
import com.oursour.model.IngredienteLocal

@Database(entities = [IngredienteLocal::class], version = 1, exportSchema = false)
abstract class BaseDatosOurSour : RoomDatabase() {
    abstract fun ingredienteDao(): IngredienteDao

    companion object {
        @Volatile private var instancia: BaseDatosOurSour? = null
        fun obtener(context: Context): BaseDatosOurSour = instancia ?: synchronized(this) {
            instancia ?: Room.databaseBuilder(
                context.applicationContext, BaseDatosOurSour::class.java, "oursour_local.db"
            ).build().also { instancia = it }
        }
    }
}
