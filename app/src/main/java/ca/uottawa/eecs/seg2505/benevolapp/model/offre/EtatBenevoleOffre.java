package ca.uottawa.eecs.seg2505.benevolapp.model.offre;

public enum EtatBenevoleOffre {
    Applique, Selectionne, Rejete, Accepte, Refuse;

    public String toString() {
        switch (this) {
            case Applique:
                return "Application";
            case Selectionne:
                return "Selection";
            case Rejete:
                return "Rejet";
            case Accepte:
                return "Acceptation";
            case Refuse:
                return "Refus";
        }

        return null;
    }
}
