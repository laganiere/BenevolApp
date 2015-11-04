package ca.uottawa.eecs.seg2505.benevolapp.offresDisponibles;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import ca.uottawa.eecs.seg2505.benevolapp.R;
import ca.uottawa.eecs.seg2505.benevolapp.model.Disponibilite;
import ca.uottawa.eecs.seg2505.benevolapp.model.JourSemaine;
import ca.uottawa.eecs.seg2505.benevolapp.model.Organisme;
import ca.uottawa.eecs.seg2505.benevolapp.model.offre.Duree;
import ca.uottawa.eecs.seg2505.benevolapp.model.offre.Lieu;
import ca.uottawa.eecs.seg2505.benevolapp.model.offre.Offre;
import ca.uottawa.eecs.seg2505.benevolapp.model.offre.PersonneContact;

public class OffresDisponiblesActivity extends AppCompatActivity {

    private Toast notification;
    private List<Offre> offresDisponibles;
    private ArrayAdapter arrayAdapter;
    private SwipeFlingAdapterView flingContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set le Content au Layout pour Offres Disponibles
        setContentView(R.layout.activity_offres_disponibles_main);
        flingContainer = (SwipeFlingAdapterView) this.findViewById(R.id.adapter_offre);

        // Définition "hard coded" des offres disponibles, pourrait être fait différement dans le futur
        offresDisponibles = new ArrayList<>();

        // Adpateur pour créer le layout d'une offre en fonction des objets offres
        arrayAdapter = new OffreAdapter(this, R.layout.activity_offres_disponibles_offre, offresDisponibles);

        // Ajout des offres dans la liste à afficher
        loadOffres();

        flingContainer.setAdapter(arrayAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {

            @Override
            public void removeFirstObjectInAdapter() {
                // Retrait de l'offre traitée
                Log.d("OFFRES", "Offre Retirée!");
                offresDisponibles.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                // L'offre a été envoyée vers la gauche
                makeToast(OffresDisponiblesActivity.this, "Offre Ignorée!");
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                // L'offre a été envoyée vers la droite
                makeToast(OffresDisponiblesActivity.this, "Offre Sélectionnée!");
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Sur le point de ne plus avoir d'offre à présenter
                // Possibilité de créer/aller chercher les offres dynamiquement ou tout reloader automatiquement.
            }

            @Override
            public void onScroll(float scrollProgressPercent) {
                // Distance de scrolling de l'offre par rapport à sa position originale
                View view = flingContainer.getSelectedView();
                view.findViewById(R.id.item_ignore_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                view.findViewById(R.id.item_select_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
            }
        });

        /* // Pas nécessaire (adaoust):
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                // L'offre a été clickée
                makeToast(OffresDisponiblesActivity.this, "Offre Clickée!");
            }
        }); */

    }

    private void makeToast(Context ctx, String s){
        if (notification != null) notification.cancel();
        notification = Toast.makeText(ctx, s, Toast.LENGTH_SHORT);
        notification.setGravity(Gravity.BOTTOM, 0, 225);
        notification.show();
    }

    private void loadOffres() {
        // Example d'offre, peut aussi, vérifier une condition et ajouter seulement certaines offres
        offresDisponibles.add(new Offre(
                "Ceci est le titre d'une offre..",
                "Communautaire",
                Arrays.asList("Écriture", "Lecture"),
                "Ceci est la description de l'offre",
                new GregorianCalendar(2015, 7, 25),
                new GregorianCalendar(2016, 3, 16),
                new Duree(5, 30),
                3,
                12,
                new PersonneContact("Personne", "Per.Per", "personne.pers@email.com"),
                new Lieu("Ottawa", "K1N 6N5"),
                new Disponibilite(JourSemaine.Lundi, true),
                new Organisme("Nom de l'organimsme", "", "")
        ));
        offresDisponibles.add(new Offre(
                "Voici une deuxième offre..",
                "Plaisir",
                Arrays.asList("Danse", "Chant"),
                null,
                new GregorianCalendar(2015, 7, 25),
                new GregorianCalendar(2016, 3, 16),
                null,
                3,
                0,
                null,
                new Lieu("Gatineau", "J8X 3Y9"),
                new Disponibilite(JourSemaine.Vendredi, false),
                new Organisme("Super Organisme", "", "organisme@email.com")
        ));
        arrayAdapter.notifyDataSetChanged();
        Log.d("OFFRES", "Offres Rechargées!");
    }

    public void onSelect(View view) {
        if (offresDisponibles.size() != 0) flingContainer.getTopCardListener().selectRight();
    }

    public void onIgnore(View view) {
        if (offresDisponibles.size() != 0) flingContainer.getTopCardListener().selectLeft();
    }

    public void onReload(View view) {
        loadOffres();
    }

}
