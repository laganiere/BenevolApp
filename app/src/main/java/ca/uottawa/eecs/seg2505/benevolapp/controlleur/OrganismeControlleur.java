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

import ca.uottawa.eecs.seg2505.benevolapp.db.DBFacade;

public class OrganismeControlleur {

	protected DBFacade dbFacade = null;
	
	public OrganismeControlleur(DBFacade dbFacade) {
		this.dbFacade = dbFacade;
	}
	
}
