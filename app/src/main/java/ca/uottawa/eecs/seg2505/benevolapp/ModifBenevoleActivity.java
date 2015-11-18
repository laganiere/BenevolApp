package ca.uottawa.eecs.seg2505.benevolapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import ca.uottawa.eecs.seg2505.benevolapp.controlleur.BenevoleControlleur;
import ca.uottawa.eecs.seg2505.benevolapp.controlleur.Delegateur;
import ca.uottawa.eecs.seg2505.benevolapp.model.Benevole;

public class ModifBenevoleActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_modif_benevole);

        final Button btn = (Button) findViewById(R.id.btnModifierCompte);


        //mettre valeur bénévole dans la vue


        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText txtPrenom = (EditText) findViewById(R.id.editPrenom);
                EditText txtAge = (EditText) findViewById(R.id.editAge);
                EditText txtCourriel = (EditText) findViewById(R.id.editCourriel);
                EditText txtTelephone = (EditText) findViewById(R.id.editTelephone);
                EditText txtVille = (EditText) findViewById(R.id.editVille);
                EditText txtCodePostal = (EditText) findViewById(R.id.editPostal);
                EditText txtDomaineInteret = (EditText) findViewById(R.id.editDomaine);
                RadioButton rbtn = (RadioButton) findViewById(R.id.rdbHomme);

                //DEvrait déjà avoir un bénévole dans la session ou quelque chose comme ca

                Benevole b = new Benevole();
                b.setPrenom(txtPrenom.getText().toString());

                b.setAge(Integer.parseInt((txtAge.getText().toString())));
                b.setCourriel(txtCourriel.getText().toString());
                b.setNumeroTelephone(txtTelephone.getText().toString());
                b.setVille(txtVille.getText().toString());
                b.setCodePostal(txtCodePostal.getText().toString());
                b.setDomaineInterets(txtDomaineInteret.getText().toString());
                b.setGenre(rbtn.isChecked());

                BenevoleControlleur benCont = Delegateur.getInstance().getBenevoleControlleur();
                benCont.SauvegarderBenevole(b);
            }
        });
    }
}
