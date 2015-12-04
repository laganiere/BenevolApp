package ca.uottawa.eecs.seg2505.benevolapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.TextView;

import java.util.List;

import ca.uottawa.eecs.seg2505.benevolapp.controlleur.BenevoleControlleur;
import ca.uottawa.eecs.seg2505.benevolapp.controlleur.Delegateur;
import ca.uottawa.eecs.seg2505.benevolapp.controlleur.OrganismeControlleur;
import ca.uottawa.eecs.seg2505.benevolapp.db.DBFacade;
import ca.uottawa.eecs.seg2505.benevolapp.db.MemoireFacade;
import ca.uottawa.eecs.seg2505.benevolapp.model.Benevole;
import ca.uottawa.eecs.seg2505.benevolapp.model.offre.Offre;

/**
 * Created by mly083 on 15/10/15.
 */
public class MainDescriptionBenevole extends AppCompatActivity {


    public Delegateur deleg = Delegateur.getInstance();
    public DBFacade mr;
    public BenevoleControlleur bvcont;
    public Benevole benev;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mr = new MemoireFacade();
        Delegateur.setDBFacade(mr);
        bvcont=deleg.getBenevoleControlleur();
        setContentView(R.layout.activity_details_benevole);


        Bundle recdData = getIntent().getExtras();
        String myVal = recdData.getString("benevole_name");
        benev=bvcont.getBenevolebyname(myVal);
        TextView info_benevole = (TextView)findViewById(R.id.info_benevole);


       info_benevole.setText(benev.toString());







    }

    public void onClickAccepter(View view) {
     //   Intent intent = new Intent(this, MainBenevoleList.class);
      //  startActivity(intent);

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        //Do your Yes progress
                        mr.selectionner(benev, MainBenevoleList.getCurrentOffre()); //A revoir
                        mr.sauvegarderOffre(MainBenevoleList.getCurrentOffre());
                        mr.sauvegarderBenevole(benev);


                       // Toast.makeText(getApplicationContext(), "CurrentOffre:"+ MainBenevoleList.getCurrentOffre(), Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(), "Operation effectuee", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), MainOffreList.class));

                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //Do your No progress
                        break;
                }
            }
        };
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        ab.setMessage("Voulez vous vraiment accepter?").setPositiveButton("Oui", dialogClickListener)
                .setNegativeButton("Non", dialogClickListener).show();

    }

    public void onClickRefuser(View view) {


        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        //Do your Yes progress
                        mr.rejeter(benev, MainBenevoleList.getCurrentOffre()); //A revoir
                        mr.sauvegarderOffre(MainBenevoleList.getCurrentOffre());
                        mr.sauvegarderBenevole(benev);
                        Toast.makeText(getApplicationContext(), "Operation effectuee", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), MainOffreList.class));
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //Do your No progress
                        break;
                }
            }
        };
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        ab.setMessage("Voulez vous vraiment refuser?").setPositiveButton("Oui", dialogClickListener)
                .setNegativeButton("Non", dialogClickListener).show();

    }


    public void onClickRetour(View view) {
            Intent intent = new Intent(this, MainOffreList.class);
            startActivity(intent);
    }

}
