//@ Mikkel Sandell
package Files;

import Domain.Member;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
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
            }else {
                PrintStream ps = new PrintStream(new FileOutputStream(file, true));
                ps.println(member.getName() + ";" + member.getAge() + ";"
                        + member.getActivityForm() + ";" + member.getActivityLevel() + ";" + member.getSvømmediciplin());
                ps.close();
            }
        } catch (FileNotFoundException e) {
            throw new FileWriteException("Can't write to " + file, e);

        }

    }
    public ArrayList<String> getAllMembers(String Member_FILE_PATH){
            File file = new File(Member_FILE_PATH);

            try {
                Scanner scanner = new Scanner(file);
                ArrayList<String> members = new ArrayList<>();

                while (scanner.hasNext()) {
                    String foundLine = scanner.nextLine();
                    members.add(foundLine);
                }
                return members;
            } catch (FileNotFoundException e) {
                throw new FileReadException("Can't read from " + file, e);
            }
        }
    }

