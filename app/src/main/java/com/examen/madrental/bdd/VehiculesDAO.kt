package com.examen.madrental.bdd

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
abstract class VehiculesDAO {

    @Query("SELECT * FROM vehicules")
    abstract fun getListeVehicules() : List<VehiculesDTO>

    @Insert
    abstract fun insert(vararg vehicule: VehiculesDTO)
}