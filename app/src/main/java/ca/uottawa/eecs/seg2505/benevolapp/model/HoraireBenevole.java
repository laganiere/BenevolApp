//Made by group 15 for SEG2505A

//To be used with the benevole class
//This class was created to hold the data about the schedule of the volunter's schedule.
// <param name="isAvailable"> array of booleans to inform on which day a volunter is available. </param>
// <param name="isAvailableDayOrNight"> array of Booleans to inform whether the volunter is available
// to work during the day or the night. True stands for day and false stands for night. Null stands for both. </param>

public class Horaire{

private boolean[] isAvailable = new boolean[7];  
private Boolean[] isAvailableDayOrNight = new Boolean[7];

//Setters for the availability of a volunter. First boolean parameter corresponds to the day on which to change the availability 
//and the second Boolean parameter corresponds to the period of the day we wish to know if he is available during
public void SetAvailability(int availableDay,boolean isAvailable, Boolean isAvailableDayOrNight){

  this.isAvailable[availableDay] = isAvailable;
  this.isAvailableDayOrNight[availableDay] = isAvailableDayOrNight;
}

//Setter using the name of the day instead of its position
public void SetAvailability(String availableDay, boolean isAvailable, Boolean isAvailableDayOrNight){

  SetAvailability(StringToDay(availableDay), isAvailable, isAvailableDayOrNight);
}


//Checks if the volunter is available on a specific day at a specific period
public boolean IsAvailable(int availableDay, Boolean isAvailableDayOrNight){
 
  return isAvailable[availableDay] && (this.isAvailableDayOrNight[availableDay] == null || this.isAvailableDayOrNight[availableDay] == isAvailableDayOrNight);
}

//Checker using the name of the day instead of its position
public boolean IsAvailable(String availableDay, Boolean isAvailableDayOrNight){

  return IsAvailable(StringToDay(availableDay), isAvailableDayOrNight);
}

//Associates a day of the week with it's position within the week, with 0 being monday and 6 being sunday
public static int StringToDay(String stringifiedDayToConvert){

stringifiedDayToConvert = stringifiedDayToConvert.toLowerCase();
if (stringifiedDayToConvert.equals("lundi")) return 0;
else if (stringifiedDayToConvert.equals("mardi")) return 1;
else if (stringifiedDayToConvert.equals("mercredi")) return 2;
else if (stringifiedDayToConvert.equals("jeudi")) return 3;
else if (stringifiedDayToConvert.equals("vendredi")) return 4;
else if (stringifiedDayToConvert.equals("samedi")) return 5;
else if (stringifiedDayToConvert.equals("dimanche")) return 6;
else throw new java.lang.IllegalArgumentException();
}

//Creates a string representation of the class
public String ToString(){
  
  String stringifiedHoraire = "Horaire de disponibilités: \n";
  int count=0;
  String stringToAdd;
  while (count<7){
    if (isAvailable[count]){           
      switch(count){      
        case 0: stringifiedHoraire = stringifiedHoraire + "Lundi: ";
        break;
        case 1: stringifiedHoraire = stringifiedHoraire + "Mardi: ";
        break;
        case 2: stringifiedHoraire = stringifiedHoraire + "Mercredi: ";
        break;
        case 3: stringifiedHoraire = stringifiedHoraire + "Jeudi: ";
        break;
        case 4: stringifiedHoraire = stringifiedHoraire + "Vendredi: ";
        break;
        case 5: stringifiedHoraire = stringifiedHoraire + "Samedi: ";
        break;
        case 6: stringifiedHoraire = stringifiedHoraire + "Dimanche: ";
        break;
        default: break;}
      stringToAdd = isAvailableDayOrNight[count] == null ? "Toute la journée" : (isAvailableDayOrNight[count] ? "Seulement pendant le jour" : "Seulement pendant la nuit");
      stringifiedHoraire = stringifiedHoraire + stringToAdd + "\n";  
    }
  count++;  
  }
  return stringifiedHoraire;  
}

//Test method to check if the class works as expected
public static void main (String[] args){

  Horaire a = new Horaire();
  a.SetAvailability(0, true, null);
  a.SetAvailability(5, true, false);
  a.SetAvailability(3, true, true);
  a.SetAvailability("mardi", true, null);
  System.out.println(a.ToString());
}
}