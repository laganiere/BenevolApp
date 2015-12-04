package ca.uottawa.eecs.seg2505.benevolapp.model.offre;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import ca.uottawa.eecs.seg2505.benevolapp.model.Benevole;
import ca.uottawa.eecs.seg2505.benevolapp.model.Disponibilite;
import ca.uottawa.eecs.seg2505.benevolapp.model.Organisme;

public class Offre implements Serializable {
    protected String ID = UUID.randomUUID().toString();

    private String titre, typeActivite, description;
    private Calendar dateDebut, dateFin;
    private int nombrePlaces, ageMin;
    private Duree duree;
    private PersonneContact personneContact;
    private Lieu lieu;
    private Disponibilite disponibilite;
    private List<String> competences;
    private String organismeName;

    private Map<Benevole, EtatBenevoleOffre> postulants = new HashMap<Benevole, EtatBenevoleOffre>();

    // Constructeur complet
    public Offre(String titre, String typeActivite, List<String> competences, String description, Calendar dateDebut, Calendar dateFin, Duree duree, int nombrePlaces, int ageMin, PersonneContact personneContact, Lieu lieu, Disponibilite disponibilite, Organisme organisme) {
        this(titre, typeActivite, dateDebut, dateFin, nombrePlaces, lieu, disponibilite, organisme);
        this.competences = competences;
        if (this.competences==null)
            this.competences= new ArrayList<>();
        this.description = description;
        this.duree = duree;
        this.ageMin = ageMin;
        this.personneContact = personneContact;
        this.organismeName = organisme.getCourriel();
    }

    // Constructeur pour les données obligatoires
    public Offre(String titre, String typeActivite, Calendar dateDebut, Calendar dateFin, int nombrePlaces, Lieu lieu, Disponibilite disponibilite, Organisme organisme) {
        this.titre = titre;
        this.typeActivite = typeActivite;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nombrePlaces = nombrePlaces;
        this.lieu = lieu;
        this.disponibilite = disponibilite;
        this.organismeName = organisme.getCourriel();
        this.competences= new ArrayList<>();
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String strTitre) {
        this.titre = strTitre;
    }

    public String getType() {
        return typeActivite;
    }

    public void setType(String strType) {
        this.typeActivite = strType;
    }

    public List<String> getCompetences() {
        return competences;
    }

    public boolean addCompetence(String competence) {
        return this.competences.add(competence);
    }

    public boolean addCompetences(List<String> competences) {
        return this.competences.addAll(competences);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Calendar getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Calendar dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Calendar getDateFin() {
        return dateFin;
    }

    public void setDateFin(Calendar dateFin) {
        this.dateFin = dateFin;
    }

    public Duree getDuree() {
        return this.duree;
    }

    public void setDuree(Duree duree) {
        this.duree = duree;
    }

    public int getNombrePlaces() {
        return nombrePlaces;
    }

    public void setNombrePlaces(int nombrePlaces) {
        this.nombrePlaces = nombrePlaces;
    }

    public int getAgeMinimum() {
        return ageMin;
    }

    public void setAgeMinimum(int ageMin) {
        this.ageMin = ageMin;
    }

    public PersonneContact getPersonneContact() {
        return personneContact;
    }

    public void setPersonneContact(PersonneContact personneContact) {
        this.personneContact = personneContact;
    }

    public Lieu getLieu() {
        return lieu;
    }

    public void setLieu(Lieu lieu) {
        this.lieu = lieu;
    }

    public Disponibilite getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(Disponibilite disponibilite) {
        this.disponibilite = disponibilite;
    }

    public String getOrganismeName() {
        return organismeName;
    }

    public void setOrganismeName(String organismeName) {
        this.organismeName = organismeName;
    }

    public Map<Benevole, EtatBenevoleOffre> getPostulants() {
        return postulants;
    }

    public EtatBenevoleOffre getEtatBenevole(Benevole benevole) {
        return this.postulants.get(benevole);
    }

    public void addApplication(Benevole benevole) {
        this.postulants.put(benevole, EtatBenevoleOffre.Applique);
    }

    public void addSelectionne(Benevole benevole) {
        this.postulants.put(benevole, EtatBenevoleOffre.Selectionne);
    }

    public void addRejete(Benevole benevole) {
        this.postulants.put(benevole, EtatBenevoleOffre.Rejete);
    }

    public void addAccepte(Benevole benevole) {
        this.postulants.put(benevole, EtatBenevoleOffre.Accepte);
    }

    public void addRefuse(Benevole benevole) {
        this.postulants.put(benevole, EtatBenevoleOffre.Refuse);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Offre offre = (Offre) o;

        return ID.equals(offre.ID);
    }

    @Override
    public int hashCode() {
        return ID.hashCode();
    }
}

	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
