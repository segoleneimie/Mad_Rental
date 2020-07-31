package com.examen.madrental

import android.os.Bundle
import android.util.Log
import android.widget.CompoundButton
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.examen.madrental.bdd.AppDatabaseHelper
import com.examen.madrental.ws.RetourVehiculeWs
import com.examen.madrental.ws.RetrofitSingleton
import com.examen.madrental.ws.WSInterface
import com.examen.madrental.ws.reseauHelper
import kotlinx.android.synthetic.main.activity_liste.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ListeActivity : AppCompatActivity() {
//Retrofit
    private val serviceRetrofit = RetrofitSingleton.retrofit.create(WSInterface::class.java)




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_liste)

    // layout manager, décrivant comment les items sont disposés :
        val layoutManager = LinearLayoutManager(this)
        liste_vehicules.layoutManager = layoutManager

        recupereVehicules()
//        réupération de la valeur du switch
        val switchFavoris : Switch = findViewById(R.id.favorisSwith)
//        ajout du listener sur le switch
        switchFavoris.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            Log.v(
                "Switch State=",
                "" + isChecked
            )
            if (switchFavoris.isChecked()){
                // si c'est checked affiche la list de favoris présent en base
                var vehiculeAdapter = AppDatabaseHelper.getDatabase(this).vehiculesDAO().getListeVehicules()
                Log.d("liste favoris", vehiculeAdapter.toString())
                if ( !vehiculeAdapter.isNullOrEmpty()){
                    // initialisation d'une liste d'objet du même type que le retour du webservice
                    val favorisList = mutableListOf<RetourVehiculeWs>()
                    //pour chaque véhicule présent dans la base l'ajouter dans la liste des favoris
                    for (a in 0..vehiculeAdapter.lastIndex){

                        vehiculeAdapter[a].nomVehicule?.let {
                            vehiculeAdapter[a].categorieVehicules?.let { it1 ->
                                RetourVehiculeWs(vehiculeAdapter[a].vehiculeId.toInt(),
                                    it,"null",0,vehiculeAdapter[a].prixVehicule,
                                    0,0, it1
                                )
                            }
                        }?.let { favorisList.add(it) }

                    }
                    // insérer la liste des favoris dans la liste des véhicules de l'adapter
                    val liste = favorisList?.let {
                        VehiculesAdapter(it.toMutableList(),this@ListeActivity)}
                    liste_vehicules.adapter = liste
                } else {
                    Toast.makeText(this,"il n'y a pas de favoris d'enregistrés",Toast.LENGTH_LONG).show()
                    recupereVehicules()
                }

            } else {
                recupereVehicules()
            }

        })

    }


    fun recupereVehicules(){

        // vérification de l'état de la connexion internet :
        if (!reseauHelper.estConnecte(this))
        {
            Log.d("tag", "Test Réseau")
            Toast.makeText(this, "vous n'avez pas de connexion internet", Toast.LENGTH_LONG).show()
            return
        }

        //appel du webservice
        val call = serviceRetrofit.wsGet()
        call.enqueue(object : Callback<List<RetourVehiculeWs>> {

            override fun onResponse(call: Call<List<RetourVehiculeWs>>, response: Response<List<RetourVehiculeWs>>){
                Log.d("tag", "Response: ${response.code()}")
                if(response.isSuccessful){
                    val retourWSVehicules = response.body()
                    Log.d("tag", retourWSVehicules.toString())

                    // à ajouter pour de meilleures performances :
                    liste_vehicules.setHasFixedSize(true)

                    val vehiculesAdapter = retourWSVehicules?.let {
                        VehiculesAdapter(it.toMutableList(),this@ListeActivity)
                    }
                    liste_vehicules.adapter = vehiculesAdapter
                }
            }
            override fun onFailure(call: Call<List<RetourVehiculeWs>>, t: Throwable){
                Log.d("tag", "OnFailure: ${t.message}")
            }
        })

    }
}
