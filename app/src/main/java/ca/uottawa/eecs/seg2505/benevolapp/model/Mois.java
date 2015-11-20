package ca.uottawa.eecs.seg2505.benevolapp.model;

public enum Mois {

    Janvier(1), Février(2), Mars(3), Avril(4), Mai(5), Juin(6), Juillet(7), Août(8), Septembre(9), Octobre(10), Novembre(11), Décembre(12);

    private final int index;
    Mois(int index) { this.index = index; }

    public int getIndex() {
        return index;
    }

    public static Mois get(int index) {
        if(index > 12) return null;
        for (Mois m : values()) if (m.getIndex() == index) return m;
        return null;
    }

}
