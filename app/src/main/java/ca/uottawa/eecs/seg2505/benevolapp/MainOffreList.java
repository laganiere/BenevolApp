package ca.uottawa.eecs.seg2505.benevolapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.List;
import android.widget.Toast;

import java.util.ArrayList;

import ca.uottawa.eecs.seg2505.benevolapp.controlleur.Delegateur;
import ca.uottawa.eecs.seg2505.benevolapp.controlleur.OrganismeControlleur;
import ca.uottawa.eecs.seg2505.benevolapp.db.DBFacade;
import ca.uottawa.eecs.seg2505.benevolapp.db.MemoireFacade;
import ca.uottawa.eecs.seg2505.benevolapp.db.MemoireFacadeRemplissage;
import ca.uottawa.eecs.seg2505.benevolapp.model.Organisme;
import ca.uottawa.eecs.seg2505.benevolapp.model.offre.Offre;

/**
 * Created by mly083 on 15/10/15.
 */
public class MainOffreList  extends AppCompatActivity {

    ListView lv;
    Context context;

    ArrayList prgmName;

    public Delegateur deleg = Delegateur.getInstance();
    public DBFacade mr = new MemoireFacade();
    public OrganismeControlleur orgcont;
    public List<Offre> prgmNameList ;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offre);
        lv=(ListView) findViewById(R.id.listView1);
        Delegateur.setDBFacade(mr);
        orgcont=deleg.getOrganismeControlleur();
        prgmNameList = orgcont.getOffres(Delegateur.getInstance().getUtilisateurCourant().getCourriel()) ;



        String [] array = new String[prgmNameList.size()];
        for(int i = 0; i < prgmNameList.size(); i++) array[i]= prgmNameList.get(i).getTitre();


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, array);

        // on assigne l'adapter à notre list
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                int itemPosition = position;

                // On récupère le texte de l'item cliqué
                String itemValue = (String) lv
                        .getItemAtPosition(position);

                Intent anotherActivityIntent = new Intent(getApplicationContext(),  MainBenevoleList.class);
                anotherActivityIntent.putExtra("organisme_name",itemValue);
                startActivity(anotherActivityIntent);
            }

        });



    }


}
