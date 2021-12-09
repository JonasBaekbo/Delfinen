//@Jonas Bækbo, Mikkel Sandell, Johanne Riis-Weitling

package controller;

import Domain.*;
import Files.FileHandler;
import ui.UserInterface;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
                case "3" -> showTop5Swimmers();
                case "4" -> showTimesForSwimmer();
                case "0" -> controller.backToMainMenu();
                default -> ui.printMessage("Du skal vælge et punkt fra menuen. Prøv venligst igen");
            }
        }
    }

    public void stop() {
        isRunning = false;
    }

    private void showTimesForSwimmer() {
        ArrayList<CompetitionSwimmer> members = files.getCompetitionSwimmers();
        listCompetitionSwimmers(members);
        ui.printMessage("Skriv navnet på svømmeren hvis tider du vil se");
        String name = ui.userInput();
        ArrayList<Training> times = swimTeam.getTimesForSwimmer(name);
        if (times.size() > 0) {
            for (Training time : times) {
                ui.printMessage(time.toString());
            }
        } else {
            ui.printMessage("Der er ikke registeret nogen tider på en svømmer, der matcher din søgning");
        }
    }

    private void listCompetitionSwimmers(ArrayList<CompetitionSwimmer> members) {
        for (CompetitionSwimmer member : members) {
            ui.printMessage(member.toString());
        }
    }

    private void addTournamentResult() {

        boolean running = true;
        ArrayList<CompetitionSwimmer> members = files.getCompetitionSwimmers();
        listCompetitionSwimmers(members);
        ui.printMessage("Indtast medlemmets navn som har deltaget i et stævne:");
        while (running) {
            try {
                String memberName = ui.userInput();
                CompetitionSwimmer foundMember = files.findCompetitionSwimmerByName(members, memberName);
                if (foundMember != null) {
                    // Konkurrencesvømmere skal have haft mindst 1 træningstid før de kan svømme i en turnering
                    ArrayList<Training> times = files.getAllSavedTimes();
                    if (foundMember.hasPracticeTime(times)) {
                        ui.printMessage("Indtast navnet på stævnet:");
                        String tournamentName = ui.userInput();

                        ui.printMessage("Indtast placeringen til stævnet:");
                        String tournamentPlace = ui.userInput();

                        ui.printMessage("Indtast tiden til stævnet (HH:mm:ss):");
                        String timeAsString = ui.userInput();
                        LocalTime tournamentTime = LocalTime.parse(timeAsString);

                        ui.printMessage("Indtast datoen for stævnet (DD/MM/ÅÅÅÅ) :");
                        String tournamentDateAsString = ui.userInput();
                        LocalDate tournamentDate = LocalDate.parse(tournamentDateAsString, dateFormatter);

                        Competition competition = new Competition(foundMember, tournamentDate, tournamentTime, tournamentName, tournamentPlace);
                        String newCompetition = competition.stringForSaving();
                        files.saveSwimResult(newCompetition);
                        running = false;
                    } else {
                        ui.printMessage("Det valgte medlem har ikke en træningstid");
                    }
                } else {
                    ui.printMessage("Det indtastede navn findes ikke, prøv igen");
                }
            } catch (DateTimeParseException e) {
                ui.printMessage("Kan ikke genkende tid/dato-format. Du bliver desværre nød til at starte forfra, med at taste navnet på svømmeren");
            }
        }

    }

    public void addTrainingResult() {
        boolean running = true;
        ArrayList<CompetitionSwimmer> members = files.getCompetitionSwimmers();
        listCompetitionSwimmers(members);
        ui.printMessage("Indtast medlemmets navn som skal have tilføjet en ny tid:");
        while (running) {
            try {
                String memberName = ui.userInput();
                CompetitionSwimmer foundMember = files.findCompetitionSwimmerByName(members, memberName);
                if (foundMember != null) {
                    ui.printMessage("Indtast medlemmets tid (HH:mm:ss)");
                    String swimTimeAsString = ui.userInput();
                    LocalTime swimTime = LocalTime.parse(swimTimeAsString);

                    ui.printMessage("Indtast datoen for tiden (DD/MM/ÅÅÅÅ)");
                    String swimDateAsString = ui.userInput();
                    LocalDate swimDate = LocalDate.parse(swimDateAsString, dateFormatter);

                    Training training = new Training(foundMember, swimDate, swimTime);
                    String newTraining = training.stringForSaving();
                    files.saveSwimResult(newTraining);
                    running = false;
                } else {
                    ui.printMessage("Det indtastede navn findes ikke, prøv igen");
                }
            } catch (DateTimeParseException e) {
                ui.printMessage("Kan ikke genkende tid/dato-format. Du bliver desværre nød til at starte forfra, med at taste navnet på svømmeren");
            }
        }
    }

    public void listAllSwimmersUnder18(ArrayList<Training> times) {
        ui.printMessage("Konkurrencesvømmere i brystsvømning:");
        ArrayList<Training> brystUnder18 = swimTeam.getDisciplineResultsSplitByAge(times, DisciplineEnum.BRYSTSVØMNING, 18, false);
        writeTop5Swimmers(brystUnder18);
        ui.printSeparator();
        ui.printMessage("Konkurrencesvømmere i butterfly:");
        ArrayList<Training> butterflyUnder18 = swimTeam.getDisciplineResultsSplitByAge(times, DisciplineEnum.BUTTERFLY, 18, false);
        writeTop5Swimmers(butterflyUnder18);
        ui.printSeparator();
        ui.printMessage("Konkurrencesvømmere i crawl:");
        ArrayList<Training> crawlUnder18 = swimTeam.getDisciplineResultsSplitByAge(times, DisciplineEnum.CRAWL, 18, false);
        writeTop5Swimmers(crawlUnder18);
        ui.printSeparator();
        ui.printMessage("Konkurrencesvømmere i rygcrawl:");
        ArrayList<Training> rygUnder18 = swimTeam.getDisciplineResultsSplitByAge(times, DisciplineEnum.RYGCRAWL, 18, false);
        writeTop5Swimmers(rygUnder18);
    }

    public void listAllSwimmersOver18(ArrayList<Training> times) {
        ui.printMessage("Konkurrencesvømmere i brystsvømning:");
        ArrayList<Training> brystOver18 = swimTeam.getDisciplineResultsSplitByAge(times, DisciplineEnum.BRYSTSVØMNING, 18, true);
        writeTop5Swimmers(brystOver18);
        ui.printSeparator();
        ui.printMessage("Konkurrencesvømmere i butterfly:");
        ArrayList<Training> butterflyOver18 = swimTeam.getDisciplineResultsSplitByAge(times, DisciplineEnum.BUTTERFLY, 18, true);
        writeTop5Swimmers(butterflyOver18);
        ui.printSeparator();
        ui.printMessage("Konkurrencesvømmere i crawl:");
        ArrayList<Training> crawlOver18 = swimTeam.getDisciplineResultsSplitByAge(times, DisciplineEnum.CRAWL, 18, true);
        writeTop5Swimmers(crawlOver18);
        ui.printSeparator();
        ui.printMessage("Konkurrencesvømmere i rygcrawl:");
        ArrayList<Training> rygOver18 = swimTeam.getDisciplineResultsSplitByAge(times, DisciplineEnum.RYGCRAWL, 18, true);
        writeTop5Swimmers(rygOver18);
    }

    public void showTop5Swimmers() {
        ArrayList<Training> times = files.getAllSavedTimes();
        ui.printMessage("Aktive konkurrencesvømmere under 18:");
        createTableHeader();
        listAllSwimmersUnder18(times);
        ui.printMessage("\nAktive konkurrencesvømmere over 18:");
        createTableHeader();
        listAllSwimmersOver18(times);
    }

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