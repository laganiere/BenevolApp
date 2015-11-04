package ca.uottawa.eecs.seg2505.benevolapp.model;

//Made by group 15 for SEG2505A

import java.util.*;

public class Benevole {

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

    private String prenom, nom, courriel, ville, codePostal, numeroTelephone;
    private List<String> competences;
    private String domaineInterets;
    private int age;
    private Boolean isHomme;
    private List<Disponibilite> horaire;

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

    public Boolean getGender() {
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

    public String getDomaine_Interets() {
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

    public void setGender(Boolean isMale) {
        this.isHomme = isMale;
    }

    public void setAge(Integer age) {
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
