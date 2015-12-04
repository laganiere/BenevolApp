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

        Delegateur.utilisateurCourant = Delegateur.getInstance().dbFacade.getOrganisme("rh@wocrc.ca");
    }

    public void onInformation(View view) {
        Intent intent = new Intent(this, ModifOrganismeActivity.class);
        startActivity(intent);
    }

    public void onSelection(View view){
        Intent intent = new Intent(this, MainOffreList.class);
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
