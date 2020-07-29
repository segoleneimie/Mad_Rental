package com.examen.madrental

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.examen.madrental.ws.RetourVehiculeWs
import kotlinx.android.synthetic.main.item_vehicule.view.*

class VehiculesAdapter(private var listeVehicules: MutableList<RetourVehiculeWs>, private val listeActivity: ListeActivity): RecyclerView.Adapter<VehiculesAdapter.VehiculeViewHolder>()
{


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehiculeViewHolder {
        val viewVehicule = LayoutInflater.from(parent.context).inflate(R.layout.item_vehicule, parent, false)

        return VehiculeViewHolder(viewVehicule)
    }

    override fun onBindViewHolder(holder: VehiculeViewHolder, position: Int) {
        holder.bind(listeVehicules[position])
    }

    override fun getItemCount(): Int = listeVehicules.size

    inner class VehiculeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(vehicule: RetourVehiculeWs) = with(itemView){
            itemView.libelle_vehicule.text = vehicule.nom
            itemView.libelle_vehicule_prix.text = "${vehicule.prixjournalierbase}.00 €"
            itemView.libelle_vehicule_category.text = "Catégorie CO2 : ${vehicule.categorieco2}"
        }
        init {
            itemView.setOnClickListener {
                // affichage du détail, selon qu'on est en mode smartphone ou en mode tablette :
                if (listeActivity.findViewById<FrameLayout>(R.id.conteneur_fragment) != null)
                {
                    // mode tablette :
                    val fragment = DetailFragment()
                    val bundle = Bundle()
                    bundle.putString(DetailFragment.NOM_VEHICULE, listeVehicules[adapterPosition].nom)
                    bundle.putString(DetailFragment.CATEGORIE_VEHICULE, listeVehicules[adapterPosition].categorieco2)
                    bundle.putInt(DetailFragment.PRIX_VEHICULE, listeVehicules[adapterPosition].prixjournalierbase)

                    fragment.arguments = bundle

                    // transaction :
                    val transaction: FragmentTransaction = listeActivity.supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.conteneur_fragment, fragment)
                    transaction.commit()
                }
                else
                {
                    // mode smartphone :
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.NOM_VEHICULE, listeVehicules[adapterPosition].nom)
                    intent.putExtra(DetailActivity.CATEGORIE_VEHICULE, listeVehicules[adapterPosition].categorieco2)
                    intent.putExtra(DetailActivity.PRIX_VEHICULE, listeVehicules[adapterPosition].prixjournalierbase)

                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}