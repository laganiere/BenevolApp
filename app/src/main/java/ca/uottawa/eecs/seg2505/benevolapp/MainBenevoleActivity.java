package ca.uottawa.eecs.seg2505.benevolapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ca.uottawa.eecs.seg2505.benevolapp.offresDisponibles.OffresDisponiblesActivity;

public class MainBenevoleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_benevole);
    }

    public void onCancel(View view) {
        finish();
    }

    public void onOffresDisponibles(View view) {
        Intent intent = new Intent(this, OffresDisponiblesActivity.class);
        startActivity(intent);
    }
}
