package ca.uottawa.eecs.seg2505.benevolapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBenevole(View view) {
        Intent intent = new Intent(this, MainBenevoleActivity.class);
        startActivity(intent);
    }

    public void onOrganisme(View view) {
        Intent intent = new Intent(this, MainOrganismeActivity.class);
        startActivity(intent);
    }


}
