package ca.uottawa.eecs.seg2505.benevolapp.model;

import java.util.List;

import ca.uottawa.eecs.seg2505.benevolapp.model.offre.Offre;

public class Organisme {
    private String nom;
    private String numeroPorte;
    private String nomRue;
    private String numeroAppartement;
    private String codePostal;
    private String ville;
    private String numeroTelephone;
    private String siteWeb;
    private String courriel;
    private String description;
    private String tailleOrganisation;
    private String secteurActivite;
    private String fondateur;

    private List<Offre> offres;

    public Organisme(String nom, String numeroPorte, String nomRue, String numeroAppartement, String ville, String codePostal, String courriel) {
        this.nom = nom;
        this.numeroPorte = numeroPorte;
        this.nomRue = nomRue;
        this.numeroAppartement = numeroAppartement;
        this.ville = ville;
        this.codePostal = codePostal; //Code postal canadien
        this.courriel = courriel;
    }

    public Organisme(String nom, String numeroPorte, String nomRue, String numeroAppartement, String codePostal, String ville, String numeroTelephone, String siteWeb, String courriel, String description, String tailleOrganisation, String secteurActivite, String fondateur) {
        this.nom = nom;
        this.numeroPorte = numeroPorte;
        this.nomRue = nomRue;
        this.numeroAppartement = numeroAppartement;
        this.codePostal = codePostal;
        this.ville = ville;
        this.numeroTelephone = numeroTelephone;
        this.siteWeb = siteWeb;
        this.courriel = courriel;
        this.description = description;
        this.tailleOrganisation = tailleOrganisation;
        this.secteurActivite = secteurActivite;
        this.fondateur = fondateur;
    }

    public Organisme(String nom, String siteWeb, String courriel) {
        this.nom = nom;
        this.siteWeb = siteWeb;
        this.courriel = courriel;
    }

    private List<Offre> offresDeOrganisme;

    public String getNomRue() {
        return nomRue;
    }

    public void setNomRue(String nomRue) {
        this.nomRue = nomRue;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNumeroPorte() {
        return numeroPorte;
    }

    public void setNumeroPorte(String numeroPorte) {
        this.numeroPorte = numeroPorte;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getNumeroAppartement() {
        return numeroAppartement;
    }

    public void setNumeroAppartement(String numeroAppartement) {
        this.numeroAppartement = numeroAppartement;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getSiteWeb() {
        return siteWeb;
    }

    public void setSiteWeb(String siteWeb) {
        this.siteWeb = siteWeb;
    }

    public String getCourriel() {
        return courriel;
    }

    public void setCourriel(String courriel) {
        this.courriel = courriel;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTailleOrganisation() {
        return tailleOrganisation;
    }

    public void setTailleOrganisation(String sizeOrganization) {
        this.tailleOrganisation = sizeOrganization;
    }

    public String getSecteurActivite() {
        return secteurActivite;
    }

    public void setSecteurActivite(String secteurActivite) {
        this.secteurActivite = secteurActivite;
    }

    public String getFondateur() {
        return fondateur;
    }

    public void setFondateur(String fondateur) {
        this.fondateur = fondateur;
    }

    // Cette fonction retourne l'adresse complète selon les différents champs de l'adresse
    // qui auront été concaténés.
    public String getAddress() {
        return (numeroPorte + " " + nomRue + ", " + numeroAppartement + ", " + ville + ", " + codePostal);
    }

    public List<Offre> getOffres() {
        return offres;
    }

    public void addOffres(Offre offre) {
        this.offres.add(offre);
    }

    public void removeOffres(Offre offre) {
        this.offres.remove(offre);
    }
}
