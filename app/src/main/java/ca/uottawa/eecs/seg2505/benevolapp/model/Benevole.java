package ca.uottawa.eecs.seg2505.benevolapp.model;

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
     System.out.println(aki);
    }

    private String name, surname, email_address, city, zip, phoneNumber;
    private LinkedList<String> competences;
    private String domaine_interets;
    private int age;
    private Boolean isMale;
    private LinkedList<Disponibilite> horaire;

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

    public String getDomaine_Interets() {
        return domaine_interets;
    }
    
    public LinkedList<Disponibilite> getHoraire(){
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


    public void setDomaine_Interets(String domaine_interets) {
        this.domaine_interets = domaine_interets;
    }

    //adders

    public void addDisponibilite(Disponibilite disponibilite) {

        if (horaire == null){ horaire = new LinkedList<Disponibilite>();}

        horaire.add(disponibilite);
    }

    public void addCompetence(String competence) {
     
      if (competences == null){ competences = new LinkedList<String>();}
      
      competences.add(competence);

    }


    //Creates a string representation of the class
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Nom : "+name+"\n");
        sb.append("Pr�nom : "+surname+"\n");
        sb.append("Ville : "+city+"\n");
        sb.append("Code postal : "+zip+"\n");
        sb.append("Adresse email : "+email_address+"\n");
        sb.append("Num�ro de T�l�phone : "+phoneNumber+"\n");
        if (age != null){
          sb.append("Age : "+age+"\n");}
        if (isMale != null){
          sb.append("Sexe : "+(isMale ? "Homme" : "Femme" )+"\n");}
        if (competences != null && competences.size() > 0) {
         sb.append("---------------\n");
            sb.append("Liste des comp�tences : \n");
            for (String s : competences) {
                sb.append(s+"\n");
            }
            sb.append("\n");
        }
        return sb.toString();
    }


}
