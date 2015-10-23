import java.util.Date;

public class Offre {
	private String strTitre, strType, strCompetence, strDescription ;
	private Date dateCreation, dateDebut, dateFin;
	private double dblDuree;
	private int nbrPlace, intAgeMin;
	private Personne pContact;
	private Lieu lEmplacement;
	private Horaire hHoraire;
	//constructeur pour les donnees obligatoires
	public Offre(String strTitre, String strType, Date dateDebut, Date dateFin, int nbrPlace, 
			Lieu lEmplacement, Horaire hHoraire) {
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
			Personne pContact, Lieu lEmplacement, Horaire hHoraire) {
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
	public String getStrTitre() {
		return strTitre;
	}
	public void setStrTitre(String strTitre) {
		this.strTitre = strTitre;
	}
	public String getStrType() {
		return strType;
	}
	public void setStrType(String strType) {
		this.strType = strType;
	}
	public String getStrCompetence() {
		return strCompetence;
	}
	public void setStrCompetence(String strCompetence) {
		this.strCompetence = strCompetence;
	}
	public String getStrDescription() {
		return strDescription;
	}
	public void setStrDescription(String strDescription) {
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
	public Personne getpContact() {
		return pContact;
	}
	public void setpContact(Personne pContact) {
		this.pContact = pContact;
	}
	public Lieu getlEmplacement() {
		return lEmplacement;
	}
	public void setlEmplacement(Lieu lEmplacement) {
		this.lEmplacement = lEmplacement;
	}
	public Horaire gethHoraire() {
		return hHoraire;
	}
	public void sethHoraire(Horaire hHoraire) {
		this.hHoraire = hHoraire;
	} 

		
}
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
