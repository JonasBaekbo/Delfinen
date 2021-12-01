//@Jonas Bækbo, @Johanne Riis-Weitling, @Mikkel Sandell

package Domain;

import Files.FileHandler;
import accounting.SubscriptionFee;
import ui.UserInterface;

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


    public void start() {
        ui.printMessage("Velkommen til Delfinen");
        ui.printMessage("-----------------------");
        mainMenu();
    }

    private void mainMenu() {
        while (isRunning) {
            ui.MaineMenu();
            switch (ui.userInput()) {
                case "0" -> exit();
                case "1" -> ceoMenu();
                case "2" -> coachMenu();
                case "3" -> treasurerMenu();
                default -> ui.printMessage("Du skal vælge et punkt fra menuen. Prøv venligst igen");


            }
        }
    }

    private void ceoMenu() {
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

    private void coachMenu() {
        while (isRunning) {
            ui.menuCoach();
            switch (ui.userInput()) {
                case "1" -> addTimeAndDateTooMember();
                case "2" -> bestPracticeTime();
                case "3" -> tournamentsResults();
                case "4" -> showTop5Swimmers((files.getAllMembers(MEMBER_FILE)));
                case "0" -> backTooMainMenu();
                default -> ui.printMessage("Du skal vælge et punkt fra menuen. Prøv venligst igen");
            }
        }
    }

    private void treasurerMenu() {
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
        boolean isActive = chooseActivityLevel(activityLevelChosen);
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
            CompetitionSwimmer competitionSwimmer = new CompetitionSwimmer(name, age, isActive, SwimDiscipline);
            files.saveNewMember(MEMBER_FILE, competitionSwimmer);
        } else {
            Member member = new Member(name, age, isActive);
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

    private boolean chooseActivityLevel(String activityLevelChosen) {
        if (activityLevelChosen.equals("1")) {
            return true;
        } else if (activityLevelChosen.equals("2")) {
            return false;
        } else {
            ui.printMessage("Ikke et gyldigt indput, sætter status til aktiv");
        }
        return true;
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
        ArrayList<CompetitionSwimmer> brystUnder18 = st.listSwimmersSplitByAge(membersList, 18, "Brystsvømning", false);
        writeTop5Swimmers(brystUnder18);
        ui.printMessage("Konkurrencesvømmere i butterfly:");
        ArrayList<CompetitionSwimmer> butterflyUnder18 = st.listSwimmersSplitByAge(membersList, 18, "Butterfly", false);
        writeTop5Swimmers(butterflyUnder18);
        ui.printMessage("Konkurrencesvømmere i crawl:");
        ArrayList<CompetitionSwimmer> crawlUnder18 = st.listSwimmersSplitByAge(membersList, 18, "Crawl", false);
        writeTop5Swimmers(crawlUnder18);
        ui.printMessage("Konkurrencesvømmere i rygcrawl:");
        ArrayList<CompetitionSwimmer> rygUnder18 = st.listSwimmersSplitByAge(membersList, 18, "Rygcrawl", false);
        writeTop5Swimmers(rygUnder18);
    }

    public void listAllSwimmersOver18(ArrayList<Member> membersList) {
        ui.printMessage("Konkurrencesvømmere i brystsvømning:");
        ArrayList<CompetitionSwimmer> brystOver18 = st.listSwimmersSplitByAge(membersList, 18, "Brystsvømning", true);
        writeTop5Swimmers(brystOver18);
        ui.printMessage("Konkurrencesvømmere i butterfly:");
        ArrayList<CompetitionSwimmer> butterflyOver18 = st.listSwimmersSplitByAge(membersList, 18, "Butterfly", true);
        writeTop5Swimmers(butterflyOver18);
        ui.printMessage("Konkurrencesvømmere i crawl:");
        ArrayList<CompetitionSwimmer> crawlOver18 = st.listSwimmersSplitByAge(membersList, 18, "Crawl", true);
        writeTop5Swimmers(crawlOver18);
        ui.printMessage("Konkurrencesvømmere i rygcrawl:");
        ArrayList<CompetitionSwimmer> rygOver18 = st.listSwimmersSplitByAge(membersList, 18, "Rygcrawl", true);
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
        CompetitionSwimmer foundMember = (CompetitionSwimmer) findMemberByName(members, memberName);
        if (foundMember != null) {
            if (foundMember.getSwimTime() != null) {
                ui.printMessage("Indtast navnet på stævnet:");
                String tournamentName = ui.userInput();
                ui.printMessage("Indtast placeringen til stævnet:");
                String tournamentPlace = ui.userInput();
                ui.printMessage("Indtast tiden til stævnet (MM:SS:mm):");
                String timeAsString = ui.userInput();
                LocalTime tournamentTime = LocalTime.parse(timeAsString);
                Competition competition = new Competition(tournamentName, tournamentPlace, tournamentTime);
                foundMember.addCompetition(competition);
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
        CompetitionSwimmer foundMember = (CompetitionSwimmer) findMemberByName(members, memberName);
        if (foundMember != null) {
            ui.printMessage("Indtast medlemmets tid (MM:SS:mm)");
            String swimTimeAsString = ui.userInput();
            LocalTime timeToAdd = LocalTime.parse(swimTimeAsString);
            foundMember.setSwimTime(timeToAdd);
            ui.printMessage("Indtast datoen for tiden (DD/MM/ÅÅÅÅ)");
            String swimDate = ui.userInput();
            foundMember.setSwimDate(swimDate);
            files.addCompetitonAndTimeAndDateTooMember(MEMBER_FILE, members);
        } else ui.printMessage("Det indtastet navn findes ikke, prøv igen");
    }

    private void listCompetitionSwimmers(ArrayList<Member> members) {
        for (Member member : members) {
            CompetitionSwimmer competitionSwimmer = (CompetitionSwimmer) member;
            if (competitionSwimmer.getSwimDisciplin() != null)
                ui.printMessage(member.toString());
        }
    }


    private void writeTop5Swimmers(ArrayList<CompetitionSwimmer> membersList) {
        ArrayList<CompetitionSwimmer> list = st.writeTop5Swimmers(membersList);
        for (CompetitionSwimmer competitionSwimmer : list) {
            createTableContents(competitionSwimmer);
        }
    }


    private void createTableHeader() {
        ui.createTableHeader();
    }

    //TODO: skal ud i userinterface,
    //TODO: deles evt. op i 2, en del i UserInterface og en del i Member
    private void createTableContents(CompetitionSwimmer competitionSwimmer) {
        System.out.format("%-20s %15s %-20s %15s %-20s %15s %-20s %15s %-20s", competitionSwimmer.getName(), "|", competitionSwimmer.getAge(), "|", competitionSwimmer.getActive(), "|", competitionSwimmer.getSwimDisciplin(), "|", competitionSwimmer.getSwimTime());
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
            String resultSubsForOne = makeSubscriptionChargeForOneMember(memberName);
            ui.printMessage(resultSubsForOne);
        } else if (choice.equalsIgnoreCase("2")) {
            String resultSubForAll = makeSubscriptionChargeForAllMembers();
            ui.printMessage(resultSubForAll);
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
        String resultUpdatePayment = updatePaymentStatus(userInput);
        ui.printMessage(resultUpdatePayment);
    }

    //TODO: skal delvist over på member?
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

    public void backTooMainMenu() {
        mainMenu();
    }

    public void updateMemberFile(String FILE_Path) {
        MEMBER_FILE = FILE_Path;
    }


}
