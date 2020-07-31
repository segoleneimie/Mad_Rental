package com.examen.madrental

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.fragment.app.FragmentTransaction
import com.examen.madrental.bdd.AppDatabaseHelper
import com.examen.madrental.bdd.VehiculesDTO
import com.examen.madrental.fragment.DetailFragment


class DetailActivity : AppCompatActivity() {

    companion object {
        const val NOM_VEHICULE = "NOM_VEHICULE"
        const val PRIX_VEHICULE = "PRIX_VEHICULE"
        const val CATEGORIE_VEHICULE = "CATEGORIE_VEHICULE"
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        val nomVehicule = intent.getStringExtra(NOM_VEHICULE)
        val prixVehicule = intent.getIntExtra(PRIX_VEHICULE,0)
        val categorieVehicule = intent.getStringExtra(CATEGORIE_VEHICULE)



        val fragment = DetailFragment()
        val bundle = Bundle()
        bundle.putString(DetailFragment.NOM_VEHICULE, nomVehicule)
        bundle.putInt(DetailFragment.PRIX_VEHICULE, prixVehicule)
        bundle.putString(DetailFragment.CATEGORIE_VEHICULE, categorieVehicule)
        fragment.arguments = bundle

        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.conteneur_fragment,fragment)
        transaction.commit()


    }

// il n'y a pas de vérification si  véhicule est déjà en base. Cela doit se faire dans une transaction au niveau de la base de données
// ne fonctionnement pas en mode paysage, devrai surement être mise dans le fragement pour être fonctionnelle partout, mais je ny suis pas arrivé dans le temps que j'avais
    fun clicEnregistrer(view: View){
        Log.d("clic", "clic bouton")
        val nomVehicule = intent.getStringExtra(NOM_VEHICULE).toString()
        val prixVehicule = intent.getIntExtra(PRIX_VEHICULE,0)
        val categorieVehicule = intent.getStringExtra(CATEGORIE_VEHICULE).toString()
        AppDatabaseHelper.getDatabase(this).vehiculesDAO().insert(VehiculesDTO(0,
            nomVehicule,categorieVehicule,prixVehicule))
    }
}