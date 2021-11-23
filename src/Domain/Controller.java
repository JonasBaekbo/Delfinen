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
    private String svømmediciplin;
    private Member m;
    public void start() throws FileNotFoundException {
        ui.printMessage("Velkommen til Delfinen");
        ui.printMessage("-----------------------");
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
        ui.printMessage("Indtast medlemmets navn: ");
        String name = ui.userInput();
        ui.printMessage("Indtast medlemmets alder: ");
        String age = ui.userInput();
        ui.printMessage("""
                Indtast medlemmets aktivitetsform:
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
        if (activityForm.equals("Konkurrence")){
            ui.printMessage("""
                    Indtast medlemmets svømmediciplin
                    1) Butterfly
                    2) Crawl
                    3) Rygcrawl
                    4) Brystsvømning
                    """);
            String svømmediciplinChosen = ui.userInput();
            if (Objects.equals(svømmediciplinChosen,"1")){
                svømmediciplin = "Butterfly";
            } else if (Objects.equals(svømmediciplinChosen,"2")){
                svømmediciplin = "Crawl";
            } else if (Objects.equals(svømmediciplinChosen,"3")){
                svømmediciplin = "Rygcrawl";
            }else if (Objects.equals(svømmediciplinChosen,"4")){
                svømmediciplin = "Brystsvømning";
            }else {
                ui.printMessage("Ikke gyldigt indput");
            }

        }
        if (svømmediciplin != null){
            m = new Member(name,age,activityForm,activityLevel,svømmediciplin);
        }else{
            m = new Member(name,age,activityForm,activityLevel);
        }
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
