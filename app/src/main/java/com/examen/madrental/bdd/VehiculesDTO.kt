package com.examen.madrental.bdd

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vehicules")
data class VehiculesDTO (
    @PrimaryKey(autoGenerate = true)
    val vehiculeId: Long = 0,
    val nomVehicule: String? = null,
    val categorieVehicules: String? = null,
    val prixVehicule: Int = 0
)