package ca.uottawa.eecs.seg2505.benevolapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import ca.uottawa.eecs.seg2505.benevolapp.controlleur.Delegateur;
import ca.uottawa.eecs.seg2505.benevolapp.db.DBFacade;
import ca.uottawa.eecs.seg2505.benevolapp.model.Organisme;
import ca.uottawa.eecs.seg2505.benevolapp.db.MemoireFacade;

/**
 * Created by alexandre on 2015-11-20.
 *
 * On assume que l'organisme à modifié a déjà été créé.
 */
public class ModifOrganismeActivity extends AppCompatActivity {
    //C'est assumé qu'un organisme est l'utilisateur courant.

    //Variables privées permettant le get et le set de l'information entrée.
    private EditText nom;
    private EditText numeroPorte;
    private EditText nomRue;
    private EditText numeroAppartement;
    private EditText ville;
    private EditText codePostal ;
    private EditText telephone;
    private EditText siteWeb;
    private EditText secteur;
    private EditText tailleOrganisme;
    private EditText description;

    //L'organisme courant
    private Organisme organismeCourant;
    //L'organisme modifié par l'utilisateur.
    private Organisme nouveauOrganisme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //SUPER CLASSE ONCREATE
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modif_organisme);

        //CRÉATIONN DES VARIABLES QUI MODIFIE LE TEXT
        this.nom = (EditText)findViewById(R.id.editNom);
        this.numeroAppartement = (EditText)findViewById(R.id.editNumeroAppartement);
        this.numeroPorte = (EditText)findViewById(R.id.editNumeroPorte);
        this.nomRue = (EditText)findViewById(R.id.editNomRue);
        this.codePostal = (EditText)findViewById(R.id.editPostal);
        this.ville = (EditText)findViewById(R.id.editVille);
        this.telephone = (EditText)findViewById(R.id.editTelephone);
        this.siteWeb = (EditText)findViewById(R.id.editSiteWeb);
        this.secteur = (EditText)findViewById(R.id.editSecteur);
        this.tailleOrganisme = (EditText)findViewById(R.id.editTailleOrg);
        this.description = (EditText)findViewById(R.id.editDescription);

        //INITIALISATION DES VARIABLES ORGANISME
        this.organismeCourant = (Organisme) Delegateur.utilisateurCourant;
        this.nouveauOrganisme = new Organisme();

        //INFORMATION IMMUABLE
        this.nouveauOrganisme.setCourriel(this.organismeCourant.getCourriel());
        this.nouveauOrganisme.setFondateur(this.organismeCourant.getFondateur());

        // INFORMATIONS OBLIGATOIRE, NE PEUVENT PAS ETRE NULL
        this.nom.setText(organismeCourant.getNom(), TextView.BufferType.EDITABLE);
        this.numeroPorte.setText(organismeCourant.getNumeroPorte());
        this.nomRue.setText(organismeCourant.getNomRue());
        this.ville.setText(organismeCourant.getVille());
        this.codePostal.setText(organismeCourant.getCodePostal());

        // INFORMATIONS FALCULTATIVES, PEUVENT ETRE NULL, ESPACE VIDE SI OUI
        this.conditionalSetter(this.numeroAppartement, organismeCourant.getNumeroAppartement());
        this.conditionalSetter(this.telephone, organismeCourant.getNumeroTelephone());
        this.conditionalSetter(this.siteWeb, organismeCourant.getSiteWeb());
        this.conditionalSetter(this.secteur, organismeCourant.getSecteurActivite());
        this.conditionalSetter(this.tailleOrganisme, organismeCourant.getTailleOrganisation());
        this.conditionalSetter(this.description, organismeCourant.getDescription());
    }

    public void onSendModCompte(View view){
        //Envoyé l'information et remplacé l'organisme modifié.
        //Il manque des attributs et des sets dans organisme.
        //INFORRMATION OBLIGATOIRE: NE PEUVENT PAS ÊTRE NULL
        EditText[] champsObligatoire= {nom, nomRue, codePostal, numeroPorte, ville};
        if (this.verifieInformationObligatoire(champsObligatoire)) {
            //INFORMATION OBLIGATOIRE.
            nouveauOrganisme.setNom(nom.getText().toString());
            nouveauOrganisme.setNomRue(nomRue.getText().toString());
            nouveauOrganisme.setNumeroPorte(numeroPorte.getText().toString());
            nouveauOrganisme.setVille(ville.getText().toString());
            nouveauOrganisme.setCodePostal(codePostal.getText().toString());

            //INFORMATIONS FALCULTATIVES, PEUVENT ÊTRE NULL
            if (numeroAppartement.getText().toString().trim().length() > 0)
                nouveauOrganisme.setNumeroAppartement(numeroAppartement.getText().toString());
            if (telephone.getText().toString().trim().length() > 0)
                nouveauOrganisme.setNumeroTelephone(telephone.getText().toString());
            if (siteWeb.getText().toString().trim().length() > 0)
                nouveauOrganisme.setSiteWeb(siteWeb.getText().toString());
            if (secteur.getText().toString().trim().length() > 0)
                nouveauOrganisme.setSecteurActivite(secteur.getText().toString());
            if (tailleOrganisme.getText().toString().trim().length() > 0)
                nouveauOrganisme.setTailleOrganisation(tailleOrganisme.getText().toString());
            if (description.getText().toString().trim().length() > 0)
                nouveauOrganisme.setDescription(description.getText().toString());

            //REMPLACE UTILISATEUR COURANT DANS LA BASE DE DONNÉE.
            //SUPPOSE L'EXISTANCE DE LA FAÇADE: (TEMPORAIREMENT EN COMMENTAIRE)*****************************************************
            //Delegateur.dbFacade.sauvegarderOrganisme(nouveauOrganisme);

            //MODIFIE UTILISATEUR COURANT.
            Delegateur.utilisateurCourant = nouveauOrganisme;

            this.popUp("L'enregistrement a été un succès!" ,
                    "OK.",
                    "BenevolApp");
        } else {
            this.popUp("De l'information obligatoire manque! Le sauvegarde a échoué!"
                    + verifyFalseID(champsObligatoire),
                    "OK.",
                    "BenevolApp");
        }
    }

    public void onClickBack(View view) {
        finish();
    }

    private void conditionalSetter(EditText editText, String value){
        if(value != null)
            editText.setText(value);
        else
            editText.setText("");
    }

    private boolean verifieInformationObligatoire(EditText[] champsObligatoire){
        //Déclaration des variables et dictionnaire des données
        boolean resultat = true; //Faux s'il y a de l'information qui manque.

        //Module
        for (int i= 0 ; i < champsObligatoire.length; i++) {
            resultat = resultat && champsObligatoire[i].getText().toString().trim().length() > 0;
        }

        return resultat;
    }

    private void popUp(String message, String bouton, String titre)
    {
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
        dlgAlert.setMessage(message);
        dlgAlert.setTitle(titre);
        dlgAlert.setPositiveButton(bouton,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //dismiss the dialog
                    }
                });
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }

    private String verifyFalseID(EditText[] champsObligatoire){
        //Déclaration des variables et dictionnaire des données
        String resultat = "\n\nLes champs obligatoires vides sont: ";
        String champsVides = "";
        //Module
     for (int i= 0 ; i < champsObligatoire.length; i++) {
         if (champsObligatoire[i].getText().toString().trim().length() == 0) {
             champsVides += champsObligatoire[i].getHint().toString() + " & ";
         }
     }
        champsVides = champsVides.substring(0, champsVides.length() - 3) + ".";

        return resultat + champsVides;
    }
}
