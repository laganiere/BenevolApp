package ca.uottawa.eecs.seg2505.benevolapp.model;

import java.io.Serializable;

public class Disponibilite  implements Serializable {

    private JourSemaine jour;
    private boolean soir;

    public Disponibilite(JourSemaine jour, boolean isSoir) {
        this.jour = jour;
        this.soir = isSoir;
    }

    public JourSemaine getJour() {
        return jour;
    }

    public void setJour(JourSemaine jour) {
        this.jour = jour;
    }

    public boolean isSoir() {
        return soir;
    }

    public void setSoir(boolean isSoir) {
        this.soir = isSoir;
    }

    public String toString() {
        return jour.name() + ((isSoir()) ? " (soir)" : " (jour)");
    }

    public boolean equals(Disponibilite other) {
        if (jour == other.getJour() && soir == other.soir) return true;
        else return false;
    }

}
