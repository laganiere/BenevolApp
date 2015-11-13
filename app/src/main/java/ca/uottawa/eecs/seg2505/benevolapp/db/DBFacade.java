package ca.uottawa.eecs.seg2505.benevolapp.db;

import java.util.List;

import ca.uottawa.eecs.seg2505.benevolapp.model.Benevole;
import ca.uottawa.eecs.seg2505.benevolapp.model.Organisme;
import ca.uottawa.eecs.seg2505.benevolapp.model.offre.Offre;

public interface DBFacade {

    Organisme getOrganisme(String nomUtilisateur);

    Benevole getBenevole(String courriel);

    List<Offre> getOffres(String nomUtilisateur);

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
}

