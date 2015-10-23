package ca.uottawa.eecs.seg2505.benevolapp.model;

public class Lieu{
	private String strVille, strCodePostal;

	public Lieu(String strVille, String strCodePostal) {

		this.strVille = strVille;
		this.strCodePostal = strCodePostal;
	}

	public String geVille() {
		return strVille;
	}

	public void setVille(String strVille) {
		this.strVille = strVille;
	}

	public String getCodePostal() {
		return strCodePostal;
	}

	public void setCodePostal(String strCodePostal) {
		this.strCodePostal = strCodePostal;
	}
	

}
	
