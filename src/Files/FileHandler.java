//@ Mikkel Sandell, @Jonas Bækbo, @Johanne Riis-Weitling
package Files;

import Domain.Coach;
import Domain.CompetitionSwimmer;
import Domain.Member;
import Domain.DisciplineEnum;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;


public class FileHandler {

    public void saveNewMember(String FILE_PATH, Member member) {
        File file = new File(FILE_PATH);
        try {
            PrintStream ps = new PrintStream(new FileOutputStream(file, true));
            String newMember = member.saveMember();
            ps.println(newMember);
            ps.close();

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


    public void addCompetitonAndTimeAndDateTooMember(String FILE_PATH, ArrayList<Member> members) {
        File file = new File(FILE_PATH);
        clearFile(FILE_PATH);
        try {
            for (Member member : members) {
                PrintStream ps = new PrintStream(new FileOutputStream(file, true));
                CompetitionSwimmer competitionSwimmer = (CompetitionSwimmer) member;
                String memberCompetition = competitionSwimmer.saveMember();
                ps.println(memberCompetition);
                ps.close();
            }

        } catch (FileNotFoundException e) {
            throw new FileWriteException("Can't write to " + file, e);
        }
    }

    public ArrayList<Member> getAllMembers(String Member_FILE_PATH) {
        File file = new File(Member_FILE_PATH);

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
                    String diciplin = details[3];
                    DisciplineEnum disciplineEnum = DisciplineEnum.valueOf(diciplin);
                    String time = details[4];
                    LocalTime timeToAdd = LocalTime.parse(time);
                    String date = details[5];
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate dateToAdd = LocalDate.parse(date, formatter);
                    String competitonName = details[6];
                    String place = details[7];
                    String competitiondate = details[8];
                    LocalDate competitionDateToAdd = LocalDate.parse(competitiondate, formatter);
                    String competitonTime = details[9];
                    LocalTime competitontimeToAdd = LocalTime.parse(competitonTime);
                    if (competitontimeToAdd != null) {
                        CompetitionSwimmer competitionSwimmer = new CompetitionSwimmer(name, age, isActive, disciplineEnum, timeToAdd, dateToAdd, competitonName, place, competitionDateToAdd, competitontimeToAdd);
                        members.add(competitionSwimmer);
                    }
                } else if (details.length == 6) {
                    String diciplin = details[3];
                    DisciplineEnum disciplineEnum = DisciplineEnum.valueOf(diciplin);
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
                    String dicsipline = details[3];
                    DisciplineEnum disciplineEnum = DisciplineEnum.valueOf(dicsipline);
                    if (dicsipline != null) {
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


    public void saveNewCoach(String FILE_PATH, Coach coach) {
        File file = new File(FILE_PATH);
        try {
            PrintStream ps = new PrintStream(new FileOutputStream(file, true));
            String newCoach = coach.coachNameAndAge();
            ps.println(newCoach);
            ps.close();
        } catch (FileNotFoundException e) {
            throw new FileWriteException("Can't write to " + file, e);

        }
    }

}

