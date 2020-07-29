package com.examen.madrental.ws



data class RetourVehiculeWs (
    val id: Int,
    val nom: String,
    val image: String,
    val disponible: Int,
    val prixjournalierbase: Int,
    val promotion: Int,
    val agemin: Int,
    val categorieco2: String
)

