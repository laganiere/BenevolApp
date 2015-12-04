package ca.uottawa.eecs.seg2505.benevolapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ca.uottawa.eecs.seg2505.benevolapp.controlleur.Delegateur;
import ca.uottawa.eecs.seg2505.benevolapp.db.DBFacade;
import ca.uottawa.eecs.seg2505.benevolapp.db.MemoireFacade;
import ca.uottawa.eecs.seg2505.benevolapp.model.Benevole;
import ca.uottawa.eecs.seg2505.benevolapp.model.Disponibilite;
import ca.uottawa.eecs.seg2505.benevolapp.model.JourSemaine;
import ca.uottawa.eecs.seg2505.benevolapp.model.Utilisateur;

public class DisponibiliteActivity extends AppCompatActivity {
    //Creation du Spinner pour les competences
    Spinner spinnerChoixCompetences = null;

    //Initialisation du EditTexte des intérêts
    EditText editTexteInterets = null;

    //Initialisation du Texte des compétences
    Spinner spinnerCompetencesBenevole = null;

    //Initialisation de l'adapter pour les compétences du bénévole
    ArrayAdapter<String> adapterCompetence = null;

    //Initialisation des checkBox
    CheckBox checklundiJour = null;
    CheckBox checklundiSoir = null;
    CheckBox checkMardiJour = null;
    CheckBox checkMardiSoir = null;
    CheckBox checkMercrediJour = null;
    CheckBox checkMercrediSoir = null;
    CheckBox checkJeudiJour = null;
    CheckBox checkJeudiSoir = null;
    CheckBox checkVendrediJour = null;
    CheckBox checkVendrediSoir = null;
    CheckBox checkSamediJour = null;
    CheckBox checkSamediSoir = null;
    CheckBox checkDimancheJour = null;
    CheckBox checkDimancheSoir = null;

    //Obtention du benevole qui utilise l'application
    Benevole benevoleCourant = null;

    //Objet de la classe Disponibilite
    List<Disponibilite> dispo = null;

    //Liste des compétences du bénévoles

    //Test githubApp
    List<String> competence = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disponibilite);

        //Courriel de l'utilisateur courant
        String courriel = Delegateur.getInstance().getUtilisateurCourant().getCourriel();

        //Recherche du bénévole qui utilise l'application
        benevoleCourant = Delegateur.getInstance().getBenevoleControlleur().getBenevole(courriel);

        //Recherche de la liste des disponibilités du bénévole
        dispo = benevoleCourant.getHoraire();

        //Définition du bloc EditText des intérêts
        editTexteInterets = (EditText) findViewById(R.id.editInterets);

        //Recherche des compétences du bénévole
        competence = benevoleCourant.getCompetences();

        //Définition du spinner pour les compétences du bénévole
        spinnerCompetencesBenevole = (Spinner) findViewById(R.id.spinnerCompetencesBenevole);

        //Définition de l'adapter pour les compétences du bénévole
        adapterCompetence = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,competence);
        spinnerCompetencesBenevole.setAdapter(adapterCompetence);


        //Appelle à la méthode qui modifie les compétences du bénévole
        updateCompetence();

        //Appelle a la methode qui modifie le spinner
        updateSpinner();

        //Appelle à la méthode qui entre le texte pour les intérêts
        updateInterets();

        //Appelle à la méthode qui modifie les disponibilités
        updateDispo();

    }

    private void updateCompetence() {
        //Définition du Spinner des Compétences du bénévole
        spinnerCompetencesBenevole = (Spinner) findViewById(R.id.spinnerCompetencesBenevole);

        adapterCompetence = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,competence);
        adapterCompetence.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCompetencesBenevole.setAdapter(adapterCompetence);

    }

    private void updateDispo() {
        checklundiJour = (CheckBox) findViewById(R.id.checkBoxLundiJour);
        checklundiSoir = (CheckBox) findViewById(R.id.checkBoxLundiSoir);
        checkMardiJour = (CheckBox) findViewById(R.id.checkBoxMardiJour);
        checkMardiSoir = (CheckBox) findViewById(R.id.checkBoxMardiSoir);
        checkMercrediJour = (CheckBox) findViewById(R.id.checkBoxMercrediJour);
        checkMercrediSoir = (CheckBox) findViewById(R.id.checkBoxMercrediSoir);
        checkJeudiJour = (CheckBox) findViewById(R.id.checkBoxJeudiJour);
        checkJeudiSoir = (CheckBox) findViewById(R.id.checkBoxJeudiSoir);
        checkVendrediJour = (CheckBox) findViewById(R.id.checkBoxVendrediJour);
        checkVendrediSoir = (CheckBox) findViewById(R.id.checkBoxVendrediSoir);
        checkSamediJour = (CheckBox) findViewById(R.id.checkBoxSamediJour);
        checkSamediSoir = (CheckBox) findViewById(R.id.checkBoxSamediSoir);
        checkDimancheJour = (CheckBox) findViewById(R.id.checkBoxDimancheJour);
        checkDimancheSoir = (CheckBox) findViewById(R.id.checkBoxDimancheSoir);

        //Modification des checkBoxs Disponibilite

        for(int i = 0; i < dispo.size(); i++){

            if (dispo.get(i).getJour().equals(JourSemaine.Lundi) && !dispo.get(i).isSoir())
                checklundiJour.setChecked(true);
            if (dispo.get(i).getJour().equals(JourSemaine.Lundi) && dispo.get(i).isSoir())
                checklundiSoir.setChecked(true);

            if (dispo.get(i).getJour().equals(JourSemaine.Mardi) && !dispo.get(i).isSoir())
                checkMardiJour.setChecked(true);
            if (dispo.get(i).getJour().equals(JourSemaine.Mardi) && dispo.get(i).isSoir())
                checkMardiSoir.setChecked(true);

            if (dispo.get(i).getJour().equals(JourSemaine.Mercredi) && !dispo.get(i).isSoir())
                checkMercrediJour.setChecked(true);
            if (dispo.get(i).getJour().equals(JourSemaine.Mercredi) && dispo.get(i).isSoir())
                checkMercrediSoir.setChecked(true);

            if (dispo.get(i).getJour().equals(JourSemaine.Jeudi) && !dispo.get(i).isSoir())
                checkJeudiJour.setChecked(true);
            if (dispo.get(i).getJour().equals(JourSemaine.Jeudi) && dispo.get(i).isSoir())
                checkJeudiSoir.setChecked(true);

            if (dispo.get(i).getJour().equals(JourSemaine.Vendredi) && !dispo.get(i).isSoir())
                checkVendrediJour.setChecked(true);
            if (dispo.get(i).getJour().equals(JourSemaine.Vendredi) && dispo.get(i).isSoir())
                checkVendrediSoir.setChecked(true);

            if (dispo.get(i).getJour().equals(JourSemaine.Samedi) && !dispo.get(i).isSoir())
                checkSamediJour.setChecked(true);
            if (dispo.get(i).getJour().equals(JourSemaine.Samedi) && dispo.get(i).isSoir())
                checkSamediSoir.setChecked(true);

            if (dispo.get(i).getJour().equals(JourSemaine.Dimanche) && !dispo.get(i).isSoir())
                checkDimancheJour.setChecked(true);
            if (dispo.get(i).getJour().equals(JourSemaine.Dimanche) && dispo.get(i).isSoir())
                checkDimancheSoir.setChecked(true);
        }

    }

    private void updateInterets() {

        editTexteInterets.setText(benevoleCourant.getDomaineInterets());


    }

    private void updateSpinner(){
        //Definition du Spinner des competences
        spinnerChoixCompetences = (Spinner)findViewById(R.id.spinnerCompetences);

        //Creation de l'objet MemoireFacade pour obtenir les competences disponibles
        MemoireFacade memoire = new MemoireFacade();

        //Liste des competences disponibles
        List<String> items = memoire.getCompetences();

        //Adapter pour mettre les competences dans le Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerChoixCompetences.setAdapter(adapter);





    }
    public void onAjouterCompetence(View view){
        String item = spinnerChoixCompetences.getSelectedItem().toString();
        if(competence.contains(item))
             competence.remove(item);
        else
            competence.add(item);
        updateCompetence();
    }
    public void onSauvegarde(View view){
        //Sauvegarde des intérêts
        String domaineInterets = editTexteInterets.getText().toString();
        benevoleCourant.setDomaineInterets(domaineInterets);
        finish();
    }

    public void onCancel(View view) {
        finish();
    }
    public void onCheckLundiJour(View view){
        if(checklundiJour.isChecked())
            dispo.add(new Disponibilite(JourSemaine.Lundi, false));
    }
    public void onCheckLundiSoir(View view){
        if(checklundiSoir.isChecked())
            dispo.add(new Disponibilite(JourSemaine.Lundi, true));
    }
    public void onCheckMardiJour(View view){
        if(checkMardiJour.isChecked())
            dispo.add(new Disponibilite(JourSemaine.Mardi, false));
    }
    public void onCheckMardiSoir(View view){
        if(checkMardiSoir.isChecked())
            dispo.add(new Disponibilite(JourSemaine.Mardi, true));
    }
    public void onCheckMercrediJour(View view){
        if(checkMercrediJour.isChecked())
            dispo.add(new Disponibilite(JourSemaine.Mercredi, false));
    }
    public void onCheckMercrediSoir(View view){
        if(checkMercrediSoir.isChecked())
            dispo.add(new Disponibilite(JourSemaine.Mercredi, true));
    }
    public void onCheckJeudiJour(View view){
        if(checkJeudiJour.isChecked())
            dispo.add(new Disponibilite(JourSemaine.Jeudi, false));
    }
    public void onCheckJeudiSoir(View view){
        if(checkJeudiSoir.isChecked())
            dispo.add(new Disponibilite(JourSemaine.Jeudi, true));
    }
    public void onCheckVendrediJour(View view){
        if(checkVendrediJour.isChecked())
            dispo.add(new Disponibilite(JourSemaine.Vendredi, false));
    }
    public void onCheckVendrediSoir(View view){
        if(checkVendrediSoir.isChecked())
            dispo.add(new Disponibilite(JourSemaine.Vendredi, true));
    }
    public void onCheckSamediJour(View view){
        if(checkSamediJour.isChecked())
            dispo.add(new Disponibilite(JourSemaine.Samedi, false));
    }
    public void onCheckSamediSoir(View view){
        if(checkSamediSoir.isChecked())
            dispo.add(new Disponibilite(JourSemaine.Samedi, true));
    }
    public void onCheckDimancheJour(View view){
        if(checkDimancheJour.isChecked())
            dispo.add(new Disponibilite(JourSemaine.Dimanche, false));
    }
    public void onCheckDimancheSoir(View view){
        if(checkDimancheSoir.isChecked())
            dispo.add(new Disponibilite(JourSemaine.Dimanche, true));
    }




}
