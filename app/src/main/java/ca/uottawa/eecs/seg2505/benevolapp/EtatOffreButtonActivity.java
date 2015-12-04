package ca.uottawa.eecs.seg2505.benevolapp;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.List;

import ca.uottawa.eecs.seg2505.benevolapp.controlleur.Delegateur;
import ca.uottawa.eecs.seg2505.benevolapp.model.offre.Offre;
import ca.uottawa.eecs.seg2505.benevolapp.offresDisponibles.OffreAdapter;
/**
 * Created by Kevin on 11/30/2015.
 */
public class EtatOffreButtonActivity extends AppCompatActivity{
    private Toast notification;
    private List<Offre> offresDisponibles;
    private ArrayAdapter arrayAdapter;
    private SwipeFlingAdapterView flingContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etat_offres_buttons);
        flingContainer = (SwipeFlingAdapterView) this.findViewById(R.id.adapter_offre);
        offresDisponibles = new ArrayList<>();
        arrayAdapter = new OffreAdapter(this, R.layout.activity_offres_disponibles_offre, offresDisponibles);
        loadOffres();

        flingContainer.setAdapter(arrayAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {


            @Override
            public void removeFirstObjectInAdapter() {
                // Retrait de l'offre traitée
                Log.d("OFFRES", "Offre Retirée!");
                offresDisponibles.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                makeToast(EtatOffreButtonActivity.this, "Refuser!");
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                makeToast(EtatOffreButtonActivity.this, "Accepter!");
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {

            }

            @Override
            public void onScroll(float scrollProgressPercent) {

                View view = flingContainer.getSelectedView();
                view.findViewById(R.id.item_ignore_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                view.findViewById(R.id.item_apply_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
            }
        });



    }

    private void makeToast(Context ctx, String s){
        if (notification != null) notification.cancel();
        notification = Toast.makeText(ctx, s, Toast.LENGTH_SHORT);
        notification.setGravity(Gravity.BOTTOM, 0, 150);
        notification.show();
    }

    private void loadOffres() {
        offresDisponibles.addAll(Delegateur.getInstance().getBenevoleControlleur().getOffreAppliquer());
        arrayAdapter.notifyDataSetChanged();

    }

    public void onAplique(View view) {
        if (offresDisponibles.size() != 0) flingContainer.getTopCardListener().selectRight();
    }

    public void onIgnore(View view) {
        if (offresDisponibles.size() != 0) flingContainer.getTopCardListener().selectLeft();
    }

    public void onReload(View view) {
        loadOffres();
    }
    public void onAccept(View view){
        Delegateur.dbFacade.accepterOffre(Delegateur.dbFacade.getBenevole(Delegateur.utilisateurCourant.getCourriel()),offresDisponibles.get(0));
        offresDisponibles.remove(Delegateur.getInstance().getBenevoleControlleur().getOffreAppliquer().get(0));
        makeToast(EtatOffreButtonActivity.this, "Accepter!");
        finish();
    }

    public void onRefuse(View view){
        Delegateur.dbFacade.refuserOffre(Delegateur.dbFacade.getBenevole(Delegateur.utilisateurCourant.getCourriel()), offresDisponibles.get(0));
        offresDisponibles.remove(Delegateur.getInstance().getBenevoleControlleur().getOffreAppliquer().get(0));
        makeToast(EtatOffreButtonActivity.this, "Refuser!");
        finish();

    }
}
