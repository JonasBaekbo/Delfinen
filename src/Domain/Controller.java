//@Jonas Bækbo, @Johanne Riis-Weitling, @Mikkel Sandell

package Domain;

import Files.FileHandler;
import accounting.SubscriptionFee;
import ui.UserInterface;

import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.ArrayList;

public class Controller {
    FileHandler files = new FileHandler();
    private static String MEMBER_FILE = "data/members.txt";
    private static final String COACH_FILE = "data/coach.txt";
    boolean isRunning = true;
    private UserInterface ui = new UserInterface();
    private SubscriptionFee subFee = new SubscriptionFee();

    private SwimTeam st = new SwimTeam();


    public void start() throws FileNotFoundException {
        ui.printMessage("Velkommen til Delfinen");
        ui.printMessage("-----------------------");
        mainMenu();
    }

    private void mainMenu() throws FileNotFoundException {
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

    private void CEOMenu() throws FileNotFoundException {
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

    private void coachMenu() throws FileNotFoundException {
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

    private void treasurerMenu() throws FileNotFoundException {
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
            String swimDisciplineChosen = ui.userInput();
            String SwimDiscipline = chooseSwimDiscipline(swimDisciplineChosen);
            Member member = new Member(name, age, activityForm, activityLevel, SwimDiscipline);
            files.saveNewMember(MEMBER_FILE, member);
        } else {
            Member member = new Member(name, age, activityForm, activityLevel);
            files.saveNewMember(MEMBER_FILE, member);
        }
    }

    private String chooseActivityForm(String activityFormChosen) {
        String activityForm = "";
        if (activityFormChosen.equals("1")) {
            activityForm = "Motionist";
        } else if (activityFormChosen.equals("2")) {
            activityForm = "Konkurrence";
        }
        return activityForm;
    }

    private String chooseActivityLevel(String activityLevelChosen) {
        String activityLevel = "";
        if (activityLevelChosen.equals("1")) {
            activityLevel = "Aktivt";
        } else if (activityLevelChosen.equals("2")) {
            activityLevel = "Passivt";
        }
        return activityLevel;

    }

    private String chooseSwimDiscipline(String swimDisciplineChosen) {
        String svømmediciplin = "";

        if (swimDisciplineChosen.equals("1")) {
            svømmediciplin = "Butterfly";
        } else if (swimDisciplineChosen.equals("2")) {
            svømmediciplin = "Crawl";
        } else if (swimDisciplineChosen.equals("3")) {
            svømmediciplin = "Rygcrawl";
        } else if (swimDisciplineChosen.equals("4")) {
            svømmediciplin = "Brystsvømning";
        } else {
            svømmediciplin = "Ikke gyldigt indput";
        }
        return svømmediciplin;
    }

    public void listAllSwimmersUnder18(ArrayList<Member> membersList) {
        ui.printMessage("Konkurrencesvømmere i brystsvømning:");
        ArrayList<Member> brystUnder18 = st.listSwimmersSplitByAge(membersList, 18, "Brystsvømning", false);
        writeTop5Swimmers(brystUnder18);
        ;
        ui.printMessage("Konkurrencesvømmere i butterfly:");
        ArrayList<Member> butterflyUnder18 = st.listSwimmersSplitByAge(membersList, 18, "Butterfly", false);
        writeTop5Swimmers(butterflyUnder18);
        ui.printMessage("Konkurrencesvømmere i crawl:");
        ArrayList<Member> crawlUnder18 = st.listSwimmersSplitByAge(membersList, 18, "Crawl", false);
        writeTop5Swimmers(crawlUnder18);
        ui.printMessage("Konkurrencesvømmere i rygcrawl:");
        ArrayList<Member> rygUnder18 = st.listSwimmersSplitByAge(membersList, 18, "Rygcrawl", false);
        writeTop5Swimmers(rygUnder18);
    }

    public void listAllSwimmersOver18(ArrayList<Member> membersList) {
        ui.printMessage("Konkurrencesvømmere i brystsvømning:");
        ArrayList<Member> brystOver18 = st.listSwimmersSplitByAge(membersList, 18, "Brystsvømning", true);
        writeTop5Swimmers(brystOver18);
        ui.printMessage("Konkurrencesvømmere i butterfly:");
        ArrayList<Member> butterflyOver18 = st.listSwimmersSplitByAge(membersList, 18, "Butterfly", true);
        writeTop5Swimmers(butterflyOver18);
        ui.printMessage("Konkurrencesvømmere i crawl:");
        ArrayList<Member> crawlOver18 = st.listSwimmersSplitByAge(membersList, 18, "Crawl", true);
        writeTop5Swimmers(crawlOver18);
        ui.printMessage("Konkurrencesvømmere i rygcrawl:");
        ArrayList<Member> rygOver18 = st.listSwimmersSplitByAge(membersList, 18, "Rygcrawl", true);
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

    private void createCoach() {
        ui.printMessage("Indtast træneres navn: ");
        String name = ui.userInput();
        ui.printMessage("Indtast træneres alder: ");
        String age = ui.userInput();
        Coach coach = new Coach(name, age);
        files.saveNewCoach(COACH_FILE, coach);
    }

    private void bestPracticeTime() {
        //TODO:
    }

    public ArrayList<Member> getMembersAsArrayList() {
        ArrayList<Member> members = files.getAllMembers(MEMBER_FILE);
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

    private void tournamentsResults() {
        ArrayList<Member> members = getMembersAsArrayList();
        listCompetitionSwimmers(members);
        ui.printMessage("Indtast medlemmets navn som har deltaget i et stævne:");
        String memberName = ui.userInput();
        Member foundMember = findMemberByName(members, memberName);
        if (foundMember != null) {
            if (foundMember.getTime() != null) {
                ui.printMessage("Indtast navnet på stævnet:");
                String tournamentName = ui.userInput();
                ui.printMessage("Indtast placeringen til stævnet:");
                String place = ui.userInput();
                ui.printMessage("Indtast tiden til stævnet (MM:SS:mm):");
                String time = ui.userInput();
                LocalTime timeToAdd = LocalTime.parse(time);
                Competition c = new Competition(tournamentName, place, timeToAdd);
                foundMember.addCompetition(c);
                files.addCompetitonAndTimeAndDateTooMember(MEMBER_FILE, members);
            } else {
                ui.printMessage("det valgte medlemmet har ikke en tid");
            }
        } else {
            ui.printMessage("Det indtastet navn findes ikke, prøv igen");
        }
    }

    public void addTimeAndDateTooMember() {
        ArrayList<Member> members = getMembersAsArrayList();
        listCompetitionSwimmers(members);
        ui.printMessage("Indtast medlemmets navn som skal have tilføjet en ny tid:");
        String memberName = ui.userInput();
        Member foundMember = findMemberByName(members, memberName);
        if (foundMember != null) {
            ui.printMessage("Indtast medlemmets tid (MM:SS:mm)");
            String time = ui.userInput();
            LocalTime timeToAdd = LocalTime.parse(time);
            foundMember.setTime(timeToAdd);
            ui.printMessage("Indtast datoen for tiden (DD/MM/ÅÅÅÅ)");
            String date = ui.userInput();
            foundMember.setDate(date);
            files.addCompetitonAndTimeAndDateTooMember(MEMBER_FILE, members);
        } else ui.printMessage("Det indtastet navn findes ikke, prøv igen");
    }

    private void listCompetitionSwimmers(ArrayList<Member> members) {
        for (Member member : members) {
            if (member.getActivityForm().equals("Konkurrence"))
                ui.printMessage(member.toString());
        }
    }

    //TODO: find ud af om dette er den rigtige placering til metoden?
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
        ui.createTableHeader();
    }

    //TODO: deles evt. op i 2, en del i UserInterface og en del i Member
    private void createTableContents(Member member) {
        System.out.format("%-20s %15s %-20s %15s %-20s %15s %-20s %15s %-20s", member.getName(), "|", member.getAge(), "|", member.getActivityLevel(), "|", member.getSwimmingDiscipline(), "|", member.getTime());
        System.out.println();

    }

    public void calculateExpectedSubFeeTotal() {
        ArrayList<Member> members = files.getAllMembers(MEMBER_FILE);
        double expectedTotal = subFee.getExpectedSubscriptionFeeTotal(members);
        ui.printMessage(Math.round(expectedTotal) + "kr. Kan forventes i kontingent");

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
            String result = makeSubscriptionChargeForOneMember(memberName);
            ui.printMessage(result);
        } else if (choice.equalsIgnoreCase("2")) {
            String result = makeSubscriptionChargeForAllMembers();
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
        String result = updatePaymentStatus(userInput);
        ui.printMessage(result);
    }

    private String makeSubscriptionChargeForOneMember(String memberName) {
        return subFee.makeSubscriptionChargeForOneMember(memberName);
    }

    private String makeSubscriptionChargeForAllMembers() {
        return subFee.makeSubscriptionChargeForAllMembers();
    }

    private String updatePaymentStatus(String userInput) {
        return subFee.updatePaymentStatus(userInput);
    }

    // SKAL SLETTES SENERE! KUN TIL TEST!
    public String showAllMembers() {
        ArrayList<Member> members = getMembersAsArrayList();
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
