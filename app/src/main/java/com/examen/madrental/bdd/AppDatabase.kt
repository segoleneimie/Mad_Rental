package com.examen.madrental.bdd

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [VehiculesDTO::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun vehiculesDAO(): VehiculesDAO
}