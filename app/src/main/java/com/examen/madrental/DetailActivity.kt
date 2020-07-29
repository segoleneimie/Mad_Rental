package com.examen.madrental

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction


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
}