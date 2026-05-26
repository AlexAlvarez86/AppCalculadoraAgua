package com.example.appcalculadoraagua.data.local

import android.content.Context
import androidx.room.Room
import com.example.appcalculadoraagua.data.local.AppDatabase

//
object DatabaseProvider {
    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "agua_fiel_db"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}