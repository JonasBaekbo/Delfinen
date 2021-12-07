//@Jonas Bækbo, Mikkel Sandell

package controller;

import Domain.*;
import Files.FileHandler;

import ui.UserInterface;

import java.util.ArrayList;


public class CEOController {
    private boolean isRunning = true;
    private UserInterface ui = new UserInterface();
    private FileHandler files = new FileHandler();



    public void ceoMenu(Controller controller) {
        while (isRunning) {
            ui.menuCEO();
            switch (ui.userInput()) {
                case "1" -> createNewMember();
                case "2" -> changeActiveStatus();
                case "3" -> createCoach();
                case "4" -> printAllCoachs();
                case "0" -> controller.backToMainMenu();
                default -> ui.printMessage("Du skal vælge et punkt fra menuen. Prøv venligst igen");
            }
        }
    }



    public void stop() {
        isRunning = false;
    }

    private void createNewMember() {
        ui.printMessage("Indtast medlemmets navn: ");
        String name = ui.userInput();
        ui.printMessage("Indtast medlemmets alder: ");
        String age = ui.userInput();
        ui.printMessage("""
                Indtast medlemmets aktivitetsform:
                1) Motionssvømmer
                2) Konkurrencesvømmer""");
        String activityFormChosen = ui.userInput();
        String activityForm = chooseActivityForm(activityFormChosen);
        ui.printMessage("""
                Aktivt eller passivt medlem?
                1) Aktivt medlem
                2) Passivt medlem""");
        String activityLevelChosen = ui.userInput();
        boolean isActive = chooseActivityLevel(activityLevelChosen);
        if (!activityForm.equalsIgnoreCase("Motionist")) {
            createCompetitionSwimmer(name, age, isActive);
        } else {
            createNormalMember(name, age, isActive);
        }
    }

   /* private void createNormalMember(String name, String age, boolean isActive) {
        Member member = new Member(name, age, isActive);
                files.saveNewMember(member);
    }*/
    private void createNormalMember(String name, String age, boolean isActive) {
        Member member = new Member(name, age, isActive);
        String newMember =member.stringForSaving();
        files.saveNewMember(newMember);
    }

    private void createCompetitionSwimmer(String name, String age, boolean isActive) {
        ui.printMessage("""
                Indtast medlemmets svømmedisciplin
                1) Butterfly
                2) Crawl
                3) Rygcrawl
                4) Brystsvømning
                """);
        String swimDisciplineChosen = ui.userInput();
        DisciplineEnum swimDiscipline = chooseSwimDiscipline(swimDisciplineChosen);
        CompetitionSwimmer competitionSwimmer = new CompetitionSwimmer(name, age, isActive, swimDiscipline);
        String newCompetitionSwimmer = competitionSwimmer.stringForSaving();
        files.saveNewMember(newCompetitionSwimmer);

    }

    private String chooseActivityForm(String activityFormChosen) {
        String activityForm = "";
        if (activityFormChosen.equals("1")) {
            activityForm = "Motionist";
        } else if (activityFormChosen.equals("2")) {
            activityForm = "Konkurrence";
        }
        return activityForm;
    }

    private boolean chooseActivityLevel(String activityLevelChosen) {
        boolean isActive = true;
        if (activityLevelChosen.equals("1")) {
            isActive = true;
        } else if (activityLevelChosen.equals("2")) {
            isActive = false;
        }
        return isActive;
    }

    private DisciplineEnum chooseSwimDiscipline(String swimDisciplineChosen) {
        DisciplineEnum swimDiscipline = switch (swimDisciplineChosen) {
            case "1" -> DisciplineEnum.BUTTERFLY;
            case "2" -> DisciplineEnum.CRAWL;
            case "3" -> DisciplineEnum.RYGCRAWL;
            case "4" -> DisciplineEnum.BRYSTSVØMNING;
            default -> null;
        };
        return swimDiscipline;
    }

    private void createCoach() {
        ui.printMessage("Indtast trænerens navn: ");
        String name = ui.userInput();
        ui.printMessage("Indtast trænerens alder: ");
        String age = ui.userInput();
        ui.printMessage("""
                Indtast trænerens svømmedisciplin
                1) Butterfly
                2) Crawl
                3) Rygcrawl
                4) Brystsvømning
                """);
        String swimDisciplineChosen = ui.userInput();
        DisciplineEnum swimDiscipline = chooseSwimDiscipline(swimDisciplineChosen);
        Coach coach = new Coach(name, age,swimDiscipline);
        String newCoach=coach.stringForSaving();
        files.saveNewCoach(newCoach);
    }
//TODO: ny metode tilføj til diagrammer'
    private void printAllCoachs() {
       ArrayList<Coach> coaches= files.getAllCoachs();
        for (Coach coach : coaches) {
            String coachString = coach.toString();
            ui.printMessage(coachString);
        }
    }

    private void changeActiveStatus() {
        ui.printMessage("Skriv navnet på det medlem, der skal ændre status");
        String name = ui.userInput();
        ui.printMessage("""
                Hvad skal ny status være?
                1) Aktivt medlem
                2) Passivt medlem""");
        String choice = ui.userInput();
        boolean newLevel = chooseActivityLevel(choice);
        updateActiveStatus(name, newLevel);
        ui.printMessage("opdaterede status på " + name);
    }

    private void updateActiveStatus(String input, boolean isActive) {
        files.updateActiveStatus(input,isActive);
    }
}
