package ca.uottawa.eecs.seg2505.benevolapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;


import ca.uottawa.eecs.seg2505.benevolapp.model.Disponibilite;
import ca.uottawa.eecs.seg2505.benevolapp.model.JourSemaine;


public class ActivityAvailibility2 extends AppCompatActivity {

    /*
    * How this works:
    *   - m for the Android Convention
    *   - L, M, Me, J, V, S, D for the days of the week in French
    *   - M, S for Matin (morning) and Soir (evening)
    *
    * Exemple : mLS = monday evening
    *           mJM = thursday morning
    *           mSM = saturday morning
    *           mDS = sunday evening
    */
    private CheckBox mLM, mLS;
    private CheckBox mMM, mMS;
    private CheckBox mMeM, mMeS;
    private CheckBox mJM, mJS;
    private CheckBox mVM, mVS;
    private CheckBox mSM, mSS;
    private CheckBox mDM, mDS;

    private CheckBox[] checkBoxReferences;


    private Button mCancel;
    private Button mConfirm;

    private Disponibilite disponibilite;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_availability);

        mLM = (CheckBox) findViewById(R.id.chk_lundiMatin);
        mLS = (CheckBox) findViewById(R.id.chk_lundiSoir);

        mMM = (CheckBox) findViewById(R.id.chk_mardiMatin);
        mMS = (CheckBox) findViewById(R.id.chk_mardiSoir);

        mMeM = (CheckBox) findViewById(R.id.chk_mercrediMatin);
        mMeS = (CheckBox) findViewById(R.id.chk_mercrediSoir);

        mJM = (CheckBox) findViewById(R.id.chk_jeudiMatin);
        mJS = (CheckBox) findViewById(R.id.chk_jeudiSoir);

        mVM = (CheckBox) findViewById(R.id.chk_vendrediMatin);
        mVS = (CheckBox) findViewById(R.id.chk_vendrediSoir);

        mSM = (CheckBox) findViewById(R.id.chk_samediMatin);
        mSS = (CheckBox) findViewById(R.id.chk_samediSoir);

        mDM = (CheckBox) findViewById(R.id.chk_dimancheMatin);
        mDS = (CheckBox) findViewById(R.id.chk_dimancheSoir);

        checkBoxReferences = new CheckBox[]{mLM, mLS, mMM, mMS, mMeM, mMeS, mJM, mJS, mVM, mVS, mSM, mSS, mDM, mDS};

        setDifferences(mLM, JourSemaine.Lundi, false);
        setDifferences(mLS, JourSemaine.Lundi, true);
        setDifferences(mMM, JourSemaine.Mardi, false);
        setDifferences(mMS, JourSemaine.Mardi, true);
        setDifferences(mMeM, JourSemaine.Mercredi, false);
        setDifferences(mMeS, JourSemaine.Mercredi, true);
        setDifferences(mJM, JourSemaine.Jeudi, false);
        setDifferences(mJS, JourSemaine.Jeudi, true);
        setDifferences(mVM, JourSemaine.Vendredi, false);
        setDifferences(mVS, JourSemaine.Vendredi, true);
        setDifferences(mSM, JourSemaine.Samedi, false);
        setDifferences(mSS, JourSemaine.Samedi, true);
        setDifferences(mDM, JourSemaine.Dimanche, false);
        setDifferences(mDS, JourSemaine.Dimanche, true);

        mCancel = (Button) findViewById(R.id.button2);
        //Closes app upon cancel
        mCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        mConfirm = (Button) findViewById(R.id.button);
        //Passes the result of the activity for the calling activity to use
        mConfirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent result = new Intent();
                result.putExtra("jourSemaine", disponibilite.getJour().toString());
                result.putExtra("isSoir", disponibilite.isSoir());
                setResult(1, result);
                finish();
            }
        });

        Disponibilite disponibilite = null;
        String jourSemaine = getIntent().getStringExtra("jourSemaine");
        boolean isSoir = getIntent().getBooleanExtra("isSoir", false);
        if (jourSemaine!= null && !jourSemaine.isEmpty()){
            disponibilite = new Disponibilite(JourSemaine.valueOf(jourSemaine), isSoir);}
        //If there is already a disponibilite for this offre it loads the previous choice into the interface for the user to see
        if (disponibilite != null){
            int index = 0;
            switch (disponibilite.getJour()){
                case Lundi: index = index + 0;
                    break;
                case Mardi: index = index + 2;
                    break;
                case Mercredi: index = index + 4;
                    break;
                case Jeudi: index = index + 6;
                    break;
                case Vendredi: index = index + 8;
                    break;
                case Samedi: index = index + 10;
                    break;
                case Dimanche: index = index + 12;
                    break;
            }
            index = index +  (disponibilite.isSoir() ? 1 : 0);
            checkBoxReferences[index].setChecked(true);}
    }

    //Sets up what happens when a checkbox gets clicked, which is to create the disponibite object corresponding to that checkbox
    private void setDifferences(final CheckBox checkBox, final JourSemaine jour, final boolean isDay) {
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                for(CheckBox box : checkBoxReferences){
                    box.setChecked(false);
                    disponibilite = null;
                }

                checkBox.setChecked(isChecked);

                if (checkBox.isChecked()){
                    disponibilite = new Disponibilite(jour, isDay);
                }

            }
        });
    }
}
