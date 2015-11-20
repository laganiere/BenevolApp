package ca.uottawa.eecs.seg2505.benevolapp.model.offre;

import java.io.Serializable;

public class Lieu implements Serializable {

    private String ville, codePostal;

    public Lieu(String ville, String codePostal) {
        this.ville = ville;
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String strVille) {
        this.ville = strVille;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String strCodePostal) {
        this.codePostal = strCodePostal;
    }

    public String toString() {
        return ville + ", " + codePostal;
    }

}