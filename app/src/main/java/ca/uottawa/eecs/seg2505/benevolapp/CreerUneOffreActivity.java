package ca.uottawa.eecs.seg2505.benevolapp;

import android.app.AlertDialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


import ca.uottawa.eecs.seg2505.benevolapp.controlleur.Delegateur;
import ca.uottawa.eecs.seg2505.benevolapp.controlleur.OrganismeControlleur;
import ca.uottawa.eecs.seg2505.benevolapp.model.Benevole;
import ca.uottawa.eecs.seg2505.benevolapp.model.Disponibilite;
import ca.uottawa.eecs.seg2505.benevolapp.model.JourSemaine;
import ca.uottawa.eecs.seg2505.benevolapp.model.Organisme;
import ca.uottawa.eecs.seg2505.benevolapp.model.offre.Duree;
import ca.uottawa.eecs.seg2505.benevolapp.model.offre.Lieu;
import ca.uottawa.eecs.seg2505.benevolapp.model.offre.Offre;
import ca.uottawa.eecs.seg2505.benevolapp.model.offre.PersonneContact;

public class CreerUneOffreActivity extends AppCompatActivity {

    //class variables
    private Disponibilite disponibilite;

    //instance view and dialog variables
    private EditText mNomPoste;
    private EditText mAddress;
    private EditText mCodePostal;
    private EditText mNombreBenevoles;
    private EditText mDebut;
    private EditText mFin;
    private EditText mTypeActivite;
    private EditText mDureeHeure;
    private EditText mDureeMinute;
    private EditText mDescription;
    private EditText mAgeMinimum;
    private EditText mContactPrenom;
    private EditText mContactNom;
    private EditText mContactEmail;
    private EditText mSkills;
    private EditText mHoraire;
    private boolean isStart;
    private Calendar start, end;

    //Method to help user pick the start and end date
    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            monthOfYear++;
            if (isStart) {
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                year1 = year;
                month1 = monthOfYear;
                day1 = dayOfMonth;
                try {
                    String startDate = (year1)+"/"+(month1 < 10 ? "0"+month1 : month1)+"/"+(day1 < 10 ? "0"+day1 : day1);
                    mDebut.setText(startDate);
                    int d1 = day1+7;
                    String endDate = (year1)+"/"+(month1 < 10 ? "0"+month1 : month1)+"/"+(d1 < 10 ? "0"+d1 : d1);
                    cal.setTime(sdf.parse(startDate));
                    mFin.setText(sdf.format(sdf.parse(endDate)));
                    start = cal;
                    cal.setTime(sdf.parse(endDate));
                    end = cal;
                } catch (ParseException e) {
                    e.printStackTrace();
                    mDebut.setText("Format de date inconnu");
                }
            } else {
                try {
                    Calendar cal = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                    year2 = year;
                    month2 = monthOfYear;
                    day2 = dayOfMonth;
                    String endDate = (year2)+"/"+(month2 < 10 ? "0"+month2 : month2)+"/"+(day2 < 10 ? "0"+day2 : day2);
                    mFin.setText(endDate);
                    cal.setTime(sdf.parse(endDate));
                    end = cal;
                } catch (ParseException e) {
                    e.printStackTrace();
                    mFin.setText("Format de date inconnu");
                }
            }
        }
    };

    private DialogInterface.OnClickListener mPositiveSetListener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            //Stop the activity
            CreerUneOffreActivity.this.finish();
        }

    };

    //other instance variables
    private int day1, month1, year1, day2, month2, year2;

    //List of all skills coupled with booleans indicating whether or not they have been selected
    private List<ListViewSkill> allSkills = new ArrayList<ListViewSkill>();

    //List of all Skills selected
    private ArrayList<String> independantListOfSkills;

    //Static Nested class coupling a skill with a boolean indicating whether or not it has been selected
    protected static class ListViewSkill {
        private String element;
        private boolean isSelected;

        private ListViewSkill(String element, boolean isSelected) {
            this.element=element; this.isSelected=isSelected;
        }

        public String getElement() {
            return element;
        }

        public void setElement(String element) {
            this.element = element;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setIsSelected(boolean isSelected) {
            this.isSelected = isSelected;
        }

        public boolean equals(Object o) {
            if (o == null) return false;
            if (!(o instanceof ListViewSkill) && !(o instanceof String)) return false;

            if (o instanceof String) {
                return element.equals(o);
            }

            ListViewSkill other = (ListViewSkill) o;
            return element.equals(other.element);
        }

        public String toString() { return element; }
    }

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if (Delegateur.dbFacade == null) Delegateur.getInstance();
        for (String skill: Delegateur.getInstance().getOrganismeControlleur().getCompetences()) {
            allSkills.add(new ListViewSkill(skill, false));
        }
        setContentView(R.layout.activity_creer_une_offre);

        if (Delegateur.utilisateurCourant == null || Delegateur.utilisateurCourant instanceof Benevole) { //faire en sorte de créer un organisme pour créer l'offre
            Delegateur.utilisateurCourant = getAppropriateUtilisateur();
        }

        mNomPoste = (EditText) findViewById(R.id.nom);
        mAddress = (EditText) findViewById(R.id.location);
        mCodePostal = (EditText) findViewById(R.id.postalCode);
        mNombreBenevoles = (EditText) findViewById(R.id.nombre);
        mDebut = (EditText) findViewById(R.id.debut);
        mFin = (EditText) findViewById(R.id.fin);
        mTypeActivite = (EditText) findViewById(R.id.type);
        mDescription = (EditText) findViewById(R.id.description);
        mAgeMinimum = (EditText) findViewById(R.id.age);
        mDureeHeure = (EditText) findViewById(R.id.dureeHeure);
        mDureeMinute = (EditText) findViewById(R.id.dureMinute);
        mContactPrenom = (EditText) findViewById(R.id.prenomcontact);
        mContactNom = (EditText) findViewById(R.id.nomcontact);
        mContactEmail = (EditText) findViewById(R.id.emailcontact);
        mSkills = (EditText) findViewById(R.id.skillsField);
        mHoraire = (EditText) findViewById(R.id.horaire_field);
        mHoraire.setInputType(InputType.TYPE_NULL);

        mSkills.setInputType(InputType.TYPE_NULL); //no keyboard allowed here. Will only show a List<?>.toString() representation.
        independantListOfSkills = new ArrayList<>();
        setUpDatePickers();

    }


    public void onCancel(View view) {
        //asks if user really wants to return when back is pressed to avoid unintentionally losing data
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Retourner ?")
                .setMessage("Voulez-vous vraiment retourner en arrière ? Les informations entrées ne seront pas sauvegardées.")
                .setPositiveButton("Oui", mPositiveSetListener)
                .setNegativeButton("Non", null)
                .show();
    }

    //asks if user really wants to return when back is pressed to avoid unintentionally losing data
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Retourner ?")
                .setMessage("Voulez-vous vraiment retourner en arrière ? Les informations entrées ne seront pas sauvegardées.")
                .setPositiveButton("Oui", mPositiveSetListener)
                .setNegativeButton("Non", null)
                .show();
    }

    //Launches the ActivityAvailability2 activity to enable user to choose his on what day he is available
    public void onHoraire(View view) {
        int REQUEST_CODE = 1;

        Intent intent = new Intent(this, ActivityAvailibility2.class);
        if (disponibilite != null){
            intent.putExtra("jourSemaine", disponibilite.getJour().toString());
            intent.putExtra("isSoir", disponibilite.isSoir());}

        startActivityForResult(intent, REQUEST_CODE);
    }

    //Handles the result from the ActivityAvailability2 call
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode == 1) {
            if (requestCode == 1) {
                String jourSemaine = data.getStringExtra("jourSemaine");
                boolean isSoir = data.getBooleanExtra("isSoir", false);
                if (jourSemaine!= null && !jourSemaine.isEmpty()){
                    disponibilite = new Disponibilite(JourSemaine.valueOf(jourSemaine), isSoir);
                    mHoraire.setText(disponibilite.getJour()+(disponibilite.isSoir() ? " soir": " matin"));
                }
                else{
                    Toast.makeText(this, "Il y a eu un problème lors de la sélection de l'horraire", Toast.LENGTH_SHORT).show();}
            }
        }
    }


    public void onConfirm(View view) {

        String message = "L'offre a été créée avec succès";
        try {
            String nom = mNomPoste.getText().toString();
            String location = mAddress.getText().toString();
            String codePostal = mCodePostal.getText().toString();
            String debut = mDebut.getText().toString();
            String fin = mFin.getText().toString();
            String type = mTypeActivite.getText().toString();
            String nombre = mNombreBenevoles.getText().toString();


            if (nom.length() == 0 || location.length() == 0 || codePostal.isEmpty() || debut.length() == 0 || fin.length() == 0
                    || type.length() == 0 || nombre.length() == 0 || disponibilite == null) {
                throw new NullPointerException("Vous devez compléter les champs obligatoires marqués d'un (*).");
            }

            if (!codePostal.isEmpty()) {
                if (!isPostalCode(codePostal)) throw new PostalCodeException(codePostal);
            } else {
                throw new PostalCodeException("<Raison : Aucun code postal entré>");
            }

            int nombreInt = 1;

            if (nombre.length() != 0) {
                nombreInt = Integer.parseInt(nombre);
            }

            Offre volunteeringOffer = new Offre(nom, type, start, end, nombreInt, new Lieu(location, codePostal), disponibilite, Delegateur.getInstance().getOrganismeControlleur().getOrganisme(Delegateur.getInstance().getUtilisateurCourant().getCourriel()));

            String theAge = mAgeMinimum.getText().toString();

            if (theAge.length() != 0) {
                volunteeringOffer.setAgeMinimum(Integer.parseInt(theAge));
            }

            String dureeHeure = mDureeHeure.getText().toString();
            String dureeMinute = mDureeMinute.getText().toString();
            int dureeHeureInt;
            int dureeMinuteInt;

            if(!dureeHeure.isEmpty() || !dureeMinute.isEmpty()){
                volunteeringOffer.setDuree(new Duree(Integer.parseInt(dureeHeure), Integer.parseInt(dureeMinute)));
            }

            String description = mDescription.getText().toString();
            if (description != null && !description.isEmpty()) {
                volunteeringOffer.setDescription(description);
            }

            String contactNom = mContactNom.getText().toString();
            String contactPrenom = mContactPrenom.getText().toString();
            String contactEmail = mContactEmail.getText().toString();

            if (!contactEmail.isEmpty()){
                volunteeringOffer.setPersonneContact(new PersonneContact(contactEmail, contactNom, contactPrenom));
            }

            try {
                for (String competence : independantListOfSkills) {
                    if (competence != null) volunteeringOffer.addCompetence(competence);
                }
            } catch (NullPointerException e) {
                volunteeringOffer.addCompetences(independantListOfSkills);
            }

            finish();

        } catch (NullPointerException e) {
            message = e.getMessage();
            e.printStackTrace();
        } catch (NumberFormatException e) {
            message = "Un caractère entré aurait du être un nombre. Cause : "+e.getMessage();
        } catch (PostalCodeException e) {
            message = e.getMessage();
        } catch (IllegalArgumentException e) {
            message = "Un caractère entré est invalide. Cause : "+e.getMessage();
        } catch (Exception e) {
            message = "Il y a eu une erreur interne avec l'application. Cause : "+e.getMessage();
            finish();
        } finally {
            Toast.makeText(getBaseContext(), message, Toast.LENGTH_LONG).show();
        }


    }

    //Opens a dialog to let a user enter a new skill.
    //If the skill isn't currently selected for the offer, it will be added to the offers list of skills
    //If the skill doesn't exist in the global list of skills, it will also be added there for other users to pick it
    public void newSkillDialog(View view) {
        final EditText newSkill = new EditText(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Entrer le nom de la compétence que vous voulez ajouter à la liste des compétences.");
        builder.setView(newSkill);
        builder.setPositiveButton("Confirmer", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                if (!Delegateur.getInstance().getOrganismeControlleur().getCompetences().contains(newSkill.getText().toString())) {
                    Delegateur.getInstance().getOrganismeControlleur().ajoutCompetence((newSkill.getText().toString()));
                    allSkills.add(new ListViewSkill(newSkill.getText().toString(), true));
                    independantListOfSkills.add(newSkill.getText().toString());

                    StringBuffer sb = new StringBuffer();
                    for (int i = 0; i<independantListOfSkills.size(); i++) {
                        if (i == 0) sb.append(independantListOfSkills.get(i));
                        else sb.append(", "+independantListOfSkills.get(i));
                    }
                    mSkills.setText(sb.toString());

                } else {
                    if (independantListOfSkills.contains(newSkill.getText().toString())){
                        showMessage("Existe déja.",
                                "La compétence existe déja et a déja été ajouté à la liste de compétence pour cette offre. Essayez-en une autre.");
                    } else{
                        showMessage("Existe déjà", "La compétence entrée existe déjà et a été associé à l'offre.");
                        StringBuffer sb = new StringBuffer();
                        for (int i = 0; i<independantListOfSkills.size(); i++) {
                            if (i == 0) sb.append(independantListOfSkills.get(i));
                            else sb.append(", "+independantListOfSkills.get(i));
                        }
                        mSkills.setText(sb.toString());
                    }
                }
            }
        });
        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                dialog.cancel();
            }
        });

        builder.show();
    }

    //Lets the user pick his skils amongst the global list of skills. The skills he already selected will be highlighted
    public void openSkillsDialog(View view){

        final List<ListViewSkill> tmpSelectedSkills = allSkills.subList(0, allSkills.size());

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View convertView = (View) inflater.inflate(R.layout.custom, null);
        alertDialog.setView(convertView);
        alertDialog.setTitle("Sélectioner les compétences associées au poste");

        OrganismeControlleur organismeControlleur = Delegateur.getInstance().getOrganismeControlleur();

        ListView lv = (ListView) convertView.findViewById(R.id.skillListListView);
        List<String> elementsOfAllSkills = new ArrayList<String>();
        for (ListViewSkill s : allSkills) {
            elementsOfAllSkills.add(s.getElement());
        }
        SkillAdapter adapter = new SkillAdapter(this, android.R.layout.simple_list_item_1, elementsOfAllSkills, tmpSelectedSkills);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (!tmpSelectedSkills.get(position).isSelected()) {
                    tmpSelectedSkills.get(position).setIsSelected(true);
                    view.setBackgroundColor(Color.parseColor("#A9BCF5"));
                    independantListOfSkills.add(tmpSelectedSkills.get(position).toString());
                } else {
                    tmpSelectedSkills.get(position).setIsSelected(false);
                    view.setBackgroundColor(Color.parseColor("#ffffff"));
                    independantListOfSkills.remove(tmpSelectedSkills.get(position).toString());
                }
            }

        });
        alertDialog.setPositiveButton("Confirmer", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                allSkills=tmpSelectedSkills;
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i<independantListOfSkills.size(); i++) {
                    if (i == 0) sb.append(independantListOfSkills.get(i));
                    else sb.append(", "+independantListOfSkills.get(i));
                }
                mSkills.setText(sb.toString());
            }
        });
        alertDialog.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                dialog.cancel();
            }
        });

        alertDialog.show();
    }

    // Intermediary for the call to the correct method to change the start or end date
    protected Dialog onCreateDialog(int id) {
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DATE);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);
        switch (id) {
            case 0: return new DatePickerDialog(this, mDateSetListener, year, month, day);
            case 1: return new DatePickerDialog(this, mDateSetListener, year, month, (day+7));
            default: return null;
        }
    }

    //Sets up the date pickers
    private void setUpDatePickers() {

        mDebut.setInputType(InputType.TYPE_NULL);
        mDebut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(0);
            }
        });

        mDebut.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    isStart = true;
                    showDialog(0);
                }
            }
        });

        mFin.setInputType(InputType.TYPE_NULL);
        mFin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(1);
            }
        });

        mFin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    isStart = false;
                    showDialog(1);
                }
            }
        });
    }

    //Creates a dialog to show a confirmation message to the user
    private void showMessage(String titre, String message) {
        new AlertDialog.Builder(CreerUneOffreActivity.this)
                .setTitle(titre)
                .setMessage(message)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private boolean isPostalCode(String postalCode) {
        if (postalCode != null && (postalCode.length() == 6 || postalCode.length() == 7)) {
            if (postalCode.length() == 6) {
                char[] seq = postalCode.toCharArray();
                try { //détermine si ce sont des chiffres
                    Integer.parseInt(String.valueOf(seq[1]));
                    Integer.parseInt(String.valueOf(seq[3]));
                    Integer.parseInt(String.valueOf(seq[5]));
                } catch (NumberFormatException e) { return false; }
                return isLetter(seq[0]) && isLetter(seq[2]) && isLetter(seq[4]); //détermine si ce sont des lettres
            } else {
                char[] seq = postalCode.toCharArray();
                try { //détermine si ce sont des chiffres
                    Integer.parseInt(String.valueOf(seq[1]));
                    Integer.parseInt(String.valueOf(seq[4]));
                    Integer.parseInt(String.valueOf(seq[6]));
                } catch (NumberFormatException e) { return false; }
                return isLetter(seq[0]) && isLetter(seq[2]) && isLetter(seq[5]) && (int) seq[5] == 32; //détermine si ce sont des lettres et un espace
            }
        }
        return false;
    }

    private boolean isLetter(char c) { return ((int) c >= 65 && (int) c <= 90) || ((int) c >= 97 && (int) c <= 122); }

    private class PostalCodeException extends IllegalArgumentException {
        public PostalCodeException(String code) {
            super("Le code postal entré est invalide : "+code);
        }
    }

    private Organisme getAppropriateUtilisateur() {
        Organisme org3 = new Organisme("Parent Resource Centre",
                "300",
                "Goulburn Private",
                "",
                "K1N 1C9",
                "Ottawa",
                "613-565-2467 x229",
                "http://www.parentresource.ca",
                "rh@parentresource.ca",
                "As a children’s charity, the Parent Resource Centre (PRC) is a leader in family support resource programs and comprehensive training for professionals. With a focus on early learning, parent support, training, research and community capacity, PRC aims to have a lasting impact on children’s developmental outcomes.",
                "Moyenne",
                "Aide parentale",
                "");
        Offre o1 = new Offre("Titre1", "Type1", Calendar.getInstance(), Calendar.getInstance(), 1,
                new Lieu("Ville1", "CP1"), new Disponibilite(JourSemaine.Lundi, true), org3);
        Offre o2 = new Offre("Titre2", "Type2", Calendar.getInstance(), Calendar.getInstance(), 2,
                new Lieu("Ville2", "CP2"), new Disponibilite(JourSemaine.Mardi, true), org3);
        Offre o3 = new Offre("Titre3", "Type3", Calendar.getInstance(), Calendar.getInstance(), 3,
                new Lieu("Ville3", "CP3"), new Disponibilite(JourSemaine.Mercredi, true), org3);
        org3.addOffres(o1);
        org3.addOffres(o2);
        org3.addOffres(o3);

        return org3;
    }
}
