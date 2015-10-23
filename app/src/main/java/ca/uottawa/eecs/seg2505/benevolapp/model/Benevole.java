//Made by group 15 for SEG2505A

import javax.swing.*;
import java.util.*;

public class Benevole {
    
    public static void main(String[] args) {
     Benevole aki = new Benevole("Akintola-Febrissy", "aakin013@uottawa.ca");
     aki.setSurname("Akinyele");
     aki.setCity("Ottawa");
     aki.setZip("K7G");
     aki.setAge(17);
     aki.setGender(true);
     aki.setPhoneNumber("911");
     aki.addCompetence("Batterie");
     aki.addCompetence("Laughing");
     aki.addDomaine_Interet("Software");
     aki.addDomaine_Interet("Computers");
     aki.setHoraire(new HoraireBenevole());
     aki.getHoraire().SetAvailability("Lundi", true, null);
     System.out.println(aki);
    }

    private String name, surname, email_address, city, zip, phoneNumber;
    private LinkedList<String> competences, domaine_interets;
    private Integer age;
    private Boolean isMale;
    private HoraireBenevole horaire;

    public Benevole(String name, String email_address) {
        this.name = name;
        this.email_address = email_address;
    }

    //getters
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail_address() {
        return email_address;
    }

    public String getCity() {
        return city;
    }

    public String getZip() {
        return zip;
    }

    public Boolean getGender() {
      return isMale == null ? null : isMale;
    }

    public String getPhoneNumber() {
      return phoneNumber;
    }
    
    public Integer getAge() {
        return age;
    }

    public LinkedList<String> getCompetences() {
        return competences;
    }

    public LinkedList<String> getDomaine_Interets() {
        return domaine_interets;
    }
    
    public HoraireBenevole getHoraire(){
        return horaire;
    }

    //setters
    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setGender(Boolean isMale) {
        this.isMale = isMale;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setCompetences(LinkedList<String> competences) {
        this.competences = competences;
    }


    public void setDomaine_Interets(LinkedList<String> domaine_interets) {
        this.domaine_interets = domaine_interets;
    }
    
    public void setHoraire(HoraireBenevole horaire){
        this.horaire = horaire;
    }

    //adders

    public void addCompetence(String competence) {
     
      if (competences == null){ competences = new LinkedList<String>();}
      
      competences.add(competence);

    }

    public void addDomaine_Interet(String domaine_interet) {

      if (domaine_interets == null){ domaine_interets = new LinkedList<String>();}
      
      domaine_interets.add(domaine_interet);

    }
    
    //Creates a string representation of the class
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Nom : "+name+"\n");
        sb.append("Prénom : "+surname+"\n");
        sb.append("Ville : "+city+"\n");
        sb.append("Code postal : "+zip+"\n");
        sb.append("Adresse email : "+email_address+"\n");
        sb.append("Numéro de Téléphone : "+phoneNumber+"\n");
        if (age != null){
          sb.append("Age : "+age+"\n");}
        if (isMale != null){
          sb.append("Sexe : "+(isMale ? "Homme" : "Femme" )+"\n");}
        if (competences != null && competences.size() > 0) {
         sb.append("---------------\n");
            sb.append("Liste des compétences : \n");
            for (String s : competences) {
                sb.append(s+"\n");
            }
            sb.append("\n");
        }
        if (domaine_interets != null && domaine_interets.size() > 0) {
         sb.append("---------------\n");
            sb.append("Liste des domaines/intérets : \n");
            for (String s : domaine_interets) {
                sb.append(s+"\n");
            }
            sb.append("\n");
        }
        if (horaire != null) {
         sb.append("---------------\n");
            sb.append(horaire.ToString());
            sb.append("\n");
        }
        return sb.toString();
    }


}
