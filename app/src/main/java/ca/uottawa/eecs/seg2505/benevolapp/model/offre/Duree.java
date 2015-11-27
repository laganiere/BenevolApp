package ca.uottawa.eecs.seg2505.benevolapp.model.offre;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Duree implements Serializable {

    private int minutes, hours;

    public Duree(int hours, int minutes) {
        if (hours < 0) throw  new IllegalArgumentException("Le nombre d'heures ne peut pas être négatif");
        if (minutes < 0) throw  new IllegalArgumentException("Le nombre de minutes ne peut pas être négatif");
        if (minutes >= 60) {
            hours += minutes / 60;
            minutes %= 60;
        }
        this.hours = hours;
        this.minutes = minutes;
    }

    public Duree(int minutes) {
        int hours = 0;
        if (minutes < 0) throw  new IllegalArgumentException("Le nombre de minutes ne peut pas être négatif");
        if (minutes >= 60) {
            hours += minutes / 60;
            minutes %= 60;
        }
        this.hours = hours;
        this.minutes = minutes;
    }

    public String toString() {
        double hoursFormatted = new BigDecimal(hours + (minutes / 60)).setScale(2, RoundingMode.HALF_UP).doubleValue();
        return hoursFormatted + " heure" + ((hoursFormatted == 1) ? "" : "s");
    }

}