package com.examen.madrental.ws

import retrofit2.Call
import retrofit2.http.GET

interface WSInterface {

    // appel get :
    @GET("get-vehicules.php")
    fun wsGet(): Call<List<RetourVehiculeWs>>
}