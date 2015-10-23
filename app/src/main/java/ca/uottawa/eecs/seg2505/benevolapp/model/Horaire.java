
public class Horaire {
	private jourSemaine jour;
	private boolean isMatin;
	public Horaire(jourSemaine jour, boolean isMatin) {
		this.jour = jour;
		this.isMatin = isMatin;
	}

	public jourSemaine getJour() {
		return jour;
	}

	public void setJour(jourSemaine jour) {
		this.jour = jour;
	}

	public boolean getisMatin() {
		return isMatin;
	}

	public void setisMatin(Boolean isMatin) {
		this.isMatin = isMatin;
	}
	
}
