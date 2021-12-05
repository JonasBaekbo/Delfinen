//@Jonas Bækbo, Mikkel Sandell

package controller;

import Domain.*;
import Files.FileHandler;
import ui.UserInterface;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class CoachController {
    private boolean isRunning = true;
    private FileHandler files = new FileHandler();
    private UserInterface ui = new UserInterface();
    private SwimTeam swimTeam = new SwimTeam();

    public void coachMenu(Controller controller) {
        while (isRunning) {
            ui.menuCoach();
            switch (ui.userInput()) {
                case "1" -> addTrainingResult();
                case "2" -> addTournamentResult();
                case "3" -> showTop5Swimmers((files.getAllMembers()));
                case "0" -> controller.backToMainMenu();
                default -> ui.printMessage("Du skal vælge et punkt fra menuen. Prøv venligst igen");
            }
        }
    }

    public void stop() {
        isRunning = false;
    }

    private void listCompetitionSwimmers(ArrayList<Member> members) {
        for (Member member : members) {
            CompetitionSwimmer competitionSwimmer = (CompetitionSwimmer) member;
            if (competitionSwimmer.getSwimDiscipline() != null) {
                ui.printMessage(member.toString());
            }
        }
    }

    private void addTournamentResult() {
        // TODO: omdøb metode i diagrammer
        ArrayList<Member> members = getMembersAsArrayList();
        listCompetitionSwimmers(members);
        ui.printMessage("Indtast medlemmets navn som har deltaget i et stævne:");
        String memberName = ui.userInput();
        CompetitionSwimmer foundMember = (CompetitionSwimmer) findMemberByName(members, memberName);
        if (foundMember != null) {
            // Konkurrencesvømmere skal have haft mindst 1 træningstid før de kan svømme i en turnering
            if (foundMember.getPracticeTime() != null) {
                ui.printMessage("Indtast navnet på stævnet:");
                String tournamentName = ui.userInput();
                ui.printMessage("Indtast placeringen til stævnet:");
                String tournamentPlace = ui.userInput();
                ui.printMessage("Indtast datoen for stævnet (DD/MM/ÅÅÅÅ) :");
                String tournamentDate = ui.userInput();
                LocalDate dateToAdd = foundMember.setCompetitionDate(tournamentDate);
                ui.printMessage("Indtast tiden til stævnet (HH:mm:ss):");
                String timeAsString = ui.userInput();
                LocalTime tournamentTime = LocalTime.parse(timeAsString);
                Competition competition = new Competition(tournamentName, tournamentPlace, dateToAdd, tournamentTime);
                foundMember.addCompetition(competition);
                files.saveMemberResult(members);
            } else {
                ui.printMessage("Det valgte medlem har ikke en træningstid");
            }
        } else {
            ui.printMessage("Det indtastede navn findes ikke, prøv igen");
        }
    }

    public void addTrainingResult() {
        // TODO: omdøb i diagrammer
        ArrayList<Member> members = getMembersAsArrayList();
        listCompetitionSwimmers(members);
        ui.printMessage("Indtast medlemmets navn som skal have tilføjet en ny tid:");
        String memberName = ui.userInput();
        CompetitionSwimmer foundMember = (CompetitionSwimmer) findMemberByName(members, memberName);
        if (foundMember != null) {
            ui.printMessage("Indtast medlemmets tid (HH:mm:ss)");
            String swimTimeAsString = ui.userInput();
            LocalTime swimTime = LocalTime.parse(swimTimeAsString);
            foundMember.setPracticeTime(swimTime);
            ui.printMessage("Indtast datoen for tiden (DD/MM/ÅÅÅÅ)");
            String swimDate = ui.userInput();
            foundMember.setPracticeDate(swimDate);
            files.saveMemberResult(members);
        } else {
            ui.printMessage("Det indtastede navn findes ikke, prøv igen");
        }
    }

    public ArrayList<Member> getMembersAsArrayList() {
        ArrayList<Member> members = files.getAllMembers();
        return members;
    }

    private Member findMemberByName(ArrayList<Member> members, String memberName) {
        Member foundMember = null;
        for (Member member : members) {
            if (memberName.equalsIgnoreCase(member.getName())) {
                foundMember = member;
            }
        }
        return foundMember;
    }

    public void listAllSwimmersUnder18(ArrayList<Member> membersList) {
        ui.printMessage("Konkurrencesvømmere i brystsvømning:");
        ArrayList<CompetitionSwimmer> brystUnder18 = swimTeam.listSwimmersSplitByAge(membersList, 18, DisciplineEnum.BRYSTSVØMNING, false);
        writeTop5Swimmers(brystUnder18);
        ui.printSeparator();
        ui.printMessage("Konkurrencesvømmere i butterfly:");
        ArrayList<CompetitionSwimmer> butterflyUnder18 = swimTeam.listSwimmersSplitByAge(membersList, 18, DisciplineEnum.BUTTERFLY, false);
        writeTop5Swimmers(butterflyUnder18);
        ui.printSeparator();
        ui.printMessage("Konkurrencesvømmere i crawl:");
        ArrayList<CompetitionSwimmer> crawlUnder18 = swimTeam.listSwimmersSplitByAge(membersList, 18, DisciplineEnum.CRAWL, false);
        writeTop5Swimmers(crawlUnder18);
        ui.printSeparator();
        ui.printMessage("Konkurrencesvømmere i rygcrawl:");
        ArrayList<CompetitionSwimmer> rygUnder18 = swimTeam.listSwimmersSplitByAge(membersList, 18, DisciplineEnum.RYGCRAWL, false);
        writeTop5Swimmers(rygUnder18);
    }

    public void listAllSwimmersOver18(ArrayList<Member> membersList) {
        ui.printMessage("Konkurrencesvømmere i brystsvømning:");
        ArrayList<CompetitionSwimmer> brystOver18 = swimTeam.listSwimmersSplitByAge(membersList, 18, DisciplineEnum.BRYSTSVØMNING, true);
        writeTop5Swimmers(brystOver18);
        ui.printSeparator();
        ui.printMessage("Konkurrencesvømmere i butterfly:");
        ArrayList<CompetitionSwimmer> butterflyOver18 = swimTeam.listSwimmersSplitByAge(membersList, 18, DisciplineEnum.BUTTERFLY, true);
        writeTop5Swimmers(butterflyOver18);
        ui.printSeparator();
        ui.printMessage("Konkurrencesvømmere i crawl:");
        ArrayList<CompetitionSwimmer> crawlOver18 = swimTeam.listSwimmersSplitByAge(membersList, 18, DisciplineEnum.CRAWL, true);
        writeTop5Swimmers(crawlOver18);
        ui.printSeparator();
        ui.printMessage("Konkurrencesvømmere i rygcrawl:");
        ArrayList<CompetitionSwimmer> rygOver18 = swimTeam.listSwimmersSplitByAge(membersList, 18, DisciplineEnum.RYGCRAWL, true);
        writeTop5Swimmers(rygOver18);
    }

    public void showTop5Swimmers(ArrayList<Member> membersList) {
        ui.printMessage("Konkurrencesvømmere under 18:");
        createTableHeader();
        listAllSwimmersUnder18(membersList);
        ui.printMessage("\nKonkurrencesvømmere over 18:");
        createTableHeader();
        listAllSwimmersOver18(membersList);
    }

    private void writeTop5Swimmers(ArrayList<CompetitionSwimmer> membersList) {
        ArrayList<CompetitionSwimmer> list = swimTeam.writeTop5Swimmers(membersList);
        for (CompetitionSwimmer competitionSwimmer : list) {
            createTableContents(competitionSwimmer);
        }
    }

    private void createTableHeader() {
        ui.printTableHeader();
    }

    private void createTableContents(CompetitionSwimmer competitionSwimmer) {
        String tableContent = competitionSwimmer.informationToTable();
        ui.printTableContents(tableContent);
    }
}
