package ca.uottawa.eecs.seg2505.benevolapp.model;

//Made by group 15 for SEG2505A

import java.util.ArrayList;
import java.util.List;

import ca.uottawa.eecs.seg2505.benevolapp.model.offre.Offre;

public class Benevole extends Utilisateur {

    /* Example d'instantiation
    public static void main(String[] args) {
     Benevole aki = new Benevole("Akintola-Febrissy", "aakin013@uottawa.ca");
     aki.setNom("Akinyele");
     aki.setVille("Ottawa");
     aki.setCodePostal("K7G");
     aki.setAge(17);
     aki.setGender(true);
     aki.setNumeroTelephone("911");
     aki.addCompetence("Batterie");
     aki.addCompetence("Laughing");
     System.out.println(aki);
    }*/

    private String prenom;
    private String nom;
    private List<String> competences;
    private String domaineInterets;
    private int age;

    /**
     * La classe Boolean est utilisée pour que l'on puisse avoir la nullitabilité.
     */
    private Boolean isHomme;

    private List<Disponibilite> horaire;


    private List<Offre> offresAppliquees = new ArrayList<Offre>();

    public Benevole() {

    }

    public Benevole(String prenom, String nom, String ville, String codePostal, String numeroTelephone, String courriel, String domaineInterets, int age, Boolean isHomme) {
        this.prenom = prenom;
        this.nom = nom;
        this.domaineInterets = domaineInterets;
        this.age = age;
        this.isHomme = isHomme;
    }

    public Benevole(String prenom, String courriel) {
        this.prenom = prenom;
        this.courriel = courriel;
    }

    //getters
    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getCourriel() {
        return courriel;
    }

    public String getVille() {
        return ville;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public Boolean getGenre() {
        return isHomme == null ? null : isHomme;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public Integer getAge() {
        return age;
    }

    public List<String> getCompetences() {
        return competences;
    }

    public String getDomaineInterets() {
        return domaineInterets;
    }

    public List<Disponibilite> getHoraire() {
        return horaire;
    }

    //setters
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCourriel(String courriel) {
        this.courriel = courriel;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    public void setGenre(Boolean isHomme) {
        this.isHomme = isHomme;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public void setDomaineInterets(String domaineInterets) {
        this.domaineInterets = domaineInterets;
    }

    //adders

    public void addDisponibilite(Disponibilite disponibilite) {

        if (horaire == null) {
            horaire = new ArrayList<Disponibilite>();
        }

        horaire.add(disponibilite);
    }

    public void addCompetence(String competence) {

        if (competences == null) {
            competences = new ArrayList<String>();
        }

        competences.add(competence);
    }


    public List<Offre> getOffresAppliquees() {
        return offresAppliquees;
    }

    public void addOffresSelectionnees(Offre offre) {
        this.offresAppliquees.add(offre);
    }


    //Creates a string representation of the class
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Nom : " + prenom + "\n");
        sb.append("Prénom : " + nom + "\n");
        sb.append("Ville : " + ville + "\n");
        sb.append("Code postal : " + codePostal + "\n");
        sb.append("Adresse email : " + courriel + "\n");
        sb.append("Numéro de Téléphone : " + numeroTelephone + "\n");
        if (age != 0) {
            sb.append("Age : " + age + "\n");
        }
        if (isHomme != null) {
            sb.append("Sexe : " + (isHomme ? "Homme" : "Femme") + "\n");
        }
        if (competences != null && competences.size() > 0) {
            sb.append("---------------\n");
            sb.append("Liste des compétences : \n");
            for (String s : competences) {
                sb.append(s + "\n");
            }
            sb.append("\n");
        }
        return sb.toString();
    }


}
