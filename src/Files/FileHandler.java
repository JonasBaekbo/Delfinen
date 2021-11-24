//@ Mikkel Sandell
package Files;

import Domain.Coach;
import Domain.Member;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class FileHandler {



    public void saveNewMember(String FILE_PATH, Member member){
        File file = new File(FILE_PATH);
        try {
            if ( member.getSvømmediciplin() == null){
                PrintStream ps = new PrintStream(new FileOutputStream(file, true));
                ps.println(member.getName() + ";" + member.getAge() + ";"
                        + member.getActivityForm() + ";" +member.getActivityLevel());
                ps.close();
            }else if (member.getTime() == null){
                PrintStream ps = new PrintStream(new FileOutputStream(file, true));
                ps.println(member.getName() + ";" + member.getAge() + ";"
                        + member.getActivityForm() + ";" + member.getActivityLevel() + ";" + member.getSvømmediciplin());
                ps.close();
            }else{
                PrintStream ps = new PrintStream(new FileOutputStream(file, true));
                ps.println(member.getName() + ";" + member.getAge() + ";"
                        + member.getActivityForm() + ";" + member.getActivityLevel() + ";" + member.getSvømmediciplin() + ";" +
                        member.getTime());
                ps.close();
            }
        } catch (FileNotFoundException e) {
            throw new FileWriteException("Can't write to " + file, e);

        }


    }

    //https://intellipaat.com/community/69798/how-to-clear-a-text-file-without-deleting-it
    public void clearFile(String FILE_PATH){
        try{
            FileWriter fw = new FileWriter(FILE_PATH, false);
            PrintWriter pw = new PrintWriter(fw, false);
            pw.flush();
            pw.close();
            fw.close();
        }catch(Exception exception){
            System.out.println("Exception have been caught");
        }
    }
    public void saveNewMember(String FILE_PATH, ArrayList<Member> members){
        File file = new File(FILE_PATH);
        clearFile(FILE_PATH);
        try {
            for (int i = 0; i < members.size(); i++) {
                if (members.get(i).getSvømmediciplin() == null) {
                    PrintStream ps = new PrintStream(new FileOutputStream(file, true));
                    ps.println(members.get(i).getName() + ";" + members.get(i).getAge() + ";"
                            + members.get(i).getActivityForm() + ";" + members.get(i).getActivityLevel());
                    ps.close();
                } else if (members.get(i).getTime() == null) {
                    PrintStream ps = new PrintStream(new FileOutputStream(file, true));
                    ps.println(members.get(i).getName() + ";" + members.get(i).getAge() + ";"
                            + members.get(i).getActivityForm() + ";" + members.get(i).getActivityLevel() + ";" + members.get(i).getSvømmediciplin());
                    ps.close();
                } else {
                    PrintStream ps = new PrintStream(new FileOutputStream(file, true));
                    ps.println(members.get(i).getName() + ";" + members.get(i).getAge() + ";"
                            + members.get(i).getActivityForm() + ";" + members.get(i).getActivityLevel() + ";" + members.get(i).getSvømmediciplin() + ";" +
                            members.get(i).getTime()+";" + members.get(i).getDate());
                    ps.close();
                }
            }
        } catch (FileNotFoundException e) {
            throw new FileWriteException("Can't write to " + file, e);

        }
    }

    public ArrayList<Member> getAllMembers(String Member_FILE_PATH){
            File file = new File(Member_FILE_PATH);

            try {
                Scanner scanner = new Scanner(file);
                ArrayList<Member> members = new ArrayList<>();

                while (scanner.hasNext()) {
                    String foundLine = scanner.nextLine();
                    String[] details = foundLine.split(";");
                    String name = details[0];
                    String age = details[1];
                    String activityForm = details[2];
                    String activityLevel = details[3];
                    if (Objects.equals(activityForm, "Konkurrence")){
                        String diciplin = details[4];
                        Member m = new Member(name, age, activityForm, activityLevel, diciplin);
                        members.add(m);

                    }else {
                    Member m = new Member(name, age, activityForm, activityLevel);
                    members.add(m);

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
        ps.println(coach.getName() + ";" + coach.getAge());
        ps.close();
        } catch (FileNotFoundException e) {
            throw new FileWriteException("Can't write to " + file, e);

        }
    }
}

