package Domain;

import Files.FileHandler;
import accounting.SubscriptionFee;
import ui.UserInterface;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Controller {
    FileHandler files = new FileHandler();
    private static final String MEMBER_FILE = "data/members.txt";
    boolean isRunning = true;
    private UserInterface ui = new UserInterface();
    private SubscriptionFee subFee = new SubscriptionFee();
    private ArrayList<Member> members = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    //private String svømmediciplin;
    private Member m;

    public void start() throws FileNotFoundException {
        ui.printMessage("Velkommen til Delfinen");
        ui.printMessage("-----------------------");
        while (isRunning) {
            ui.menu();
            switch (ui.userInput()) {
                case "0" -> exit();
                case "1" -> createNewMember();
                case "2" -> calculateExpectedSubFeeTotal();
                case "3"-> sowMissingPayments();
                case "5" -> showTop5Swimmers(files.getAllMembers(MEMBER_FILE));
                case "4" -> addTimeTooMember();
                case "8" -> chargeSubscriptionFee();
                case "9" ->markAsPaid();
            }
        }
    }




    public void createNewMember() {
        ui.printMessage("Indtast medlemmets navn: ");
        String name = ui.userInput();
        ui.printMessage("Indtast medlemmets alder: ");
        String age = ui.userInput();
        ui.printMessage("""
                Indtast medlemmets aktivitetsform:
                1) Motionssvømmer
                2) Konkurrencesvømmer""");
        String activityFormChosen = ui.userInput();
        String activityForm = "";
        if (Objects.equals(activityFormChosen, "1")) {
            activityForm = "Motionist";
        } else if (Objects.equals(activityFormChosen, "2")) {
            activityForm = "Konkurrence";
        }
        ui.printMessage("""
                Aktivt eller passivt medlem?
                1) Aktivt medlem
                2) Passivt medlem""");
        String activityLevelChosen = ui.userInput();
        String activityLevel = "";
        if (Objects.equals(activityLevelChosen, "1")) {
            activityLevel = "Aktivt";
        } else if (Objects.equals(activityLevelChosen, "2")) {
            activityLevel = "Passivt";
        }
        String svømmediciplin = "";
        if (activityForm.equals("Konkurrence")) {
            ui.printMessage("""
                    Indtast medlemmets svømmediciplin
                    1) Butterfly
                    2) Crawl
                    3) Rygcrawl
                    4) Brystsvømning
                    """);
            String svømmediciplinChosen = ui.userInput();
            if (Objects.equals(svømmediciplinChosen, "1")) {
                svømmediciplin = "Butterfly";
            } else if (Objects.equals(svømmediciplinChosen, "2")) {
                svømmediciplin = "Crawl";
            } else if (Objects.equals(svømmediciplinChosen, "3")) {
                svømmediciplin = "Rygcrawl";
            } else if (Objects.equals(svømmediciplinChosen, "4")) {
                svømmediciplin = "Brystsvømning";
            } else {
                ui.printMessage("Ikke gyldigt indput");
            }

        }
        if (!svømmediciplin.equals("")) {
            Member m = new Member(name, age, activityForm, activityLevel, svømmediciplin);
            members.add(m); // KUN TIL TEST
            files.saveNewMember(MEMBER_FILE, m);
        } else {
            Member j = new Member(name, age, activityForm, activityLevel);
            members.add(j); // KUN TIL TEST!
            files.saveNewMember(MEMBER_FILE, j);
        }

    }

    public void showTop5Swimmers(ArrayList<Member> membersList) {
        ui.printMessage("Konkurrencesvømmere under 18:");
        listAllSwimmers("Under", membersList);
        ui.printMessage("Konkurrencesvømmere over 18:");
        listAllSwimmers("Above", membersList);

    }

    public void listAllSwimmers(String aboveOrUnder, ArrayList<Member> membersList) {
        for (Member member : membersList) {
            if (Objects.equals(member.getActivityLevel(), "Aktivt")) {
                if (Objects.equals(member.getActivityForm(), "Konkurrence")) {
                    if (Objects.equals(aboveOrUnder, "Under")) {
                        if (Integer.parseInt(member.getAge()) < 18) {
                            getSwimDisciplin(member);
                        }
                    } else if (Objects.equals(aboveOrUnder, "Above")) {
                        if (Integer.parseInt(member.getAge()) >= 18) {
                            getSwimDisciplin(member);
                        }
                    }

                }
            }
        }
    }

    private void getSwimDisciplin(Member member) {
        if (Objects.equals(member.getSvømmediciplin(), "Butterfly")) {
            createTableContents(member);

        } else if (Objects.equals(member.getSvømmediciplin(), "Crawl")) {
            createTableContents(member);

        } else if (Objects.equals(member.getSvømmediciplin(), "Rygcrawl")) {
            createTableContents(member);

        } else if (Objects.equals(member.getSvømmediciplin(), "Brystsvømning")) {
            createTableContents(member);

        }
    }

    private void createTableHeader() {
        System.out.printf("%s%n", "-------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-20s %15s %-15s %20s %-20s %15s %-20s %n", "Navn", "|", "Alder", "|", "Aktivitetsniveau", "|", "Svømmediciplin");
        System.out.printf("%s%n", "-------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    private void createTableContents(Member member) {
        System.out.format("%-20s %15s %-20s %15s %-20s %15s %-20s", member.getName(), "|", member.getAge(), "|", member.getActivityLevel(), "|", member.getSvømmediciplin());
        System.out.println();
    }

    public void addTimeTooMember() {
        Member foundMember = null;
        members.clear();
        ArrayList<Member> members = files.getAllMembers(MEMBER_FILE);
        for (Member member : members) {
            System.out.println(member);
        }
        ui.printMessage("Indtast medlemmets navn som du gerne vil tilføje tid til:");
        String memberName = ui.userInput();
        for (Member member : members) {
            if (memberName.equals(member.getName())) {
                if (member.getActivityForm().equals("Konkurrence")) {
                    foundMember = member;
                } else {
                    ui.printMessage("Det valgte medlem er ikke en konkurrence svømmer");
                }
            }
        }
        ui.printMessage("Indtast medlemmets tid");
        String time = ui.userInput();
        foundMember.setTime(time);
        files.saveNewMember(MEMBER_FILE, members);
    }

    public void calculateExpectedSubFeeTotal() {
        ArrayList<Member> members = files.getAllMembers(MEMBER_FILE);
        double expectedTotal = subFee.getExpectedSubscriptionFeeTotal(members);
        ui.printMessage(Double.toString(expectedTotal));
    }

    private void chargeSubscriptionFee() throws FileNotFoundException {
        subFee.makeSubscriptionCharge();

    }

    private void sowMissingPayments() throws FileNotFoundException {
        ArrayList<String> missingPayments = subFee.memberMissingPayment();
        for (String member : missingPayments) {
            ui.printMessage(member);
        }
    }
private void markAsPaid(){
        ui.printMessage("Skriv navnet på personen der har indbetalt");
        String memberName = ui.userInput();
        subFee.updatePaymentStatus(memberName);
}



    // SKAL SLETTES SENERE! KUN TIL TEST!
    public String showAllMembers() {
        return members.toString();
    }

    public void exit() {
        isRunning = false;
    }
}
