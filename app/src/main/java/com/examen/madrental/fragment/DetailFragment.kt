package com.examen.madrental.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.examen.madrental.R
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment() {

    companion object {
        const val NOM_VEHICULE = "NOM_VEHICULE"
        const val PRIX_VEHICULE = "PRIX_VEHICULE"
        const val CATEGORIE_VEHICULE = "CATEGORIE_VEHICULE"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        // récupération de l'argument depuis DetailActivity :
        val arguments = requireArguments()
        val nom = arguments.getString(NOM_VEHICULE)
        val prix = arguments.getInt(PRIX_VEHICULE)
        val categorie = arguments.getString(CATEGORIE_VEHICULE)

        // affichage si les arguments sont passés
        nom?.let { libelle_vehicule.text = nom }
        prix?.let { libelle_vehicule_prix.text ="${prix}.00 €"}
        categorie?.let { libelle_vehicule_category.text = "Catégorie CO2 : ${categorie}" }
            }
    }
