package ca.uottawa.eecs.seg2505.benevolapp.db;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ca.uottawa.eecs.seg2505.benevolapp.model.Benevole;
import ca.uottawa.eecs.seg2505.benevolapp.model.Disponibilite;
import ca.uottawa.eecs.seg2505.benevolapp.model.Organisme;
import ca.uottawa.eecs.seg2505.benevolapp.model.Utilisateur;
import ca.uottawa.eecs.seg2505.benevolapp.model.offre.Offre;

public class MemoireFacade implements DBFacade {

    /**
     * Cette liste contient tous les utilisateurs (bénévoles et organisme).
     */
    private List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();

    private List<Organisme> organismes = new ArrayList<Organisme>();
    private List<Benevole> benevoles = new ArrayList<Benevole>();
    private List<Offre> offres = new ArrayList<Offre>();
    private List<String> competences = new ArrayList<String>();

    public MemoireFacade() {
        MemoireFacadeRemplissage.remplirMemoire(utilisateurs, organismes, benevoles, offres, competences);
    }

    /**
     * Cette méthode doit aller chercher l'organisme selon le nom d'utilisateur (le courriel).
     *
     * @param nomUtilisateur Le courriel de l'organisme.
     * @return L'objet Organisme correspondant.
     */
    @Override
    public Organisme getOrganisme(String nomUtilisateur) { // Équipe 4
        return null;
    }

    /**
     * Cette méthode doit aller chercher le bénévole selon le nom d'utilisateur (le courriel).
     *
     * @param courriel Le courriel du benevole.
     * @return L'objet Benevole correspondant.
     */
    @Override
    public Benevole getBenevole(String courriel) { // Équipe 7
        for (Benevole b : benevoles) if (b.getCourriel() == courriel) return b;
        return null;
    }

    /**
     * Cette méthode va chercher les offre de l'organisme selon le nom d'utilisateur (le courriel).
     *
     * @param nomUtilisateur Le courriel de l'organisme.
     * @return Un liste d'offre de l'organisme.
     */
    @Override
    public List<Offre> getOffres(String nomUtilisateur) { // Équipe 1
        return null;
    }

    /**
     * Cette méthode va chercher les bénévoles qui ont sélectionner une offre.
     *
     * @param offre L'offre.
     * @return La liste des bénévoles.
     */
    @Override
    public List<Benevole> getBenevoles(Offre offre) { // Équipe 1
        return null;
    }

    /**
     * Cette méthode retourne la liste globale des compétences.
     *
     * @return La liste globale des compétences.
     */
    @Override
    public List<String> getCompetences() { // Équipe 15
        return null;
    }

    /**
     * Cette méthode sauvegarde une organisme. Si l'organisme existe déjà, alors ses informations
     * sont mises à jour. Sinon, l'organisme est ajouté à la liste des organismes.
     *
     * @param organisme L'organisme à sauvegarder.
     */
    @Override
    public void sauvegarderOrganisme(Organisme organisme) { // Équipe 8 et 4

    }

    /**
     * Cette méthode sauvegarde un offre. Si l'offre existe déjà, alors ses informations
     * sont mises à jour. Sinon, l'offre est ajoutée à la liste des offres.
     *
     * @param offre L'offre à sauvegarder.
     */
    @Override
    public void sauvegarderOffre(Offre offre) { // Équipe 9

    }

    /**
     * Cette méthode doit supprimer une offre de la liste des offres.
     *
     * @param offre L'offre à supprimer.
     */
    @Override
    public void supprimerOffre(Offre offre) { // Équipe 9

    }

    /**
     * Cette méthode doit sélectionner un bénévole pour une offre.
     *
     * @param benevole Le bénévole à sélectionner.
     * @param offre    L'offre pour laquelle le bénévole est sélectionnée.
     */
    @Override
    public void selectionner(Benevole benevole, Offre offre) { // Équipe 11

    }

    /**
     * Cette méthode est appelée quand un organisme veut ajouter une compétence dans le système.
     *
     * @param competence La compétence à ajouter.
     */
    @Override
    public void ajouteCompetence(String competence) { // Équipe 15

    }

    /**
     * Cette méthode sauvegarde un bénévole. Si le bénévole existe déjà, alors ses informations
     * sont mises à jour. Sinon, le bénévole est ajouté à la liste des bénévoles.
     *
     * @param benevole Le bénévole à sauvegarder.
     */
    @Override
    public void sauvegarderBenevole(Benevole benevole) { // Équipe 8 et 4

    }

    /**
     * Cette méthode retourne la liste des offres sélectionnées par un bénévole.
     *
     * @param benevole Le bénévole.
     * @return :a liste des offres sélectionnées par un bénévole.
     */
    @Override
    public List<Offre> getOffres(Benevole benevole) { // Équipe 12
        return null;
    }

    /**
     * Cette methode retourne la liste des offres disponibles pour un bénévole
     *
     @param benevole - Le bénévole
     @return : a liste des offres sélectionées par un bénévole
     */
    @Override
    public List<Offre> getOffresDisponibles(Benevole benevole) { // Equipe 7
        List<Offre> offresDispo = new ArrayList<Offre>();
        List<Disponibilite> dispoBenevole = benevole.getHoraire();

        //Recherche a travers TOUTES les offres et retourne les offres qui correspondent a la disponibilite du benevole
        for (int i = 0; i < offres.size(); i++) {
            Offre offre = offres.get(i);
            // Il pourrait y avoir un Etat spécifique pour disponible au lieu de null.
            if (offre.getEtatBenevole(benevole) == null) {
                for (int j = 0; j < dispoBenevole.size(); j++) {
                    if (offre.getDisponibilite().equals(dispoBenevole.get(j))) {
                        offresDispo.add(offre);
                    }
                }
            }
        }

        // Ordonner les résultats.
        Collections.sort(offresDispo, new CompatibiliteComparator(benevole));

        return offresDispo;
    }

    /**
     * Cette méthode permet à un utilisateur d'appliquer pour une offre.
     *
     * @param benevole Le bénévole.
     * @param offre    L'offre à appliquer.
     */
    @Override
    public void applique(Benevole benevole, Offre offre) { // Équipe 12
        if (offre != null && benevole != null) {
            offre.addApplication(benevole);
        }
    }

    /**
     * Cette classe permet de trier les offres disponibles pour un utilisateur en ordre de compatibilite.
     */
    private class CompatibiliteComparator implements Comparator<Offre> {

        private Benevole benevole;

        public CompatibiliteComparator(Benevole benevole) {
            this.benevole = benevole;
        }

        @Override
        public int compare(Offre o0, Offre o1) {
            if (benevole.getVille() == o0.getLieu().getVille()) {
                // La ville de l'offre 0 est la meme que celle du benevole, alors l'offre a la priorite.
                return -1;
            } else if (benevole.getVille() == o1.getLieu().getVille()) {
                // La ville de l'offre 1 est la meme que celle du benevole, alors l'offre a la priorite.
                return 1;
            }
            // On pourrait ajouter plusieurs autres facteurs qui ordonne les offres.
            return 0;
        }
    }
}
