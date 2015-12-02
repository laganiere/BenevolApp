package ca.uottawa.eecs.seg2505.benevolapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import ca.uottawa.eecs.seg2505.benevolapp.controlleur.Delegateur;
import ca.uottawa.eecs.seg2505.benevolapp.model.Organisme;

public class MainOrganismeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_organisme);

        Delegateur.utilisateurCourant= new Organisme("Croix rouge",
                "340",
                "Catherine Stree",
                "",
                "K1R 1C4",
                "Ottawa",
                "613 560-7220",
                "http://www.croixrouge.ca",
                "rh@croixrouge.ca",
                "La Croix-Rouge canadienne vise à améliorer les conditions d’existence des personnes vulnérables en mobilisant le pouvoir de l’humanité au Canada et partout dans le monde.",
                "Grande",
                "Aide humanitaire",
                "");
    }

    public void onInformation(View view) {
        Intent intent = new Intent(this, ModifOrganismeActivity.class);
        startActivity(intent);
    }

    public void onOffre(View view) {
        Intent intent = new Intent(this, OffreListActivity.class);
        startActivity(intent);
    }

    public void onCancel(View view) {
        finish();
    }
}
