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
 * Robert Laganiere 2015
 */
package ca.uottawa.eecs.seg2505.benevolapp.controlleur;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ca.uottawa.eecs.seg2505.benevolapp.db.DBFacade;
import ca.uottawa.eecs.seg2505.benevolapp.model.Benevole;
import ca.uottawa.eecs.seg2505.benevolapp.model.offre.Offre;

public class BenevoleControlleur {

	protected DBFacade dbFacade = null;
	
	public BenevoleControlleur(DBFacade dbFacade) {
		this.dbFacade = dbFacade;
	}

	/** @Return Les offres à proposer à l'utilisateur courrant. **/
	public List<Offre> getOffresDisponibles() {
		// Assumant que l'utilisateur courrant est bien un bénévole.
		return dbFacade.getOffresDisponibles((Benevole) Delegateur.getInstance().getUtilisateurCourant());
	}

	/** @Return Le bénévole inscrit avec le courriel **/
	public Benevole getBenevole(String courriel) {
		return dbFacade.getBenevole(courriel);
	}

	public boolean SauvegarderBenevole(Benevole b){

		if (b.getPrenom().equals("") || b.getAge() == null || b.getAge() < 1 ||
				b.getVille().equals("") || b.getCodePostal().equals("")){
			//Des informations obligatoire sont manquente.
		}
		else if(!isValid(b.getCodePostal().replace(" ", "").toUpperCase(),"[A-Z]{1}\\d{1}[A-Z]{1}\\d{1}[A-Z]{1}\\d{1}")){
			//Le code postal n'est pas valide.
		}
		else if (!b.getNumeroTelephone().equals("") && !isValid(b.getNumeroTelephone(),"\\d{3}-\\d{3}-\\d{4}")){
			//Le numéro de téléphone n'est pas valide.
		}
		else{
			dbFacade.sauvegarderBenevole(b);
			return true;
		}
		return false;
	}

	private boolean isValid(String text, String PATTERN){
		Pattern pattern = Pattern.compile(PATTERN);
		Matcher matcher = pattern.matcher(text);
		return matcher.matches();
	}
}
