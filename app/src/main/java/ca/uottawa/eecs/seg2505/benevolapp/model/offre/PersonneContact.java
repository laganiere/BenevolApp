package ca.uottawa.eecs.seg2505.benevolapp.model.offre;

public class PersonneContact {

    private String prenom, nom, email;

    public PersonneContact(String email) {
        this.email = email;
    }

    public PersonneContact(String prenom, String nom, String email) {
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
    }

    public String getFullName() {
        return getNom() + ", " + getPrenom();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {
        return getPrenom() + " " + getNom() + " - " + getEmail();
    }

}