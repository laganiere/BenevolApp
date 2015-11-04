package ca.uottawa.eecs.seg2505.benevolapp.model;

/**
 * Created by grp8 on 2015-10-02.
 */
public class Organisme {

	// The constructor with all the necessary values.
	public Organisme(String name, String streetNumber, String streetName, String unitNumber, String town, String postalCode, String email) {
		this.name = name;
		this.streetNumber = streetNumber;
		this.streetName = streetName;
		this.unitNumber = unitNumber;
		this.town = town;
		this.postalCode = postalCode; //Code postal canadien
		this.email = email;
	}

	// The constructor with everything 
	public Organisme(String name, String streetNumber, String streetName, String unitNumber, String postalCode, String town, String phoneNumber, String webSite, String email, String description, String organizationSize, String activitySector, String founder) {
		this.name = name;
		this.streetNumber = streetNumber;
		this.streetName = streetName;
		this.unitNumber = unitNumber;
		this.postalCode = postalCode;
		this.town = town;
		this.phoneNumber = phoneNumber;
		this.webSite = webSite;
		this.email = email;
		this.description = description;
		this.organizationSize = organizationSize;
		this.activitySector = activitySector;
		this.founder = founder;
	}

	// In the case of an online Organization searching for volunteers worldwide
	// through the web
	public Organisme(String name, String webSite, String email) {
		this.name = name;
		this.webSite = webSite;
		this.email = email;
	}

	// All values
	private String name;
	private String streetNumber;
	private String streetName;
	private String unitNumber;
	private String postalCode;
	private String town;
	private String phoneNumber;
	private String webSite;
	private String email;
	private String description;
	private String organizationSize;
	private String activitySector;
	private String founder;

	// The get and set of StreetName
	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	// The get and set of Name
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// The get and set of Street Number
	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	// The get and set of Postal Code
	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	// The get and set of Unit Number
	public String getUnitNumber() {
		return unitNumber;
	}

	public void setUnitNumber(String unitNumber) {
		this.unitNumber = unitNumber;
	}

	// The get and set of town
	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	// The get and set of web site
	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	// The get and set of email
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// The get and set of phone number
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	// The get and set of description
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	// To add to the original description

	// The get and set of the size of the organization
	public String getOrganizationSize() {
		return organizationSize;
	}

	public void setOrganizationSize(String sizeOrganization) {
		this.organizationSize = sizeOrganization;
	}

	// The get and set of the activity Sector
	public String getActivitySector() {
		return activitySector;
	}

	public void setActivitySector(String activitySector) {
		this.activitySector = activitySector;
	}

	// The get and set of the founder
	public String getFounder() {
		return founder;
	}

	public void setFounder(String founder) {
		this.founder = founder;
	}

	// the get of address that returns the street number, street name, unit
	// number if available, postal code with the town.
	public String getAddress() {
		return (streetNumber + " " + streetName + ", " + unitNumber + ", " + town + ", " + postalCode);
	}
}
