//package com.examen.madrental
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.util.Log
//import android.widget.Toast
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.examen.madrental.ws.RetourVehiculeWs
//import com.examen.madrental.ws.RetrofitSingleton
//import com.examen.madrental.ws.WSInterface
//import com.examen.madrental.ws.reseauHelper
//import kotlinx.android.synthetic.main.activity_liste.*
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class MainActivity : AppCompatActivity() {
//    // Retrofit :
//    private val serviceRetrofit = RetrofitSingleton.retrofit.create(WSInterface::class.java)
//
//    override fun onCreate(savedInstanceState: Bundle?)
//    {
//        // init :
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        // layout manager, décrivant comment les items sont disposés :
//        val layoutManager = LinearLayoutManager(this)
//        liste_vehicules.layoutManager = layoutManager
//
////        getListVehicule()
//    }
//
//    fun getListVehicule(){
//            // vérification de l'état de la connexion internet :
//            if (!reseauHelper.estConnecte(this))
//            {
//                Log.d("tag", "Test Réseau")
//                Toast.makeText(this, "vous n'aves pas de connection internet", Toast.LENGTH_LONG).show()
//                return
//            }
//
//
//
//            //appel du webservice
//            val call = serviceRetrofit.wsGet()
//            call.enqueue(object : Callback<List<RetourVehiculeWs>> {
//
//                override fun onResponse(call: Call<List<RetourVehiculeWs>>, response: Response<List<RetourVehiculeWs>>){
//                    Log.d("tag", "Response: ${response.code()}")
//                    if(response.isSuccessful){
//                        val retourWSVehicules = response.body()
//                        Log.d("tag", retourWSVehicules.toString())
//
//                        // à ajouter pour de meilleures performances :
//                        liste_vehicules.setHasFixedSize(true)
//
//                        val vehiculesAdapter = retourWSVehicules?.let {
//                            VehiculesAdapter(it.toMutableList())
//                        }
//                        liste_vehicules.adapter = vehiculesAdapter
//                    }
//                }
//                override fun onFailure(call: Call<List<RetourVehiculeWs>>, t: Throwable){
//                    Log.d("tag", "OnFailure: ${t.message}")
//                    //texte_vehicule.text = t.message
//                    //progressbar_attente.visibility = View.GONE
//                }
//            })
//    }
//}