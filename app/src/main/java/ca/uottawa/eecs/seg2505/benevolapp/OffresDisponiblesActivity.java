package ca.uottawa.eecs.seg2505.benevolapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class OffresDisponiblesActivity extends AppCompatActivity {

    private Toast notification;
    private List<String> offresDisponibles;
    private ArrayAdapter arrayAdapter;
    private int i;

    @InjectView(R.id.offer_adapter)
    SwipeFlingAdapterView flingContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set le Content au Layout pour Offres Disponibles
        setContentView(R.layout.activity_offres_disponibles_main);

        // Ajout de fonctionnalitées permettant du dévelopement plus rapide
        ButterKnife.inject(this);

        // Définition "hard coded" des offres disponibles, pourrait être fait différement dans le futur
        offresDisponibles = new ArrayList<>();
        offresDisponibles.add("php");
        offresDisponibles.add("c");
        offresDisponibles.add("python");
        offresDisponibles.add("java");
        offresDisponibles.add("html");
        offresDisponibles.add("c++");
        offresDisponibles.add("css");
        offresDisponibles.add("javascript");

        // Adpateur pour créer le layout d'une offre en fonction des objets offres
        arrayAdapter = new ArrayAdapter<>(this, R.layout.activity_offres_disponibles_offre, R.id.helloText, offresDisponibles);

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
                // L'offre a été envoyée vers la gauche
                makeToast(OffresDisponiblesActivity.this, "Offre Ignorée!");
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                // L'offre a été envoyée vers la droite
                makeToast(OffresDisponiblesActivity.this, "Offre Sélectionnée!");
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Sur le point de ne plus avoir d'offre à présemter
                offresDisponibles.add("XML ".concat(String.valueOf(i)));
                arrayAdapter.notifyDataSetChanged();
                Log.d("OFFRES", "Update du adapter!");
                i++;
            }

            @Override
            public void onScroll(float scrollProgressPercent) {
                // Distance de scrolling de l'offre par rapport à sa position originale
                View view = flingContainer.getSelectedView();
                view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
            }
        });

        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                // L'offre a été clickée
                makeToast(OffresDisponiblesActivity.this, "Offre Clickée!");
            }
        });

    }

    private void makeToast(Context ctx, String s){
        if (notification != null) notification.cancel();
        notification = Toast.makeText(ctx, s, Toast.LENGTH_SHORT);
        notification.show();
    }

    @OnClick(R.id.btn_right)
    public void onRight() {
        flingContainer.getTopCardListener().selectRight();
    }

    @OnClick(R.id.btn_left)
    public void onLeft() {
        flingContainer.getTopCardListener().selectLeft();
    }

}
