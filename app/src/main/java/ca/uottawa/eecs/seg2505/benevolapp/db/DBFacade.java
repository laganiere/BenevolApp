package ca.uottawa.eecs.seg2505.benevolapp.db;

import java.util.List;

import ca.uottawa.eecs.seg2505.benevolapp.model.Benevole;
import ca.uottawa.eecs.seg2505.benevolapp.model.Organisme;
import ca.uottawa.eecs.seg2505.benevolapp.model.offre.Offre;

public interface DBFacade {

    Organisme getOrganisme(String nomUtilisateur);

    List<Offre> getOffresOrg(String nomUtilisateur);

    List<Offre> getOffresBenevole(String nomUtilisateur);

    Benevole getBenevole(String courriel);

    List<Benevole> getBenevoles(Offre offre);

    List<String> getCompetences();

    void sauvegarderOrganisme(Organisme organisme);

    void sauvegarderOffre(Offre offre);

    void supprimerOffre(Offre offre);

    void selectionner(Benevole benevole, Offre offre);

    void ajouteCompetence(String competence);

    void sauvegarderBenevole(Benevole benevole);

    List<Offre> getOffres(Benevole benevole);

    List<Offre> getOffresDisponibles(Benevole benevole);

    void applique(Benevole benevole, Offre offre);

    public void rejeter(Benevole benevole, Offre offre);

    public boolean accepterOffre(Benevole benevole, Offre offre);

    public void refuserOffre(Benevole benevole, Offre offre);
}
