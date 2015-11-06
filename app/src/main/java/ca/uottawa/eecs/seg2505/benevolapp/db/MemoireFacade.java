package ca.uottawa.eecs.seg2505.benevolapp.db;

import java.util.ArrayList;
import java.util.List;

import ca.uottawa.eecs.seg2505.benevolapp.model.Benevole;
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

        // recherche la liste de l'organisme et vérifie le courriel a chaque fois.
        for (int i = 0; i < this.organismes.size(); i ++) {
            if (this.organismes.get(i).getCourriel().equals(nomUtilisateur))
                return this.organismes.get(i);
        }
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

        // d'après le système, le couriel de l'utilisateur ne changeras jamais.
        // donc on peut se servir du courriel pour trouver l'organisme correspondant
        // et le modifier au besoin, ou ajouter le nouveau organisme a la fin

        for (int i = 0; i < this.organismes.size(); i ++) {
            if (this.organismes.get(i).getCourriel().equals(organisme.getCourriel())) {
                this.organismes.remove(i);
                this.organismes.add(i, organisme);
                return;
            }
        }
        this.organismes.add(organisme);
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

        // d'après le système, le couriel de l'utilisateur ne changeras jamais.
        // donc on peut se servir du courriel pour trouver le bénévoles correspondant
        // et le modifier au besoin, ou ajouter le nouveau organisme a la fin
        for (int i = 0; i < this.benevoles.size(); i ++) {
            if (this.benevoles.get(i).getCourriel().equals(benevole.getCourriel())) {
                this.benevoles.remove(i);
                this.benevoles.add(i, benevole);
                return;
            }
            this.benevoles.add(benevole);
        }
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
     * Cette méthode permet à un utilisateur d'appliquer pour une offre.
     *
     * @param benevole Le bénévole.
     * @param offre    L'offre à appliquer.
     */
    @Override
    public void applique(Benevole benevole, Offre offre) { // Équipe 12

    }
}
