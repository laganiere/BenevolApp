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
    public Organisme getOrganisme(String nomUtilisateur) { // rl

        for (Organisme org: organismes) {
            if (org.getCourriel().equals(nomUtilisateur))
                return org;
        }

        return null;
    }

    /**
     * Cette méthode va chercher les offres de l'organisme selon le nom d'utilisateur (le courriel).
     *
     * @param courriel Le courriel du benevole.
     * @return L'objet Benevole correspondant.
     */
    @Override
    public Benevole getBenevole(String courriel) { // Équipe 7
        for (Benevole b : benevoles) if (b.getCourriel().equals(courriel)) return b;
        return null;
    }

    public Benevole getBenevolebyname(String name) { // Ã‰quipe 7
        for (Benevole b : benevoles) if (b.getNom().equals(name )) return b;
        return null;
    }

    /**
     * Cette méthode va chercher les offre de l'organisme selon le nom d'utilisateur (le courriel).
     *
     * @param nomUtilisateur Le courriel de l'organisme.
     * @return Un liste d'offre de l'organisme.
     */
    @Override
    public List<Offre> getOffresOrg(String nomUtilisateur) { // rl
        List<Offre> res= new ArrayList<Offre>();
        for (Offre of: offres) {
            if (of.getOrganismeName().equals(nomUtilisateur))
                res.add(of);
        }

        return res;
    }

    /**
     * Cette méthode va chercher les offres auxquelles le bénévole a appliquées
     * selon le nom d'utilisateur (le courriel).
     *
     * @param nomUtilisateur Le courriel de du bénévole.
     * @return Un liste d'offre de l'organisme.
     */
    @Override
    public List<Offre> getOffresBenevole(String nomUtilisateur) { // rl
        List<Offre> res= new ArrayList<Offre>();
        for (Offre of: offres) {
            for (Benevole ben : of.getPostulants().keySet()) {
                if (ben.getCourriel().equals(nomUtilisateur))
                    res.add(of);
            }
        }

        return res;
    }


    /**
     * Cette méthode va chercher les bénévoles qui ont sélectionnés une offre.
     *
     * @param offre L'offre.
     * @return La liste des bénévoles.
     */
    @Override
    public List<Benevole> getBenevoles(Offre offre) { // rl

        List <Benevole> res= new ArrayList<Benevole>();
        for (Benevole ben : offre.getPostulants().keySet())
            res.add(ben);

        return res;
    }

    /**
     * Cette méthode retourne la liste globale des compétences.
     *
     * @return La liste globale des compétences.
     */
    @Override
    public List<String> getCompetences() { // Équipe 15
        return competences;
    } //Équipe 14


    /**
     * Cette méthode sauvegarde un offre. Si l'offre existe déjà , alors ses informations
     * sont mises à jour. Sinon, l'offre est ajoutée Ã  la liste des offres.
     *
     * @param offre L'offre à sauvegarder.
     */
    @Override
    public void sauvegarderOffre(Offre offre) { // équipe 9
        if(offres.contains(offre)){
            int x = offres.lastIndexOf(offre);
            offres.set(x,offre);
            Organisme o = getOrganisme(offre.getOrganismeName());
            o.removeOffres(offre);
            o.addOffres(offre);
        }else{
            offres.add(offre);
            getOrganisme(offre.getOrganismeName()).addOffres(offre);
        }
    }

    /**
     * Cette méthode doit supprimer une offre de la liste des offres.
     *
     * @param offre L'offre à supprimer.
     */
    @Override
    public void supprimerOffre(Offre offre) { // Ã‰quipe 9
        if(offres.contains(offre)){
            Organisme o = getOrganisme(offre.getOrganismeName());
            offres.remove(offre);
            o.removeOffres(offre);
        }else{
            // offre pas dans offres (List)
        }
    }

    /**
     * Cette méthode est appelée quand un organisme veut ajouter une compétence dans le système.
     *
     * @param competence La compétence à ajouter.
     */
    @Override
    public void ajouteCompetence(String competence) { // Équipe 15
        competences.add(competence);
    } //Équipe 14

    /**
     * Cette méthode sauvegarde un bénévole. Si le bénévole existe déjà, alors ses informations
     * sont mises à jour. Sinon, le bénévole est ajouté à la liste des bénévoles.
     *
     * @param benevole Le bénévole à sauvegarder.
     */
    @Override
    public void sauvegarderBenevole(Benevole benevole) { // Équipe 8 et 4
        int index = benevoles.indexOf(benevole);
        if (index != -1){
            benevoles.set(index, benevole);
        }
        else{
            benevoles.add(benevole);
        }
    }

    /**
     * Cette méthode sauvegarde une organisme. Si l'organisme existe déjà, alors ses informations
     * sont mises à jour. Sinon, l'organisme est ajouté à la liste des organismes.
     *
     * @param organisme L'organisme à sauvegarder.
     */
    @Override
    public void sauvegarderOrganisme(Organisme organisme) { // Équipe 8 et 4
        int index = organismes.indexOf(organisme);
        if (index != -1){
            organismes.set(index, organisme);
        }
        else{
            organismes.add(organisme);
        }
    }

    /**
     * Cette méthode retourne la liste des offres appliquées par un bénévole.
     *
     * @param benevole Le bénévole.
     * @return :a liste des offres sélectionnées par un bénévole.
     */
    @Override
    public List<Offre> getOffres(Benevole benevole) { // Équipe 12
        return getOffresBenevole(benevole.getCourriel());
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

    public List<Offre> getOffresParInterets(Benevole benevole){
        List<Offre> sorted = new ArrayList<Offre>(offres);
        List<Integer> matches = new ArrayList<Integer>();

        String[] interests = benevole.getDomaineInterets().split(" "); //Crée un tableau contenant les intérets.

        int num;
        for(int i = 0; i<sorted.size(); i++){
            num = 0;
            for(int j=0; j<interests.length; j++){
                if(sorted.get(i).getDescription().contains(interests[j]))
                    num++;
            }
            matches.add(num);
        }

        for(int i=0; i<sorted.size(); i++){
            for(int j = i + 1; j<sorted.size(); j++){
                if(matches.get(i)<matches.get(j)){
                    int temp1 = matches.get(i);
                    matches.set(i, matches.get(j));
                    matches.set(j, temp1);

                    Offre temp2 = sorted.get(i);
                    sorted.set(i, sorted.get(j));
                    sorted.set(j, temp2);
                }
            }
        }

        return sorted;
    }

    /**
     * Cette méthode permet à un utilisateur d'appliquer pour une offre.
     *
     * @param benevole Le bénévole.
     * @param offre    L'offre à appliquer.
     */
    @Override
    public void applique(Benevole benevole, Offre offre) { // Équipe 12
        benevole.addOffresSelectionnees(offre);
        offre.addApplication(benevole);
    }

    /**
     * Cette méthode doit sélectionner un bénévole pour une offre.
     *
     * @param benevole Le bénévole à sélectionner.
     * @param offre    L'offre pour laquelle le bénévole est sélectionnée.
     */
    @Override
    public void selectionner(Benevole benevole, Offre offre) { // Ã‰quipe 11
        benevole.addOffresSelectionnees(offre);
        offre.addSelectionne(benevole);
//        if (offre != null && benevole != null) {
  //          offre.addApplication(benevole);
    //    }
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

    /**
     * Cette méthode doit Rejeter un bénévole pour une offre.
     *
     * @param benevole Le bénévole à rejeter.
     * @param offre    L'offre pour laquelle le bénévole est rejeté.
     */
    @Override
    public void rejeter(Benevole benevole, Offre offre) { // équipe 11
        offre.addRejete(benevole);
    }

    /**
     * Cette méthode permet à un utilisateur d'accepter un offre
     */
    @Override
    public boolean accepterOffre(Benevole benevole, Offre offre) { // Équipe 16

        if(offre.getNombrePlaces() > 0 ){
            offre.addAccepte(benevole);

            offre.setNombrePlaces(offre.getNombrePlaces() -1 );
            return true;
        }

        return false;
    }

    /**
     * Cette méthode permet à un utilisateur de refuser un offre
     */
    @Override
    public void refuserOffre(Benevole benevole, Offre offre) { //equipe 16

        offre.addRefuse(benevole);
    }

}
