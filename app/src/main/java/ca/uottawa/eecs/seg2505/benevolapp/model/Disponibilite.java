package ca.uottawa.eecs.seg2505.benevolapp.model;

public class Disponibilite {
	private jourSemaine jour;
	private boolean soir;
	public Horaire(jourSemaine jour, boolean isSoir) {
		this.jour = jour;
		this.soir = isSoir;
	}

	public jourSemaine getJour() {
		return jour;
	}

	public void setJour(jourSemaine jour) {
		this.jour = jour;
	}

	public boolean isSoir() {
		return soir;
	}

	public void setSoir(Boolean isSoir) {
		this.soir = isSoir;
	}
	
}
