package controller;

import Domain.*;
import Files.FileHandler;
import Files.FilePath;
import Files.FileReadException;
import ui.UserInterface;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class CEOController {
    boolean isRunning = true;
    FileHandler files = new FileHandler();
    private final FilePath filePath =new FilePath();
    private UserInterface ui = new UserInterface();

    public void ceoMenu(Controller controller) {
        while (isRunning) {
            ui.menuCEO();
            switch (ui.userInput()) {
                case "1" -> createNewMember();
                case "2" -> changeActiveStatus();
                case "3" -> createCoach();
                case "0" -> controller.backTooMainMenu();
                default -> ui.printMessage("Du skal vælge et punkt fra menuen. Prøv venligst igen");
            }
        }
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
        //TODO ændre den til at tage "2" som input og ikke lave denne om til string?
        String activityForm = chooseActivityForm(activityFormChosen);
        ui.printMessage("""
                Aktivt eller passivt medlem?
                1) Aktivt medlem
                2) Passivt medlem""");
        String activityLevelChosen = ui.userInput();
        boolean isActive = chooseActivityLevel(activityLevelChosen);
        //TODO ændre den til at tage "2" som input?
        if (!activityForm.equalsIgnoreCase("Motionist")) {
            createCompetitionSwimmer(name, age, isActive);

        } else {
            createNormalMember(name, age, isActive);
        }
    }

    private void createNormalMember(String name, String age, boolean isActive){
        Member member = new Member(name, age, isActive);
        files.saveNewMember(filePath.MEMBER_PATH, member);
    }

    private void createCompetitionSwimmer(String name, String age, boolean isActive){
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
        files.saveNewMember(filePath.MEMBER_PATH, competitionSwimmer);

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
        boolean isActive=true;
        if (activityLevelChosen.equals("1")) {
            isActive= true;
        } else if (activityLevelChosen.equals("2")) {
            isActive= false;
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
        ui.printMessage("Indtast træneres navn: ");
        String name = ui.userInput();
        ui.printMessage("Indtast træneres alder: ");
        String age = ui.userInput();
        Coach coach = new Coach(name, age);
        files.saveNewCoach(filePath.COACH_PATH, coach);
    }

    //TODO: ny funktion tilføj til diagrammer
    private void changeActiveStatus(){
        ui.printMessage("Skriv navnet på det medlem der skal ændre status");
        String name = ui.userInput();
        ui.printMessage("""
                Hvad skal ny status være?
                1) Aktivt medlem
                2) Passivt medlem""");
        String choose= ui.userInput();
        boolean newLevel= chooseActivityLevel(choose);
        updateActiveStatus(name,newLevel);


    }

    public void updateActiveStatus(String input,boolean isActive) {
        String file= filePath.MEMBER_PATH;
        try {
            ArrayList<Member> members =getMembersAsArrayList(file);
            files.clearFile(file);
            PrintStream ps = new PrintStream(new FileOutputStream(file, true));

            for (Member member : members) {
                if (member.getName().equalsIgnoreCase(input)) {
                    member.setActive(isActive);
                }
                ps.println(member.saveMember());
            }
            ps.close();
            ui.printMessage("opdaterede status på "+input);

        } catch (FileNotFoundException e) {
            throw new FileReadException("Can't read from subscription file", e);
        }
    }

    private ArrayList<Member> getMembersAsArrayList(String filePath){
        ArrayList<Member> members= files.getAllMembers(filePath);
        return members;
    }

    }


