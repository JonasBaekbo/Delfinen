//@Johanne Riis-Weitling
package accounting;

import Domain.Member;
import Files.FileHandler;
import Files.FileReadException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class SubscriptionFee {
    private Member member;
    private double below18Fee = 1000;
    private double above18Fee = 1600;
    private double passiveFee = 500;
    private double seniorFeeDiscount = 0.75;//der er 25% rabat for medlemmer over 60
    double subscriptionFee;
    FileHandler files = new FileHandler();
    private static final String SUBSCRIPTION_FILE = "data/subCharge.csv";
    private static final String MEMBER_FILE = "data/members.txt";


    public double getSubscriptionFee(Member member) {
        if (!isMemberActive(member)) {
            subscriptionFee = passiveFee;
        } else {
            subscriptionFee = calculateSubscriptionFee(member);
        }
        return subscriptionFee;
    }

    private boolean isMemberActive(Member member) {
        boolean isActive;
        isActive = member.getActivityLevel().equals("Aktivt");
        return isActive;
    }

    private double calculateSubscriptionFee(Member member) {
        int age = getAgeAsInt(member);
        if (age < 18) {
            subscriptionFee = below18Fee;
        } else if (age >= 60) {
            subscriptionFee = above18Fee * seniorFeeDiscount;
        } else {
            subscriptionFee = above18Fee;
        }
        return subscriptionFee;
    }

    public double getExpectedSubscriptionFeeTotal(ArrayList<Member> memberArrayList) {
        double totalSubscription = 0;

        for (Member member : memberArrayList){
            totalSubscription += getSubscriptionFee(member);
        }
        return totalSubscription;
    }

        public int getAgeAsInt(Member member){
            return Integer.parseInt(member.getAge());
        }


        public ArrayList<String> memberMissingPayment() throws FileNotFoundException {
            ArrayList<String> members = new ArrayList<>();
            ArrayList<Charge> charges = readSubFile();

            for (int i = 0; i < charges.size(); i++) {
                Charge charge = charges.get(i);
                if (Objects.equals(charge.getIsPaid(), "ikke betalt")) {
                    members.add(charge.toString());
                }
            }
            return members;
        }

        //TODO: se på det duplikerede i Charge metoder
    //TODO: fejlbesked hvis input ikke kan findes
    public void makeOneSubscriptionCharge(String memberName) throws FileNotFoundException {
        ArrayList<Member> members = files.getAllMembers(MEMBER_FILE);
        for (int i = 0; i < members.size(); i++) {
            Member member = members.get(i);
            if (member.getName().equalsIgnoreCase(memberName)) {
                double amount = getSubscriptionFee(member);
                String line = member.getName() + "; " + member.getAge() + ";" + member.getActivityLevel() + ";" + amount + ";" + "ikke betalt";
                saveToCSV(SUBSCRIPTION_FILE, line);
            }
        }
    }

        public void makeSubscriptionChargeForAllMembers() throws FileNotFoundException {
            ArrayList<Member> members = files.getAllMembers(MEMBER_FILE);
            for (int i = 0; i < members.size(); i++) {
                Member member = members.get(i);
                double amount = getSubscriptionFee(member);
                String line = member.getName() + "; " + member.getAge() + ";" + member.getActivityLevel() + ";" + amount + ";" + "ikke betalt";
                saveToCSV(SUBSCRIPTION_FILE, line);
            }
        }

        private void saveToCSV(String filePath, String line) throws FileNotFoundException {
            PrintStream printStream = new PrintStream(new FileOutputStream(filePath, true));
            printStream.append(line).append("\n");
        }

        //TODO: fejlbesked hvis input ikke kan findes
        public void updatePaymentStatus(String memberName) {
            try {
                // læs filen og gem indhold i arraylist
                ArrayList<Charge> charges = readSubFile();

                // ryd filen
                files.clearFile(SUBSCRIPTION_FILE);

                // skriv filen forfra
                File file = new File(SUBSCRIPTION_FILE);
                PrintStream ps = new PrintStream(new FileOutputStream(file, true));

                // skriv hvert træk i filen
                for (int i = 0; i < charges.size(); i++) {
                    Charge charge = charges.get(i);
                    if (charge.getName().equalsIgnoreCase(memberName)) {
                        charge.setIsPaid("betalt");
                    }
                    ps.println(charge);

                }
                ps.close();
            } catch (FileNotFoundException e) {
                throw new FileReadException("Can't read from subscription file", e);
            }
        }

        public ArrayList<Charge> readSubFile() throws FileNotFoundException {
            ArrayList<Charge> result = new ArrayList<>();
            File file = new File(SUBSCRIPTION_FILE);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                String foundLine = scanner.nextLine();
                String[] details = foundLine.split(";");

                String name = details[0];
                String age = details[1];
                String activityLevel = details[2];
                String amount = details[3];
                String isPaid = details[4];

                Charge charge = new Charge(name, age, activityLevel, amount, isPaid);

                result.add(charge);
            }

            return result;
        }

    @Override
    public String toString() {
        return "Medlem: " + member + ".\nKontingent: " + getSubscriptionFee(member);
    }
}

