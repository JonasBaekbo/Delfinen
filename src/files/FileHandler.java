//@ Mikkel Sandell, Jonas Bækbo, Johanne Riis-Weitling, Adam Lasson
package files;

import domain.*;
import domain.Charge;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final String memberFile = "data/members.txt";
    private final String coachFile = "data/coach.txt";
    private final String subscriptionFile = "data/subCharge.csv";
    private final String swimResultFile = "data/swimResults.txt";

    public void saveNewMember(String stringForSaving) {
        File file = new File(memberFile);
        saveToFile(stringForSaving, file);
    }

    public void saveNewCoach(String stringForSaving) {
        File file = new File(coachFile);
        saveToFile(stringForSaving, file);
    }

    public void saveToSubscriptionFile(String stringForSaving) {
        File file = new File(subscriptionFile);
        saveToFile(stringForSaving, file);
    }

    public void saveSwimResult(String stringForSaving) {
        File file = new File(swimResultFile);
        saveToFile(stringForSaving, file);
    }

    public void saveToFile(String stringForSaving, File file) {
        try {
            PrintStream printStream = new PrintStream(new FileOutputStream(file, true));
            printStream.append(stringForSaving).append("\n");
            printStream.close();
        } catch (FileNotFoundException e) {
            throw new FileWriteException("Can't write to " + file, e);
        }
    }

    //https://intellipaat.com/community/69798/how-to-clear-a-text-file-without-deleting-it
    public void clearFile(String FILE_PATH) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH, false);
            PrintWriter pw = new PrintWriter(fw, false);
            pw.flush();
            pw.close();
            fw.close();
        } catch (Exception exception) {
            System.out.println("Exception have been caught");
        }
    }

    public ArrayList<Coach> getAllCoachs() {
        File file = new File(coachFile);
        try {
            Scanner scanner = new Scanner(file);
            ArrayList<Coach> coaches = new ArrayList<>();

            while (scanner.hasNext()) {
                String foundLine = scanner.nextLine();
                String[] details = foundLine.split(";");
                String name = details[0];
                String age = details[1];
                String disciplineAsString = details[2];
                DisciplineEnum discipline = DisciplineEnum.valueOf(disciplineAsString);
                Coach coach = new Coach(name, age, discipline);
                coaches.add(coach);
            }
            return coaches;
        } catch (FileNotFoundException e) {
            throw new FileReadException("Can't read from " + file, e);
        }
    }

    public ArrayList<Member> getAllMembers() {
        File file = new File(memberFile);

        try {
            Scanner scanner = new Scanner(file);
            ArrayList<Member> members = new ArrayList<>();

            while (scanner.hasNext()) {
                String foundLine = scanner.nextLine();
                String[] details = foundLine.split(";");
                String name = details[0];
                String age = details[1];
                boolean isActive = Boolean.parseBoolean(details[2]);

                if (details.length == 4) {
                    String discipline = details[3];
                    DisciplineEnum disciplineEnum = DisciplineEnum.valueOf(discipline);
                    if (discipline != null) {
                        CompetitionSwimmer competitionSwimmer = new CompetitionSwimmer(name, age, isActive, disciplineEnum);
                        members.add(competitionSwimmer);
                    }
                } else {
                    Member member = new Member(name, age, isActive);
                    members.add(member);
                }
            }
            return members;
        } catch (FileNotFoundException e) {
            throw new FileReadException("Can't read from " + file, e);
        }
    }

    public ArrayList<Training> getAllSavedTimes() {
        File file = new File(swimResultFile);
        try {
            Scanner scanner = new Scanner(file);
            ArrayList<Training> times = new ArrayList<>();
            ArrayList<Member> members = getAllMembers();

            while (scanner.hasNext()) {
                String foundLine = scanner.nextLine();
                String[] details = foundLine.split(";");
                String name = details[0];
                String dateAsString = details[1];
                LocalDate date = LocalDate.parse(dateAsString, dateFormatter);
                String timeAsString = details[2];
                LocalTime time = LocalTime.parse(timeAsString);

                Member member = findMemberByName(members, name);

                if (details.length > 3) {
                    String competitionName = details[3];
                    String placementAtCompetition = details[4];
                    Competition competition = new Competition(member, date, time, competitionName, placementAtCompetition);
                    times.add(competition);
                } else {
                    Training training = new Training(member, date, time);
                    times.add(training);
                }
            }
            return times;
        } catch (FileNotFoundException e) {
            throw new FileReadException("Can't read from " + file, e);
        }
    }

    public ArrayList<Charge> readSubFile() {
        try {
            ArrayList<Charge> charges = new ArrayList<>();
            File file = new File(subscriptionFile);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                String foundLine = scanner.nextLine();
                String[] details = foundLine.split(";");

                String chargeNumber = details[0];
                String name = details[1];
                String age = details[2];
                String isActive = details[3];
                String amount = details[4];
                String isPaid = details[5];

                Charge charge = new Charge(chargeNumber, name, age, isActive, amount, isPaid);

                charges.add(charge);
            }
            return charges;
        } catch (FileNotFoundException e) {
            throw new FileReadException("Can't read from subscription file", e);
        }
    }

    public Member findMemberByName(ArrayList<Member> members, String memberName) {
        Member foundMember = null;
        for (Member member : members) {
            if (memberName.equalsIgnoreCase(member.getName())) {
                foundMember = member;
            }
        }
        return foundMember;
    }

    public CompetitionSwimmer findCompetitionSwimmerByName(ArrayList<CompetitionSwimmer> competitionSwimmers, String memberName) {
        for (CompetitionSwimmer swimmer : competitionSwimmers) {
            if (memberName.equalsIgnoreCase(swimmer.getName())) {
                return swimmer;
            }
        }
        return null;
    }

    public ArrayList<CompetitionSwimmer> getCompetitionSwimmers() {
        ArrayList<CompetitionSwimmer> competitionSwimmers = new ArrayList<>();
        for (Member member : getAllMembers()) {
            if (member.getDiscipline() != null) {
                CompetitionSwimmer swimmer = (CompetitionSwimmer) member;
                competitionSwimmers.add(swimmer);
            }
        }
        return competitionSwimmers;
    }

    public String updatePaymentStatus(String input) {
        try {
            ArrayList<Charge> charges = readSubFile();
            clearFile(subscriptionFile);

            File file = new File(subscriptionFile);
            PrintStream ps = new PrintStream(new FileOutputStream(file, true));

            int numUpdates = 0;
            for (Charge charge : charges) {
                // Slå op på medlemsnavn eller fakturanummer
                if (charge.getName().equalsIgnoreCase(input) || (charge.getChargeNumber().equalsIgnoreCase(input))) {
                    charge.setIsPaid("betalt");
                    numUpdates++;
                }
                ps.println(charge);
            }
            ps.close();

            if (numUpdates == 0) {
                return "Kunne ikke finde noget, der matchede din søgning";
            } else if (numUpdates == 1) {
                return "Markerede fakturaen som betalt";
            } else {
                return "Markerede flere fakturaer som betalte";
            }
        } catch (FileNotFoundException e) {
            throw new FileReadException("Can't read from subscription file", e);
        }
    }

    public void updateActiveStatus(String input, boolean isActive) {
        String file = memberFile;
        try {
            ArrayList<Member> members = getAllMembers();
            clearFile(file);
            PrintStream ps = new PrintStream(new FileOutputStream(file, true));
            for (Member member : members) {
                if (member.getName().equalsIgnoreCase(input)) {
                    member.setActive(isActive);
                }
                ps.println(member.stringForSaving());
            }
            ps.close();
        } catch (FileNotFoundException e) {
            throw new FileReadException("Can't read member file", e);
        }
    }

    //Vi tæller linjerne i subscription-filen for at finde ud af hvad det næste fakturanummer skal være
    public int getNextInvoiceNumber() {
        File file = new File(subscriptionFile);
        int lines = 0;
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                scanner.nextLine();
                lines++;
            }
            return lines;
        } catch (FileNotFoundException e) {
            throw new FileReadException("Can't read from " + file, e);
        }
    }
}