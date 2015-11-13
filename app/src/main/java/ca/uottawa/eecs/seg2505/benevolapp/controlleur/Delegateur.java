/**
 * This file contains material supporting the course
 * SEG2505: Introduction to Software Engineering
 * University of Ottawa.
 * http://wwww.eecs.uottawa.ca/~laganier
 *
 * This program is free software; permission is hereby granted to use, copy, modify,
 * and distribute this source code, or portions thereof, for any purpose, without fee,
 * subject to the restriction that the copyright notice may not be removed
 * or altered from any source or altered source distribution.
 * The software is released on an as-is basis and without any warranties of any kind.
 * In particular, the software is not guaranteed to be fault-tolerant or free from failure.
 * The author disclaims all warranties with regard to this software, any use,
 * and any consequent failure, is purely the responsibility of the user.
 *
 * Hanna Farah 2014
 * Robert Laganiere 2015
 */
package ca.uottawa.eecs.seg2505.benevolapp.controlleur;

import ca.uottawa.eecs.seg2505.benevolapp.db.DBFacade;
import ca.uottawa.eecs.seg2505.benevolapp.db.MemoireFacade;
import ca.uottawa.eecs.seg2505.benevolapp.model.Utilisateur;

/**
 * Singleton
 * Toute les fonctionalites du modele devraient etre accedees de cette classe.
 * 
 */
public class Delegateur {

    public static Utilisateur utilisateurCourant;
	public static Delegateur delegateur;

	/**
	 * La specification d'une DBFacade est requise pour le fonctionnement du systeme
	 */
	public static DBFacade dbFacade;
	
	private BenevoleControlleur benevoleControlleur;
    private OrganismeControlleur organismeControlleur;

	// acces au Singleton
	public static Delegateur getInstance() {
		if (delegateur == null) delegateur = new Delegateur();
		return delegateur;
	}

	/**
	 * Methode pour assigner une DBFacade au systeme
	 * @param facade une classe qui implemente DBFacade
	 */
	public static void setDBFacade(DBFacade facade) {
		dbFacade = facade;
	}

	// the private constructor
	private Delegateur() {
		dbFacade = new MemoireFacade();
		benevoleControlleur = new BenevoleControlleur(dbFacade);
		organismeControlleur = new OrganismeControlleur(dbFacade);
		utilisateurCourant = benevoleControlleur.getBenevole("ftremblay1234@gmail.com");
	}

    public Utilisateur getUtilisateurCourant() {
        return utilisateurCourant;
    }

	// afin d'obtenir le controlleur de benevoles
	public BenevoleControlleur getBenevoleControlleur() {
		return benevoleControlleur;
	}

	// afin d'obtenir le controlleur de Questions
	public OrganismeControlleur getOrganismeControlleur() {
		return organismeControlleur;
	}

}
