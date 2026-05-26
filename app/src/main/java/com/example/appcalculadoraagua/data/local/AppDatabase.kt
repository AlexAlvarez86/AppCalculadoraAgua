package com.example.appcalculadoraagua.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.appcalculadoraagua.data.local.dao.VentaDao
import com.example.appcalculadoraagua.data.local.entities.VentaDiariaEntity


@Database(entities = [VentaDiariaEntity::class], version = 1)  //esta linea indica que @Database es una clase de base de datos de Room. entities indica las entidades que se usarán en la base de datos, en este caso VentaDiariaEntity. :: indican que es una referencia a la clase. version indica la versión de la base de datos.
abstract class AppDatabase : RoomDatabase() {   //esta linea indica que AppDatabase es una clase abstracta de RoomDatabase. RoomDatabase es la clase base para todas las bases de datos de Room.
    abstract fun ventaDao(): VentaDao //esta linea indica que ventaDao es un método abstracto que devuelve una instancia de la interfaz VentaDao. es decir, VentaDao es una interfaz que define los métodos para acceder a los datos de la base de datos.
}