package ca.uottawa.eecs.seg2505.benevolapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ca.uottawa.eecs.seg2505.benevolapp.controlleur.Delegateur;
import ca.uottawa.eecs.seg2505.benevolapp.model.Benevole;
import ca.uottawa.eecs.seg2505.benevolapp.offresDisponibles.OffresDisponiblesActivity;

public class MainBenevoleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_benevole);

        Delegateur.utilisateurCourant= new Benevole("François",
                "Tremblay",
                "Ottawa",
                "K2C 4F5",
                "613-231-2143",
                "ftremblay1234@gmail.com",
                "Je suis bon en programmation et surtout pour faire des sites web.",
                23,
                true);

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
