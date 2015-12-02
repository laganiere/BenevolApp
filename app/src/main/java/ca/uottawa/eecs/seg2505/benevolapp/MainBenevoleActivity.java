package ca.uottawa.eecs.seg2505.benevolapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import ca.uottawa.eecs.seg2505.benevolapp.controlleur.Delegateur;
import ca.uottawa.eecs.seg2505.benevolapp.model.Benevole;
import ca.uottawa.eecs.seg2505.benevolapp.offresDisponibles.OffresDisponiblesActivity;

public class MainBenevoleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_benevole);
        if (Delegateur.utilisateurCourant == null) {
            Delegateur.utilisateurCourant= Delegateur.getInstance().dbFacade.getBenevole("ftremblay1234@gmail.com");
        }
    }

    public void onCancel(View view) {
        finish();
    }

    public void onDisponibilite(View view) {
        Intent intent = new Intent(this, DisponibiliteActivity.class);
        startActivity(intent);
    }

    public void onOffresDisponibles(View view) {
        Intent intent = new Intent(this, OffresDisponiblesActivity.class);
        startActivity(intent);
    }

    public void onOffre(View view) {
        Intent intent = new Intent(this, EtatOffresActivity.class);
        startActivity(intent);
    }

    public void onInscription(View view) {
        Intent intent = new Intent(this, ModifBenevoleActivity.class);
        startActivity(intent);
    }
}
