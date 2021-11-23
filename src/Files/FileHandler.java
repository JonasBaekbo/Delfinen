//@ Mikkel Sandell
package Files;

import Domain.Member;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
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
    }

