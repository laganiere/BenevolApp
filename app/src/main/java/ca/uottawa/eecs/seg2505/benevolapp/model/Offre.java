package ca.uottawa.eecs.seg2505.benevolapp.model;

import java.util.Date;

public class Offre {
	private String strTitre, strType, strCompetence, strDescription ;
	private Date dateCreation, dateDebut, dateFin;
	private double dblDuree;
	private int nbrPlace, intAgeMin;
	private String pContact;
	private Lieu lEmplacement;
	private Disponibilite hHoraire;
	//constructeur pour les donnees obligatoires
	public Offre(String strTitre, String strType, Date dateDebut, Date dateFin, int nbrPlace, 
			Lieu lEmplacement, Disponibilite hHoraire) {
		this.strTitre = strTitre;
		this.strType = strType;
		this.hHoraire = hHoraire;
		this.dateCreation = new Date();
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.nbrPlace = nbrPlace;
		this.lEmplacement = lEmplacement;
	}
	//constructeur general
	public Offre(String strTitre, String strType, String strCompetence, String strDescription,
			Date dateDebut, Date dateFin, double dblDuree, int nbrPlace, int intAgeMin,
			String pContact, Lieu lEmplacement, Disponibilite hHoraire) {
		this.strTitre = strTitre;
		this.strType = strType;
		this.strCompetence = strCompetence;
		this.hHoraire = hHoraire;
		this.strDescription = strDescription;
		this.dateCreation = new Date();
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.dblDuree = dblDuree;
		this.nbrPlace = nbrPlace;
		this.intAgeMin = intAgeMin;
		this.pContact = pContact;
		this.lEmplacement = lEmplacement;
	}
	public String getTitre() {
		return strTitre;
	}
	public void setTitre(String strTitre) {
		this.strTitre = strTitre;
	}
	public String getType() {
		return strType;
	}
	public void setType(String strType) {
		this.strType = strType;
	}
	public String getCompetence() {
		return strCompetence;
	}
	public void setCompetence(String strCompetence) {
		this.strCompetence = strCompetence;
	}
	public String getDescription() {
		return strDescription;
	}
	public void setDescription(String strDescription) {
		this.strDescription = strDescription;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	
	public Date getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	public Date getDateFin() {
		return dateFin;
	}
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	public double getDblDuree() {
		return dblDuree;
	}
	public void setDblDuree(double dblDuree) {
		this.dblDuree = dblDuree;
	}
	public int getNbrPlace() {
		return nbrPlace;
	}
	public void setNbrPlace(int nbrPlace) {
		this.nbrPlace = nbrPlace;
	}
	public int getIntAgeMin() {
		return intAgeMin;
	}
	public void setIntAgeMin(int intAgeMin) {
		this.intAgeMin = intAgeMin;
	}
	public String getpContact() {
		return pContact;
	}
	public void setpContact(String pContact) {
		this.pContact = pContact;
	}
	public Lieu getlEmplacement() {
		return lEmplacement;
	}
	public void setlEmplacement(Lieu lEmplacement) {
		this.lEmplacement = lEmplacement;
	}
	public Disponibilite gethHoraire() {
		return hHoraire;
	}
	public void sethHoraire(Disponibilite hHoraire) {
		this.hHoraire = hHoraire;
	} 

		
}
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
