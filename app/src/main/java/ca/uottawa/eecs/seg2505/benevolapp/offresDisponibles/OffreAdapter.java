package ca.uottawa.eecs.seg2505.benevolapp.offresDisponibles;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

import ca.uottawa.eecs.seg2505.benevolapp.R;
import ca.uottawa.eecs.seg2505.benevolapp.model.Disponibilite;
import ca.uottawa.eecs.seg2505.benevolapp.model.Mois;
import ca.uottawa.eecs.seg2505.benevolapp.model.offre.Duree;
import ca.uottawa.eecs.seg2505.benevolapp.model.offre.Lieu;
import ca.uottawa.eecs.seg2505.benevolapp.model.offre.Offre;
import ca.uottawa.eecs.seg2505.benevolapp.model.offre.PersonneContact;

public class OffreAdapter extends ArrayAdapter<Offre> {

    private Context context;
    private int viewID;

    public OffreAdapter(Context context, int viewID, List<Offre> offres) {
        super(context, viewID, offres);
        this.viewID = viewID;
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent){

        // Inflate le layout de l'offre si nécessaire
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(viewID, parent, false);
        }

        Offre offre = getItem(position);

        if (offre != null) {

            // Les valeurs venant de l'offre à mettre dans le layout
            String titre = offre.getTitre();
            String description = offre.getDescription();
            String organisme = offre.getOrganisme().getName();
            PersonneContact personne = offre.getPersonneContact();
            Lieu lieu = offre.getLieu();
            Disponibilite horaire = offre.getDisponibilite();
            Duree duree = offre.getDuree();
            Calendar dateDebut = offre.getDateDebut();
            Calendar dateFin = offre.getDateFin();
            int ageMin = offre.getAgeMinimum();
            int places = offre.getNombrePlaces();
            List<String> competences = offre.getCompetences();
            String typeActivite = offre.getType();

            // Les views à changer dans le layout offre
            TextView titreView = (TextView) convertView.findViewById(R.id.lbl_titleValue);
            TextView descriptionView = (TextView) convertView.findViewById(R.id.lbl_descriptionValue);
            TextView organismeView = (TextView) convertView.findViewById(R.id.lbl_organismeName);
            TextView personneNameView = (TextView) convertView.findViewById(R.id.lbl_personneName);
            TextView personneEmailView = (TextView) convertView.findViewById(R.id.lbl_personneEmail);
            TableRow lieuView = (TableRow) convertView.findViewById(R.id.row_lieu);
            TableRow horaireView = (TableRow) convertView.findViewById(R.id.row_horaire);
            TableRow dureeView = (TableRow) convertView.findViewById(R.id.row_duree);
            TableRow dateDebutView = (TableRow) convertView.findViewById(R.id.row_dateDebut);
            TableRow dateFinView = (TableRow) convertView.findViewById(R.id.row_dateFin);
            TableRow ageMinView = (TableRow) convertView.findViewById(R.id.row_ageMin);
            TableRow placesView = (TableRow) convertView.findViewById(R.id.row_places);
            TableRow competencesView = (TableRow) convertView.findViewById(R.id.row_competences);
            TableRow typeActiviteView = (TableRow) convertView.findViewById(R.id.row_typeActivite);

            // Changement des valeurs du layout
            titreView.setText(titre);
            if (description != null) descriptionView.setText(description);
            else descriptionView.setHeight(0);
            organismeView.setText(organisme);
            if (personne != null) {
                personneNameView.setText(personne.getFullName());
                personneEmailView.setText(personne.getEmail());
            } else {
                personneNameView.setText(offre.getOrganisme().getEmail());
                personneEmailView.setVisibility(View.GONE);
            }
            if (lieu != null) ((TextView) lieuView.getChildAt(1)).setText(lieu.toString());
            else lieuView.setVisibility(View.GONE);
            if (horaire != null) ((TextView) horaireView.getChildAt(1)).setText(horaire.toString());
            else horaireView.setVisibility(View.GONE);
            if (duree != null) ((TextView) dureeView.getChildAt(1)).setText(duree.toString());
            else dureeView.setVisibility(View.GONE);
            if (dateDebut != null) ((TextView) dateDebutView.getChildAt(1)).setText(dateDebut.get(Calendar.DAY_OF_MONTH) + " " + Mois.get(dateDebut.get(Calendar.MONTH)) + " " + dateDebut.get(Calendar.YEAR));
            else dateDebutView.setVisibility(View.GONE);
            if (dateFin != null) ((TextView) dateFinView.getChildAt(1)).setText(dateFin.get(Calendar.DAY_OF_MONTH) + " " + Mois.get(dateFin.get(Calendar.MONTH)) + " " + dateFin.get(Calendar.YEAR));
            else dateFinView.setVisibility(View.GONE);
            if (ageMin > 0) ((TextView) ageMinView.getChildAt(1)).setText(ageMin + " an" + ((ageMin > 1) ? "s" : ""));
            else ageMinView.setVisibility(View.GONE);
            if (places > 0) ((TextView) placesView.getChildAt(1)).setText(places + " place" + ((places > 1) ? "s" : ""));
            else placesView.setVisibility(View.GONE);
            if (competences.size() > 0) {
                String competencesString = competences.get(0);
                for (int i = 1; i < competences.size(); i++) competencesString += ", " + competences.get(i);
                ((TextView) competencesView.getChildAt(1)).setText(competencesString);
            } else competencesView.setVisibility(View.GONE);
            if (typeActivite != null) ((TextView) typeActiviteView.getChildAt(1)).setText(typeActivite);
            else typeActiviteView.setVisibility(View.GONE);

        }

        // Retourne le view pour l'offre courante
        return convertView;
    }



}
