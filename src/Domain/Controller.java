package Domain;


import ui.UserInterface;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Controller {
    boolean isRunning = true;
    private UserInterface ui = new UserInterface();
    private ArrayList<Member> members = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    public void start() throws FileNotFoundException {

        while(isRunning){
            ui.menu();
            switch (ui.userInput()){
                case "0" -> exit();
               case "1" -> createNewMember();
               case "2" -> ui.printMessage(showAllMembers());

            }
        }
    }

    public void createNewMember(){
        ui.printMessage("indtast brugerens navn: ");
        String name = ui.userInput();
        ui.printMessage("indtast alderen på brugeren: ");
        String age = ui.userInput();
        ui.printMessage("""
                indtast activityformen på brugeren: 
                1) Motionssvømmer
                2) Konkurrencesvømmer""");
        String activityFormChosen = ui.userInput();
        String activityForm = "";
        if (Objects.equals(activityFormChosen, "1")){
            activityForm = "Motionist";
        }else if (Objects.equals(activityFormChosen, "2")){
            activityForm = "Konkurrence";
        }
        ui.printMessage("""
                Aktivt eller passivt medlem?
                1) Aktivt medlem
                2) Passivt medlem""");
        String activityLevelChosen = ui.userInput();
        String activityLevel = "";
        if (Objects.equals(activityLevelChosen, "1")){
            activityLevel = "Aktivt";
        }else if (Objects.equals(activityLevelChosen, "2")){
            activityLevel = "Passivt";
        }
        Member m = new Member(name,age,activityForm,activityLevel);
        members.add(m);
    }

    // SKAL SLETTES SENERE! KUN TIL TEST!
    public String showAllMembers(){
        return members.toString();
    }

    public void exit(){
        isRunning = false;
    }
}
