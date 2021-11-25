//@Jonas Bækbo

package Domain;

import Files.FileHandler;
import accounting.SubscriptionFee;
import ui.UserInterface;

import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Objects;

public class Controller {
    FileHandler files = new FileHandler();
    private static String MEMBER_FILE = "data/members.txt";
    private static final String COACH_FILE = "data/coach.txt";
    boolean isRunning = true;
    private UserInterface ui = new UserInterface();
    private SubscriptionFee subFee = new SubscriptionFee();
    private ArrayList<Member> members = new ArrayList<>();


    public void start() throws FileNotFoundException {
        ui.printMessage("Velkommen til Delfinen");
        ui.printMessage("-----------------------");
        mainMenu();
    }

    public void mainMenu() throws FileNotFoundException {
        while (isRunning) {
            ui.MaineMenu();
            switch (ui.userInput()) {
                case "0" -> exit();
                case "1" -> CEOMenu();
                case "2" -> coachMenu();
                case "3" -> treasurerMenu();
                default -> ui.printMessage("Du skal vælge et punkt fra menuen. Prøv venligst igen");


            }
        }
    }

    public void CEOMenu() throws FileNotFoundException {
        while (isRunning) {
            ui.menuCEO();
            switch (ui.userInput()) {
                case "1" -> createNewMember();
                case "2" -> createCoach();
                case "0" -> backTooMainMenu();
                default -> ui.printMessage("Du skal vælge et punkt fra menuen. Prøv venligst igen");

            }
        }
    }

    public void coachMenu() throws FileNotFoundException {
        while (isRunning) {
            ui.menuCoach();
            switch (ui.userInput()) {
                case "1" -> addTimeAndDateTooMember();
                case "2" -> bestPracticeTime();
                case "3" -> tournamentsResults();
                case "4" -> showTop5Swimmers(files.getAllMembers(MEMBER_FILE));
                case "0" -> backTooMainMenu();
                default -> ui.printMessage("Du skal vælge et punkt fra menuen. Prøv venligst igen");
            }
        }
    }

    public void treasurerMenu() throws FileNotFoundException {
        while (isRunning) {
            ui.menuTreasurer();
            switch (ui.userInput()) {
                case "1" -> chargeSubscriptionFee();
                case "2" -> markAsPaid();
                case "3" -> calculateExpectedSubFeeTotal();
                case "4" -> sowMissingPayments();
                case "0" -> backTooMainMenu();
                default -> ui.printMessage("Du skal vælge et punkt fra menuen. Prøv venligst igen");

            }
        }
    }

    private void createCoach() {
        ui.printMessage("Indtast træneres navn: ");
        String name = ui.userInput();
        ui.printMessage("Indtast træneres alder: ");
        String age = ui.userInput();
        Coach m = new Coach(name, age);
        files.saveNewCoach(COACH_FILE, m);
    }

    private void bestPracticeTime() {
        //TODO:
    }

    public void addTimeAndDateTooMember() {
        chooseMember(2);
    }

    private void tournamentsResults() {
        chooseMember(1);
    }

    public void chooseMember(int choose) {
        int counter = 0;
        boolean isChossing = true;
        Member foundMember = null;
        members.clear();
        ArrayList<Member> members = files.getAllMembers(MEMBER_FILE);
        for (Member member : members) {
            System.out.println(member);
        }
        ui.printMessage("Indtast medlemmets navn som har deltaget i et stævne:");
        while (isChossing) {
            String memberName = ui.userInput();
            for (Member member : members) {
                if (memberName.equals(member.getName())) {
                    if (member.getActivityForm().equals("Konkurrence")) {
                        if (choose == 2) {
                            foundMember = member;
                            isChossing = false;
                        }
                        if (choose == 1) {
                            if (member.getTime() != null) {
                                foundMember = member;
                                isChossing = false;

                            } else {
                                ui.printMessage("det valgte medlemmet har ikke en tid");
                            }
                        }
                    } else {
                        ui.printMessage("Det valgte medlem er ikke en konkurrence svømmer");
                    }
                } else {
                    counter++;
                    if (counter == members.size()) {
                        ui.printMessage("Det indtastet navn findes ikke, prøv igen");
                    }
                }
            }
        }
        if (choose == 1) {
            ui.printMessage("Indtast navnet på stævnet:");
            String tournamentName = ui.userInput();
            ui.printMessage("Indtast placeringen til stævnet:");
            String place = ui.userInput();
            ui.printMessage("Indtast tiden til stævnet (MM:SS:mm):");
            String time = ui.userInput();
            LocalTime timeToAdd = LocalTime.parse(time);
            Competitions c = new Competitions(tournamentName, place, timeToAdd);
            foundMember.addCompetition(c);
            files.addCompetitonAndTimeAndDateTooMember(MEMBER_FILE, members);
        }
        if (choose == 2) {
            ui.printMessage("Indtast medlemmets tid (MM:SS:mm)");
            String time = ui.userInput();
            LocalTime timeToAdd = LocalTime.parse(time);
            foundMember.setTime(timeToAdd);
            ui.printMessage("Indtast datoen for tiden (DD/MM/ÅÅÅÅ)");
            String date = ui.userInput();
            foundMember.setDate(date);
            files.addCompetitonAndTimeAndDateTooMember(MEMBER_FILE, members);
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
        String activityForm = chooseActivityForm(activityFormChosen);
        ui.printMessage("""
                Aktivt eller passivt medlem?
                1) Aktivt medlem
                2) Passivt medlem""");
        String activityLevelChosen = ui.userInput();
        String activityLevel = chooseActivityLevel(activityLevelChosen);
        if (!activityForm.equalsIgnoreCase("Motionist")) {
            ui.printMessage("""
                    Indtast medlemmets svømmediciplin
                    1) Butterfly
                    2) Crawl
                    3) Rygcrawl
                    4) Brystsvømning
                    """);
            String svømmediciplinChosen = ui.userInput();
            String svømmediciplin = chooseSwimDesiplin(svømmediciplinChosen);
            Member member = new Member(name, age, activityForm, activityLevel, svømmediciplin);
            files.saveNewMember(MEMBER_FILE,member);
        } else {
            Member member = new Member(name, age, activityForm, activityLevel);
            files.saveNewMember(MEMBER_FILE,member);
        }
    }

    private String chooseActivityForm(String activityFormChosen) {
        return Member.chooseActivityForm(activityFormChosen);
    }

    private String chooseActivityLevel(String activityLevelChosen) {
        return Member.chooseActivityLevel(activityLevelChosen);
    }

    private String chooseSwimDesiplin(String svømmediciplinChosen) {
        return Member.chooseSwimDesiplin(svømmediciplinChosen);
    }

    public void listAllSwimmersUnder18(ArrayList<Member> membersList) {
        ArrayList<Member> butterflyUnder18 = new ArrayList<>();
        ArrayList<Member> crawlUnder18 = new ArrayList<>();
        ArrayList<Member> rygUnder18 = new ArrayList<>();
        ArrayList<Member> brystUnder18 = new ArrayList<>();

        for (Member member : membersList) {
            if (Objects.equals(member.getActivityLevel(), "Aktivt")) {
                if (Objects.equals(member.getActivityForm(), "Konkurrence")) {

                    if (Integer.parseInt(member.getAge()) <= 18) {
                        if (member.getTime() != null) {
                            if (Objects.equals(member.getSvømmediciplin(), "Butterfly")) {
                                butterflyUnder18.add(member);
                            } else if (Objects.equals(member.getSvømmediciplin(), "Crawl")) {
                                crawlUnder18.add(member);
                            } else if (Objects.equals(member.getSvømmediciplin(), "Rygcrawl")) {
                                rygUnder18.add(member);
                            } else if (Objects.equals(member.getSvømmediciplin(), "Brystsvømning")) {
                                brystUnder18.add(member);
                            }
                        }
                    }
                }
            }
        }
        ui.printMessage("Konkurrencesvømmere i brystsvømning:");
        writeTop5Swimmers(brystUnder18);
        ui.printMessage("Konkurrencesvømmere i butterfly:");
        writeTop5Swimmers(butterflyUnder18);
        ui.printMessage("Konkurrencesvømmere i crawl:");
        writeTop5Swimmers(crawlUnder18);
        ui.printMessage("Konkurrencesvømmere i rygcrawl:");
        writeTop5Swimmers(rygUnder18);

    }

    public void listAllSwimmersOver18(ArrayList<Member> membersList) {
        ArrayList<Member> butterflyOver18 = new ArrayList<>();
        ArrayList<Member> crawlOver18 = new ArrayList<>();
        ArrayList<Member> rygOver18 = new ArrayList<>();
        ArrayList<Member> brystOver18 = new ArrayList<>();

        for (Member member : membersList) {
            if (Objects.equals(member.getActivityLevel(), "Aktivt")) {
                if (Objects.equals(member.getActivityForm(), "Konkurrence")) {

                    if (Integer.parseInt(member.getAge()) >= 18) {
                        if (member.getTime() != null) {
                            if (Objects.equals(member.getSvømmediciplin(), "Butterfly")) {
                                butterflyOver18.add(member);
                            } else if (Objects.equals(member.getSvømmediciplin(), "Crawl")) {
                                crawlOver18.add(member);
                            } else if (Objects.equals(member.getSvømmediciplin(), "Rygcrawl")) {
                                rygOver18.add(member);
                            } else if (Objects.equals(member.getSvømmediciplin(), "Brystsvømning")) {
                                brystOver18.add(member);
                            }
                        }
                    }
                }
            }
        }
        ui.printMessage("Konkurrencesvømmere i brystsvømning:");
        writeTop5Swimmers(brystOver18);
        ui.printMessage("Konkurrencesvømmere i butterfly:");
        writeTop5Swimmers(butterflyOver18);
        ui.printMessage("Konkurrencesvømmere i crawl:");
        writeTop5Swimmers(crawlOver18);
        ui.printMessage("Konkurrencesvømmere i rygcrawl:");
        writeTop5Swimmers(rygOver18);

    }

    public void showTop5Swimmers(ArrayList<Member> membersList) throws FileNotFoundException {
        ui.printMessage("Konkurrencesvømmere under 18:");
        createTableHeader();
        listAllSwimmersUnder18(membersList);
        ui.printMessage("Konkurrencesvømmere over 18:");
        createTableHeader();
        listAllSwimmersOver18(membersList);
        coachMenu();
    }

    private void writeTop5Swimmers(ArrayList<Member> membersList) {
        membersList.sort(new Sorting("time"));
        if (membersList.size() < 5) {
            for (Member member : membersList) {
                createTableContents(member);
            }
        } else {
            for (int j = 0; j < 5; j++) {
                createTableContents(membersList.get(j));
            }
        }
    }

    private void createTableHeader() {
        System.out.printf("%s%n", "-------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-20s %15s %-15s %20s %-20s %15s %-20s %15s %-20s %n", "Navn", "|", "Alder", "|", "Aktivitetsniveau", "|", "Svømmediciplin", "|", "Tid");
        System.out.printf("%s%n", "-------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    private void createTableContents(Member member) {
        System.out.format("%-20s %15s %-20s %15s %-20s %15s %-20s %15s %-20s", member.getName(), "|", member.getAge(), "|", member.getActivityLevel(), "|", member.getSvømmediciplin(), "|", member.getTime());
        System.out.println();

    }


    public void calculateExpectedSubFeeTotal() throws FileNotFoundException {
        ArrayList<Member> members = files.getAllMembers(MEMBER_FILE);
        double expectedTotal = subFee.getExpectedSubscriptionFeeTotal(members);
        ui.printMessage(Math.round(expectedTotal) + "kr. Kan forventes i kontingent");
        treasurerMenu();
    }

    private void chargeSubscriptionFee() {
        ui.printMessage("""
                Vil du:
                  1) Opkræve kontingent for en person
                  2) Opkræve kontingent for ALLE medlemmer""");

        String choice = ui.userInput();
        if (choice.equalsIgnoreCase("1")) {
            ui.printMessage("Skriv navnet på medlemmet der skal opkræves");
            String memberName = ui.userInput();
            String result = subFee.makeSubscriptionChargeForOneMember(memberName);
            ui.printMessage(result);
        } else if (choice.equalsIgnoreCase("2")) {
            String result = subFee.makeSubscriptionChargeForAllMembers();
            ui.printMessage(result);
        } else {
            ui.printMessage(choice + " er ikke et gyldigt indput. Vælg 1 eller 2");
        }
    }

    private void sowMissingPayments() {
        ArrayList<String> missingPayments = subFee.memberMissingPayment();
        for (String member : missingPayments) {
            ui.printMessage(member);
        }
    }

    private void markAsPaid() {
        ui.printMessage("Følgende personer har ubetalte regninger:");
        sowMissingPayments();
        ui.printMessage("Skriv fakturanummer eller navnet på personen der har indbetalt");
        String userInput = ui.userInput();
        String result = subFee.updatePaymentStatus(userInput);
        ui.printMessage(result);
    }


    // SKAL SLETTES SENERE! KUN TIL TEST!
    public String showAllMembers() {
        return members.toString();
    }

    public void exit() {
        isRunning = false;
    }

    public void backTooMainMenu() throws FileNotFoundException {
        mainMenu();
    }

    public void updateMemberFile(String FILE_Path) {
        MEMBER_FILE = FILE_Path;
    }
}
