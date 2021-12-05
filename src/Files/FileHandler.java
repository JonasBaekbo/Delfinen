//@ Mikkel Sandell, @Jonas Bækbo, @Johanne Riis-Weitling
package Files;

import Domain.Coach;
import Domain.CompetitionSwimmer;
import Domain.Member;
import Domain.DisciplineEnum;
import accounting.Charge;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    private final String memberFile = "data/members.txt";
    private final String coachFile = "data/coach.txt";
    private final String subscriptionFile ="data/subCharge.csv";

    public void saveNewMember(Member member) {
        File file = new File(memberFile);
        try {
            PrintStream ps = new PrintStream(new FileOutputStream(file, true));
            String newMember = member.getStringForSaving();
            ps.println(newMember);
            ps.close();
        } catch (FileNotFoundException e) {
            throw new FileWriteException("Can't write to " + file, e);
        }
    }

    public void saveNewCoach(Coach coach) {
        File file = new File(coachFile);
        try {
            PrintStream ps = new PrintStream(new FileOutputStream(file, true));
            String newCoach = coach.getStringForSaving();
            ps.println(newCoach);
            ps.close();
        } catch (FileNotFoundException e) {
            throw new FileWriteException("Can't write to " + file, e);

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
                if (details.length == 10) {
                    String discipline = details[3];
                    DisciplineEnum disciplineEnum = DisciplineEnum.valueOf(discipline);
                    String time = details[4];
                    LocalTime timeToAdd = LocalTime.parse(time);
                    String date = details[5];
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate dateToAdd = LocalDate.parse(date, formatter);
                    String competitionName = details[6];
                    String place = details[7];
                    String competitionDate = details[8];
                    LocalDate competitionDateToAdd = LocalDate.parse(competitionDate, formatter);
                    String competitionTime = details[9];
                    LocalTime competitionTimeToAdd = LocalTime.parse(competitionTime);
                    if (competitionTimeToAdd != null) {
                        CompetitionSwimmer competitionSwimmer = new CompetitionSwimmer(name, age, isActive, disciplineEnum, timeToAdd, dateToAdd, competitionName, place, competitionDateToAdd, competitionTimeToAdd);
                        members.add(competitionSwimmer);
                    }
                } else if (details.length == 6) {
                    String discipline = details[3];
                    DisciplineEnum disciplineEnum = DisciplineEnum.valueOf(discipline);
                    String time = details[4];
                    LocalTime timeToAdd = LocalTime.parse(time);
                    String date = details[5];
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate dateToAdd = LocalDate.parse(date, formatter);
                    if (timeToAdd != null) {
                        CompetitionSwimmer competitionSwimmer = new CompetitionSwimmer(name, age, isActive, disciplineEnum, timeToAdd, dateToAdd);
                        members.add(competitionSwimmer);
                    }

                } else if (details.length == 4) {
                    String discipline = details[3];
                    DisciplineEnum disciplineEnum = DisciplineEnum.valueOf(discipline);
                    if (discipline != null) {
                        CompetitionSwimmer competitionSwimmer = new CompetitionSwimmer(name, age, isActive, disciplineEnum);
                        members.add(competitionSwimmer);

                    } else {
                        Member m = new Member(name, age, isActive);
                        members.add(m);

                    }
                }
            }

            return members;
        } catch (FileNotFoundException e) {
            throw new FileReadException("Can't read from " + file, e);
        }
    }

    public void saveMemberResult(ArrayList<Member> members) {
        // TODO: omdøb i diagrammer
        File file = new File(memberFile);
        clearFile(memberFile);
        try {
            for (Member member : members) {
                PrintStream ps = new PrintStream(new FileOutputStream(file, true));
                CompetitionSwimmer competitionSwimmer = (CompetitionSwimmer) member;
                String memberCompetition = competitionSwimmer.getStringForSaving();
                ps.println(memberCompetition);
                ps.close();
            }
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

    public void saveToSubscriptionFile(String line) {
        File file = new File(subscriptionFile);
        try {
            PrintStream printStream = new PrintStream(new FileOutputStream(file, true));
            printStream.append(line).append("\n");
        } catch (FileNotFoundException e) {
            throw new FileReadException("Can't find the file you're looking for", e);
        }
    }

    public int countLinesInSubscriptionFile() {
        // TODO: omdøb i diagrammer
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

    public ArrayList<Charge> readSubFile() {
        try {
            ArrayList<Charge> result = new ArrayList<>();
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

                result.add(charge);
            }

            return result;

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
                ps.println(member.getStringForSaving());
            }
            ps.close();

        } catch (FileNotFoundException e) {
            throw new FileReadException("Can't read member file", e);
        }
    }
}

