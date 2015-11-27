package ca.uottawa.eecs.seg2505.benevolapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ca.uottawa.eecs.seg2505.benevolapp.controlleur.BenevoleControlleur;
import ca.uottawa.eecs.seg2505.benevolapp.controlleur.Delegateur;
import ca.uottawa.eecs.seg2505.benevolapp.model.Benevole;
import ca.uottawa.eecs.seg2505.benevolapp.model.Utilisateur;

public class ModifBenevoleActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //HERE

        super.onCreate(savedInstanceState);
        final BenevoleControlleur benCont = Delegateur.getInstance().getBenevoleControlleur();
                setContentView(R.layout.activity_modif_benevole);

        final Button btn = (Button) findViewById(R.id.btnModifierCompte);

        Utilisateur utilisateur = Delegateur.getInstance().getUtilisateurCourant();
        Benevole b = benCont.getBenevole(utilisateur.getCourriel());

        if (b==null) {
            android.content.Context context = getApplicationContext();
            CharSequence text = "Une erreur s'est produite.";
            int duration = android.widget.Toast.LENGTH_LONG;
            android.widget.Toast toast = android.widget.Toast.makeText(context, text, duration);
            toast.show();
        }
        else {

            final EditText txtPrenom = (EditText) findViewById(R.id.editPrenom);
            final EditText txtAge = (EditText) findViewById(R.id.editAge);
            final EditText txtCourriel = (EditText) findViewById(R.id.editCourriel);
            final EditText txtTelephone = (EditText) findViewById(R.id.editTelephone);
            final EditText txtVille = (EditText) findViewById(R.id.editVille);
            final EditText txtCodePostal = (EditText) findViewById(R.id.editPostal);
            final EditText txtDomaineInteret = (EditText) findViewById(R.id.editDomaine);
            final RadioButton rbtnHomme = (RadioButton) findViewById(R.id.rdbHomme);
            final RadioButton rbtnFemme = (RadioButton) findViewById(R.id.rdbFemme);


            txtPrenom.setText(b.getPrenom());
            txtAge.setText(b.getAge().toString());
            txtCourriel.setText(b.getCourriel());
            txtTelephone.setText(b.getNumeroTelephone());
            txtVille.setText(b.getVille());
            txtCodePostal.setText(b.getCodePostal());
            txtDomaineInteret.setText(b.getDomaineInterets());


            if (b.getGenre())
                rbtnHomme.setChecked(true);
            else
                rbtnFemme.setChecked(true);


            //mettre valeur bénévole dans la vue


            btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {


                    //DEvrait déjà avoir un bénévole dans la session ou quelque chose comme ca



                    if (txtPrenom.getText().toString().equals("") || txtAge.getText().toString().equals("") ||
                            txtVille.getText().toString().equals("") || txtCodePostal.getText().toString().equals("")){
                        showToast("Des informations obligatoire sont manquantes.");
                    }
                    else if (Integer.parseInt(txtAge.getText().toString()) < 1){
                        showToast("L'âge doit être plus grand que 0.");
                    }
                    else if(!isValid(txtCodePostal.getText().toString().replace(" ", "").toUpperCase(),"[A-Z]{1}\\d{1}[A-Z]{1}\\d{1}[A-Z]{1}\\d{1}")){
                        showToast("Le code postal n'est pas valide.");
                    }
                    else if (!txtTelephone.getText().toString().equals("") && !isValid(txtTelephone.getText().toString(),"\\d{3}-\\d{3}-\\d{4}")){
                        showToast("Le numéro de téléphone n'est pas valide.");
                    }
                    else{
                        Benevole b = new Benevole();
                        b.setPrenom(txtPrenom.getText().toString());
                        b.setAge(Integer.parseInt(txtAge.getText().toString()));
                        b.setCourriel(txtCourriel.getText().toString());
                        b.setNumeroTelephone(txtTelephone.getText().toString());
                        b.setVille(txtVille.getText().toString());
                        b.setCodePostal(txtCodePostal.getText().toString());
                        b.setDomaineInterets(txtDomaineInteret.getText().toString());
                        b.setGenre(rbtnHomme.isChecked());

                        if(benCont.SauvegarderBenevole(b)){
                            showToast("Les informations ont été modifiées.");
                            finish();
                        }
                        else
                            showToast("Impossible de sauvegarder. Des informations sont invaide.");


                    }
                }


            });
        }




    }

    private void showToast(CharSequence message){
        android.content.Context context = getApplicationContext();
        int duration = android.widget.Toast.LENGTH_LONG;
        android.widget.Toast toast = android.widget.Toast.makeText(context, message, duration);
        toast.show();
    }
    private boolean isValid(String text, String PATTERN){
        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }
}
