//@Jonas Bækbo, Mikkel Sandell

package controller;

import Domain.*;
import Files.FileHandler;
import ui.UserInterface;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CoachController {
    private boolean isRunning = true;
    private FileHandler files = new FileHandler();
    private UserInterface ui = new UserInterface();
    private SwimTeam swimTeam = new SwimTeam();
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    public void coachMenu(Controller controller) {
        while (isRunning) {
            ui.menuCoach();
            switch (ui.userInput()) {
                case "1" -> addTrainingResult();
                case "2" -> addTournamentResult();
                case "3" -> showTop5Times();
                case "9" -> showTimesForSwimmer();
                case "0" -> controller.backToMainMenu();
                default -> ui.printMessage("Du skal vælge et punkt fra menuen. Prøv venligst igen");
            }
        }
    }

    private void showTimesForSwimmer() {
        ui.printMessage("Skriv navnet på svømmeren");
        String name = ui.userInput();
        ArrayList<Training> times = swimTeam.getTimesForSwimmer(name);
        for (Training time : times) {
            ui.printMessage(time.toString());
        }
    }

    public void stop() {
        isRunning = false;
    }

    private void listCompetitionSwimmers(ArrayList<CompetitionSwimmer> members) {
        for (CompetitionSwimmer member : members) {
            ui.printMessage(member.toString());
        }
    }
    //TODO: denne skal ændres
    private void addTournamentResult() {
        ArrayList<CompetitionSwimmer> members = files.getCompetitionSwimmers();
        listCompetitionSwimmers(members);
        ui.printMessage("Indtast medlemmets navn som har deltaget i et stævne:");
        String memberName = ui.userInput();
        CompetitionSwimmer foundMember = files.findCompetitionSwimmerByName(members, memberName);
        if (foundMember != null) {
            // Konkurrencesvømmere skal have haft mindst 1 træningstid før de kan svømme i en turnering
            if (foundMember.hasPracticeTime() == true) {
                ui.printMessage("Indtast navnet på stævnet:");
                String tournamentName = ui.userInput();

                ui.printMessage("Indtast placeringen til stævnet:");
                String tournamentPlace = ui.userInput();

                ui.printMessage("Indtast datoen for stævnet (DD/MM/ÅÅÅÅ) :");
                String tournamentDateAsString = ui.userInput();
                LocalDate tournamentDate = LocalDate.parse(tournamentDateAsString, dateFormatter);

                //LocalDate dateToAdd = foundMember.setCompetitionDate(tournamentDate);
                ui.printMessage("Indtast tiden til stævnet (HH:mm:ss):");
                String timeAsString = ui.userInput();
                LocalTime tournamentTime = LocalTime.parse(timeAsString);

                Competition competition = new Competition(foundMember, tournamentDate,tournamentTime,tournamentName,tournamentPlace);
                files.saveSwimResultResult(competition);
                //foundMember.addCompetition(competition);
                //files.saveMemberResult(members);
            } else {
                ui.printMessage("Det valgte medlem har ikke en træningstid");
            }
        } else {
            ui.printMessage("Det indtastede navn findes ikke, prøv igen");
        }
    }
//TODO: denne skal ændres
    public void addTrainingResult() {
        ArrayList<CompetitionSwimmer> members = files.getCompetitionSwimmers();
        listCompetitionSwimmers(members);
        ui.printMessage("Indtast medlemmets navn som skal have tilføjet en ny tid:");
        String memberName = ui.userInput();
        CompetitionSwimmer foundMember = files.findCompetitionSwimmerByName(members, memberName);
        if (foundMember != null) {
            ui.printMessage("Indtast medlemmets tid (HH:mm:ss)");
            String swimTimeAsString = ui.userInput();
            LocalTime swimTime = LocalTime.parse(swimTimeAsString);
            //foundMember.setPracticeTime(swimTime);
            ui.printMessage("Indtast datoen for tiden (DD/MM/ÅÅÅÅ)");
            String swimDateAsString = ui.userInput();
            LocalDate swimDate = LocalDate.parse(swimDateAsString,dateFormatter);
            //foundMember.setPracticeDate(swimDate);
            //files.saveMemberResult(members);
            Training training = new Training(foundMember, swimDate, swimTime);
            files.saveSwimResultResult(training);
        } else {
            ui.printMessage("Det indtastede navn findes ikke, prøv igen");
        }
    }

    public ArrayList<Member> getMembersAsArrayList() {
        ArrayList<Member> members = files.getAllMembers();
        return members;
    }

    //TODO: denne skal ændres
    public void listAllSwimmersUnder18() {
        ui.printMessage("Konkurrencesvømmere i brystsvømning:");
        ArrayList<Training> brystUnder18 = swimTeam.getDisciplineResultsSplitByAge(DisciplineEnum.BRYSTSVØMNING, 18, false);
        writeTop5Swimmers(brystUnder18);
        ui.printSeparator();
        ui.printMessage("Konkurrencesvømmere i butterfly:");
        ArrayList<Training> butterflyUnder18 = swimTeam.getDisciplineResultsSplitByAge(DisciplineEnum.BUTTERFLY, 18, false);
        writeTop5Swimmers(butterflyUnder18);
        ui.printSeparator();
        ui.printMessage("Konkurrencesvømmere i crawl:");
        ArrayList<Training> crawlUnder18 = swimTeam.getDisciplineResultsSplitByAge(DisciplineEnum.CRAWL, 18, false);
        writeTop5Swimmers(crawlUnder18);
        ui.printSeparator();
        ui.printMessage("Konkurrencesvømmere i rygcrawl:");
        ArrayList<Training> rygUnder18 = swimTeam.getDisciplineResultsSplitByAge(DisciplineEnum.RYGCRAWL, 18, false);
        writeTop5Swimmers(rygUnder18);
    }
    //TODO: denne skal ændres
    public void listAllSwimmersOver18() {
        ui.printMessage("Konkurrencesvømmere i brystsvømning:");
        ArrayList<Training> brystOver18 = swimTeam.getDisciplineResultsSplitByAge(DisciplineEnum.BRYSTSVØMNING, 18, true);
        writeTop5Swimmers(brystOver18);
        ui.printSeparator();
        ui.printMessage("Konkurrencesvømmere i butterfly:");
        ArrayList<Training> butterflyOver18 = swimTeam.getDisciplineResultsSplitByAge(DisciplineEnum.BUTTERFLY, 18, true);
        writeTop5Swimmers(butterflyOver18);
        ui.printSeparator();
        ui.printMessage("Konkurrencesvømmere i crawl:");
        ArrayList<Training> crawlOver18 = swimTeam.getDisciplineResultsSplitByAge(DisciplineEnum.CRAWL, 18, true);
        writeTop5Swimmers(crawlOver18);
        ui.printSeparator();
        ui.printMessage("Konkurrencesvømmere i rygcrawl:");
        ArrayList<Training> rygOver18 = swimTeam.getDisciplineResultsSplitByAge(DisciplineEnum.RYGCRAWL, 18, true);
        writeTop5Swimmers(rygOver18);
    }
    //TODO: denne skal ændres
    public void showTop5Times() {
        ui.printMessage("Konkurrencesvømmere under 18:");
        createTableHeader();
        listAllSwimmersUnder18();
        ui.printMessage("\nKonkurrencesvømmere over 18:");
        createTableHeader();
        listAllSwimmersOver18();
    }
    //TODO: denne skal ændres
    private void writeTop5Swimmers(ArrayList<Training> times) {
        ArrayList<Training> list = swimTeam.writeTop5Times(times);
        for (Training result : list) {
            createTableContents(result);
        }
    }

    private void createTableHeader() {
        ui.printTableHeader();
    }

    private void createTableContents(Training training) {
        String tableContent = training.informationToTable();
        ui.printTableContents(tableContent);
    }
}
