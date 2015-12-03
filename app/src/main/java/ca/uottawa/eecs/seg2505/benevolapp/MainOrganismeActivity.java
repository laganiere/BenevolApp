package ca.uottawa.eecs.seg2505.benevolapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Calendar;

import ca.uottawa.eecs.seg2505.benevolapp.controlleur.Delegateur;
import ca.uottawa.eecs.seg2505.benevolapp.model.Disponibilite;
import ca.uottawa.eecs.seg2505.benevolapp.model.JourSemaine;
import ca.uottawa.eecs.seg2505.benevolapp.model.Organisme;
import ca.uottawa.eecs.seg2505.benevolapp.model.offre.Lieu;
import ca.uottawa.eecs.seg2505.benevolapp.model.offre.Offre;

public class MainOrganismeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_organisme);

        //A titre d'exemple pour tester la création de l'offre
        //Cette activité ainsi que son fichier .xml contient le bouton addtionnel pour la création de l'offre
        Organisme org3 = new Organisme("Parent Resource Centre",
                "300",
                "Goulburn Private",
                "",
                "K1N 1C9",
                "Ottawa",
                "613-565-2467 x229",
                "http://www.parentresource.ca",
                "rh@parentresource.ca",
                "As a children’s charity, the Parent Resource Centre (PRC) is a leader in family support resource programs and comprehensive training for professionals. With a focus on early learning, parent support, training, research and community capacity, PRC aims to have a lasting impact on children’s developmental outcomes.",
                "Moyenne",
                "Aide parentale",
                "");
        Offre o1 = new Offre("Titre1", "Type1", Calendar.getInstance(), Calendar.getInstance(), 1,
                new Lieu("Ville1", "CP1"), new Disponibilite(JourSemaine.Lundi, true), org3);
        Offre o2 = new Offre("Titre2", "Type2", Calendar.getInstance(), Calendar.getInstance(), 2,
                new Lieu("Ville2", "CP2"), new Disponibilite(JourSemaine.Mardi, true), org3);
        Offre o3 = new Offre("Titre3", "Type3", Calendar.getInstance(), Calendar.getInstance(), 3,
                new Lieu("Ville3", "CP3"), new Disponibilite(JourSemaine.Mercredi, true), org3);
        org3.addOffres(o1);
        org3.addOffres(o2);
        org3.addOffres(o3);

        Delegateur.utilisateurCourant = org3;
    }

    public void onCancel(View view) {
        finish();
    }

    public void newOffre(View view) {
        Intent intent = new Intent(this, CreerUneOffreActivity.class);
        startActivity(intent);
    }

    public void onManage(View view) {
        //TODO: SET ORGANISME AT INDEX TO MODIFY, FILL UP THE CORRESPONDING FIELDS
        startActivity(new Intent(this, EtatOffresActivity.class));
    }

    public void onDelete(View view) {
        //TODO: DELETE THE OFFER (APPORPRIATELY)
    }
}
