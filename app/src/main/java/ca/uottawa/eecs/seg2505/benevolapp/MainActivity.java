package ca.uottawa.eecs.seg2505.benevolapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import ca.uottawa.eecs.seg2505.benevolapp.controlleur.Delegateur;
import ca.uottawa.eecs.seg2505.benevolapp.db.MemoireFacade;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Delegateur.dbFacade= new MemoireFacade();
    }

    // demarrage de l'activité bénévole
    public void onBenevole(View view) {
        Intent intent = new Intent(this, MainBenevoleActivity.class);
        startActivity(intent);
    }

    // demarrage de l'activité organisme
    public void onOrganisme(View view) {
        Intent intent = new Intent(this, MainOrganismeActivity.class);
        startActivity(intent);//
    }


}
