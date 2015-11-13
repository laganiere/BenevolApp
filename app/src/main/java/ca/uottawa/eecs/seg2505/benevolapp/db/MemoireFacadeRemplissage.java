package ca.uottawa.eecs.seg2505.benevolapp.db;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import ca.uottawa.eecs.seg2505.benevolapp.model.Benevole;
import ca.uottawa.eecs.seg2505.benevolapp.model.Disponibilite;
import ca.uottawa.eecs.seg2505.benevolapp.model.JourSemaine;
import ca.uottawa.eecs.seg2505.benevolapp.model.Organisme;
import ca.uottawa.eecs.seg2505.benevolapp.model.Utilisateur;
import ca.uottawa.eecs.seg2505.benevolapp.model.offre.Duree;
import ca.uottawa.eecs.seg2505.benevolapp.model.offre.Lieu;
import ca.uottawa.eecs.seg2505.benevolapp.model.offre.Offre;

public class MemoireFacadeRemplissage {
    public static void remplirMemoire(List<Utilisateur> utilisateurs,
                                      List<Organisme> organismes,
                                      List<Benevole> benevoles,
                                      List<Offre> offres,
                                      List<String> competences) {
        creerCompetences(competences);
        creerOrganismes(organismes);
        utilisateurs.addAll(organismes);
        creerBenevoles(benevoles, competences);
        utilisateurs.addAll(benevoles);
        creerOffres(offres, competences, organismes);

        ajouterApplications(offres, benevoles);
    }

    private static void creerOrganismes(List<Organisme> organismes) {
        Organisme org1 = new Organisme("Croix rouge",
                "340",
                "Catherine Stree",
                "",
                "K1R 1C4",
                "Ottawa",
                "613 560-7220",
                "http://www.croixrouge.ca",
                "rh@croixrouge.ca",
                "La Croix-Rouge canadienne vise à améliorer les conditions d’existence des personnes vulnérables en mobilisant le pouvoir de l’humanité au Canada et partout dans le monde.",
                "Grande",
                "Aide humanitaire",
                "");
        organismes.add(org1);

        Organisme org2 = new Organisme("Western Ottawa Community Resource Centre",
                "2",
                "MacNeil Court",
                "",
                "K2L 4H7",
                "Ottawa",
                "613-591-3686 ext 280",
                "http://www.wocrc.ca",
                "rh@wocrc.ca",
                "The WOCRC partners with others to develop, provide and coordinate accessible community, health and social services for all members of our diverse communities. We are committed to ensuring access to permanent and quality French Language Services in our designated programs and services.",
                "Moyenne",
                "Développement local",
                "");
        organismes.add(org2);

        Organisme org3 = new Organisme("Parent Resource Centre",
                "300",
                "Goulburn Private",
                "",
                "K1N 1C9",
                "Ottawa",
                "613-565-2467 x229",
                "http://www.parentresource.ca",
                "rh@parentresource.ca",
                "As a children’s charity, the Parent Resource Centre (PRC) is a leader in family support resource programs and comprehensive training for professionals. With a focus on early learning, parent support, training, research and community capacity, PRC aims to have a lasting impact on children’s developmental outcomes.",
                "Moyenne",
                "Aide parentale",
                "");
        organismes.add(org3);
    }

    private static void creerBenevoles(List<Benevole> benevoles, List<String> competences) {
        Benevole ben1 = new Benevole("François",
                "Tremblay",
                "Ottawa",
                "K2C 4F5",
                "613-231-2143",
                "ftremblay1234@gmail.com",
                "Je suis bon en programmation et surtout pour faire des sites web.",
                23,
                true);
        ben1.addCompetence(competences.get(0));
        ben1.addCompetence(competences.get(2));
        ben1.addCompetence(competences.get(3));
        ben1.addDisponibilite(new Disponibilite(JourSemaine.Dimanche, true));
        ben1.addDisponibilite(new Disponibilite(JourSemaine.Dimanche, false));
        ben1.addDisponibilite(new Disponibilite(JourSemaine.Jeudi, false));
        ben1.addDisponibilite(new Disponibilite(JourSemaine.Vendredi, true));
        benevoles.add(ben1);

        Benevole ben2 = new Benevole("John",
                "Connor",
                "Ottawa",
                "K3J 8A1",
                "613-534-8854",
                "j_coonnor1987@gmail.com",
                "J'aime en cuisine et en art.",
                28,
                true);
        ben2.addCompetence(competences.get(4));
        ben2.addCompetence(competences.get(1));
        ben2.addCompetence(competences.get(6));
        ben2.addDisponibilite(new Disponibilite(JourSemaine.Samedi, false));
        ben2.addDisponibilite(new Disponibilite(JourSemaine.Dimanche, false));
        ben2.addDisponibilite(new Disponibilite(JourSemaine.Lundi, true));
        ben2.addDisponibilite(new Disponibilite(JourSemaine.Mardi, true));
        ben2.addDisponibilite(new Disponibilite(JourSemaine.Jeudi, true));
        benevoles.add(ben2);

        Benevole ben3 = new Benevole("Amélie",
                "Duroi",
                "Ottawa",
                "K6A 4H9",
                "613-977-3247",
                "duroy.amelie.127@gmail.com",
                "J'ai un diplôme d'infirmière.",
                28,
                true);
        ben3.addCompetence(competences.get(4));
        ben3.addCompetence(competences.get(2));
        ben3.addCompetence(competences.get(3));
        ben3.addDisponibilite(new Disponibilite(JourSemaine.Samedi, false));
        ben3.addDisponibilite(new Disponibilite(JourSemaine.Samedi, true));
        ben3.addDisponibilite(new Disponibilite(JourSemaine.Dimanche, false));
        ben3.addDisponibilite(new Disponibilite(JourSemaine.Dimanche, true));
        benevoles.add(ben3);
    }

    private static void creerOffres(List<Offre> offres, List<String> competences, List<Organisme> organismes) {
        Offre off1 = new Offre("Progammation du nouveau site web",
                "Programmation d'une site web",
                Arrays.asList(competences.get(0)),
                "La personne sera en charge de programmer notre nouveau site web qui devra contenir des fonctionnalités avancées.",
                Calendar.getInstance(),
                null,
                new Duree(80),
                4,
                16,
                null,
                null,
                new Disponibilite(JourSemaine.Vendredi, false),
                organismes.get(2));
        organismes.get(2).addOffres(off1);
        offres.add(off1);

        Offre off2 = new Offre("Plantation d'arbre",
                "Projet de plantation d'arbre dans la ville d'Ottawa",
                Arrays.asList(competences.get(1), competences.get(3)),
                "Le projet est de planter des arbres un peu partout dans la ville d'ottawa",
                new GregorianCalendar(2016, Calendar.APRIL, 1),
                new GregorianCalendar(2016, Calendar.AUGUST, 30),
                new Duree(100),
                10,
                16,
                null,
                new Lieu("Ottawa", "K2H 4H7"),
                new Disponibilite(JourSemaine.Samedi, false),
                organismes.get(1));
        offres.add(off2);
        organismes.get(1).addOffres(off2);

        Offre off3 = new Offre("Aide cuisinier pour cuisine communautaire",
                "Assister les gens voulant faire de la cuisine communautaire",
                Arrays.asList(competences.get(3), competences.get(6)),
                "Il s'agit d'aide les gens voulant faire de la cuisine communautaire en leur donnant des petits trucs, cours ou autres permettant aux gens de se réaliser.",
                new GregorianCalendar(2015, Calendar.DECEMBER, 1),
                null,
                new Duree(7),
                2,
                18,
                null,
                new Lieu("Ottawa", "K2H 4H7"),
                new Disponibilite(JourSemaine.Mercredi, true),
                organismes.get(2));
        organismes.get(2).addOffres(off3);
        offres.add(off3);
    }

    private static void creerCompetences(List<String> competences) {
        competences.add("Programmation");
        competences.add("Menuiserie");
        competences.add("Comptabilité");
        competences.add("Agriculture");
        competences.add("Infirmier");
        competences.add("Ressources humaines");
        competences.add("Cuisine");
    }

    private static void ajouterApplications(List<Offre> offres, List<Benevole> benevoles) {
        offres.get(1).addApplication(benevoles.get(1));
        benevoles.get(1).addOffresSelectionnees(offres.get(1));

        offres.get(1).addApplication(benevoles.get(2));
        benevoles.get(2).addOffresSelectionnees(offres.get(1));

        offres.get(2).addApplication(benevoles.get(1));
        benevoles.get(1).addOffresSelectionnees(offres.get(2));
    }

    public static void main(String... args) {
        new MemoireFacade();
    }
}

