package ca.uottawa.eecs.seg2505.benevolapp;


import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;


import java.util.ArrayList;

import java.util.List;
import java.util.Map;


import ca.uottawa.eecs.seg2505.benevolapp.controlleur.Delegateur;
import ca.uottawa.eecs.seg2505.benevolapp.controlleur.OrganismeControlleur;
import ca.uottawa.eecs.seg2505.benevolapp.db.DBFacade;
import ca.uottawa.eecs.seg2505.benevolapp.db.MemoireFacade;
import ca.uottawa.eecs.seg2505.benevolapp.model.Benevole;
import ca.uottawa.eecs.seg2505.benevolapp.model.offre.EtatBenevoleOffre;
import ca.uottawa.eecs.seg2505.benevolapp.model.offre.Offre;

/**
 * Created by mly083 on 15/10/15.
 */
public class MainBenevoleList  extends AppCompatActivity {

    public ListView lv;
    Context context;

    ArrayList prgmName;
    Map<Benevole,EtatBenevoleOffre> listebenevole;
    //public static String [] prgmNameList={"Benevole 1","Benevole 2","Benevole 3","Benevole 4","Benevole 5","Benevole 6","Benevole 7"};

    public DBFacade mr = new MemoireFacade();
    public Delegateur deleg = Delegateur.getInstance();
    public OrganismeControlleur orgcont;
    public List<Offre> prgmNameList ;

    public static Offre current; // A revoir
    public String [] benevole ;
    public String [] etat;
    public static Offre getCurrentOffre(){
        return current;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_benevole);
        lv=(ListView) findViewById(R.id.listView1);




        Delegateur.setDBFacade(mr);
        orgcont= deleg.getOrganismeControlleur();
        Bundle recdData = getIntent().getExtras();
        String myVal = recdData.getString("organisme_name");


        prgmNameList = orgcont.getOffres(Delegateur.getInstance().getUtilisateurCourant().getCourriel()) ;


        Offre [] array = new Offre[prgmNameList.size()];

        for(int i = 0; i < prgmNameList.size(); i++) array[i]= prgmNameList.get(i);

        int size;
        size=prgmNameList.size()-1;

        while(!array[size].getTitre().equals(myVal) && size>=0){

            size--;
        }
        current=array[size];


        listebenevole = current.getPostulants();

       benevole = new String[listebenevole.size()];
        etat = new String[listebenevole.size()];
        int i = 0;
      for (Map.Entry<Benevole, EtatBenevoleOffre> entry: listebenevole.entrySet()) {
            benevole[i] = entry.getKey().getNom();
          i++;
        }
        int j = 0 ;
        for (Map.Entry<Benevole, EtatBenevoleOffre> entry: listebenevole.entrySet()) {
            etat[j] = entry.getValue().toString();
            j++;
        }



        lv.setAdapter(new VersionAdapter(this));



        lv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                int pos = arg2;

                Intent anotherActivityIntent = new Intent(getApplicationContext(), MainDescriptionBenevole.class);
                anotherActivityIntent.putExtra("benevole_name", benevole[pos]);
                startActivity(anotherActivityIntent);

            }
        });











    }




    class VersionAdapter extends BaseAdapter {

        private LayoutInflater layoutInflater;

        public VersionAdapter(MainBenevoleList activity) {
            // TODO Auto-generated constructor stub
            layoutInflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return listebenevole.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub

            View listItem = convertView;
            int pos = position;
            if (listItem == null) {
                listItem = layoutInflater.inflate(R.layout.item, null);
            }

            // Initialize the views in the layout

            TextView tvTitle = (TextView) listItem.findViewById(R.id.name);
            TextView tvDesc = (TextView) listItem.findViewById(R.id.etat);

            // set the views in the layout

            tvTitle.setText(benevole[pos]);
            tvDesc.setText(etat[pos]);

            return listItem;
        }

    }



}
