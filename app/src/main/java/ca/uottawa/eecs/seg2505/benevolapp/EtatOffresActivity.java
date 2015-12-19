package ca.uottawa.eecs.seg2505.benevolapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import ca.uottawa.eecs.seg2505.benevolapp.R;
import ca.uottawa.eecs.seg2505.benevolapp.controlleur.Delegateur;
import ca.uottawa.eecs.seg2505.benevolapp.model.Benevole;
import ca.uottawa.eecs.seg2505.benevolapp.model.Disponibilite;
import ca.uottawa.eecs.seg2505.benevolapp.model.JourSemaine;
import ca.uottawa.eecs.seg2505.benevolapp.model.Organisme;
import ca.uottawa.eecs.seg2505.benevolapp.model.offre.EtatBenevoleOffre;
import ca.uottawa.eecs.seg2505.benevolapp.model.offre.Lieu;
import ca.uottawa.eecs.seg2505.benevolapp.model.offre.Offre;
import ca.uottawa.eecs.seg2505.benevolapp.offresDisponibles.OffreAdapter;
import ca.uottawa.eecs.seg2505.benevolapp.offresDisponibles.OffresDisponiblesActivity;

public class EtatOffresActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{


    private List<Offre> offreList;
    private ArrayAdapter arrayAdapter;
    private List<String> item;
    private SwipeFlingAdapterView flingContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etat_offres);
        ListView mylistview = (ListView) findViewById(R.id.listViewOffre);
        offreList = new ArrayList<>();
        item = new ArrayList<>();

       offreList.addAll(Delegateur.getInstance().getBenevoleControlleur().getOffreAppliquer());




        for (int i=0; i < offreList.size(); i++){
<<<<<<< HEAD
            if(offreList.get(i).getEtatBenevole(Delegateur.dbFacade.getBenevole(Delegateur.utilisateurCourant.getCourriel()))== EtatBenevoleOffre.Selectionne  ){
            item.add(offreList.get(i).getTitre());
            }
=======
            Offre of= offreList.get(i);
            item.add(of.getTitre() + " :: "+ of.getPostulants().get((Benevole)(Delegateur.getInstance().getUtilisateurCourant())));

>>>>>>> refs/remotes/laganiere/master
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,item);

        mylistview.setAdapter(adapter);
        mylistview.setOnItemClickListener(this);


    }

    public void onBack(View view) {
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, EtatOffreButtonActivity.class);
        startActivity(intent);





    }

}
