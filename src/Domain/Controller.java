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
    private static final String MEMBER_FILE = "data/members.txt";
    private static final String COACH_FILE = "data/coach.txt";
    boolean isRunning = true;
    private UserInterface ui = new UserInterface();
    private SubscriptionFee subFee = new SubscriptionFee();
    private ArrayList<Member> members = new ArrayList<>();

    public void start() throws FileNotFoundException {
        ui.printMessage("Velkommen til Delfinen");
        ui.printMessage("-----------------------");
        maineMenu();
    }

    public void maineMenu() throws FileNotFoundException {
        while (isRunning) {
            ui.MaineMenu();
            switch (ui.userInput()) {
                case "0" -> exit();
                case "1" -> CEOManu();
                case "2" -> coachMenu();
                case "3" -> treasurerMenu();

            }
        }
    }

    public void CEOManu() throws FileNotFoundException {
        while (isRunning){
        ui.menuCEO();
        switch (ui.userInput()) {
            case "1" -> createNewMember();
            case "2" -> createCoach();
            case "0" -> backTooMainMenu();
        }
        }
    }

    public void coachMenu() throws FileNotFoundException {
        while (isRunning){
        ui.menuCoach();
        switch (ui.userInput()) {
            case "1" -> addTimeAndDateTooMember();
            case "2" -> bestPracticeTime();
            case "3" -> tournamentsResults();
            case "4" -> showTop5Swimmers(files.getAllMembers(MEMBER_FILE));
            case "0" -> backTooMainMenu();
        }
        }
    }

    public void treasurerMenu() throws FileNotFoundException {
        while (isRunning){
        ui.menuTreasurer();
        switch (ui.userInput()) {
            case "1" -> chargeSubscriptionFee();
            case "2" -> markAsPaid();
            case "3" -> calculateExpectedSubFeeTotal();
            case "4" -> sowMissingPayments();
            case "0" -> backTooMainMenu();
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

    private void tournamentsResults() {
        //TODO:
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

    public void showTop5Swimmers(ArrayList<Member> membersList) throws FileNotFoundException {
        ui.printMessage("Konkurrencesvømmere under 18:");
        createTableHeader();
        listAllSwimmers("Under", membersList);
        ui.printMessage("Konkurrencesvømmere over 18:");
        createTableHeader();
        listAllSwimmers("Above", membersList);
        coachMenu();
    }

    public void listAllSwimmers(String aboveOrUnder, ArrayList<Member> membersList) {
        for (Member member : membersList) {
            if (Objects.equals(member.getActivityLevel(), "Aktivt")) {
                if (Objects.equals(member.getActivityForm(), "Konkurrence")) {
                    if (Objects.equals(aboveOrUnder, "Under")) {
                        if (Integer.parseInt(member.getAge()) < 18) {
                            if (member.getTime() != null){
                            getSwimDisciplin(member);

                            }
                        }
                    } else if (Objects.equals(aboveOrUnder, "Above")) {
                        if (Integer.parseInt(member.getAge()) >= 18) {
                            if (member.getTime() != null){
                                getSwimDisciplin(member);

                            }
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

    public void addTimeAndDateTooMember() throws FileNotFoundException {
        int counter=0;
        boolean isChossing = true;
        Member foundMember = null;
        members.clear();
        ArrayList<Member> members = files.getAllMembers(MEMBER_FILE);
        for (Member member : members) {
            System.out.println(member);
        }
        ui.printMessage("Indtast medlemmets navn som du gerne vil tilføje tid til:");
        while (isChossing) {
            String memberName = ui.userInput();
            for (Member member : members) {
                if (memberName.equals(member.getName())) {
                    if (member.getActivityForm().equals("Konkurrence")) {
                        foundMember = member;
                        isChossing=false;
                    } else {
                        ui.printMessage("Det valgte medlem er ikke en konkurrence svømmer");
                    }
                }else{
                    counter++;
                    if (counter==members.size()) {
                        ui.printMessage("Det indtastet navn findes ikke, prøv igen");
                    }
                }
            }
        }
        ui.printMessage("Indtast medlemmets tid (MM:SS:mm)");
        String time = ui.userInput();
        LocalTime timeToAdd = LocalTime.parse(time);
        foundMember.setTime(timeToAdd);
        ui.printMessage("Indtast datoen for tiden (DD/MM/ÅÅÅÅ)");
        String date = ui.userInput();
        foundMember.setDate(date);
        files.addTimeAndDateTooMember(MEMBER_FILE, members);
    }

    public void calculateExpectedSubFeeTotal() throws FileNotFoundException {
        ArrayList<Member> members = files.getAllMembers(MEMBER_FILE);
        double expectedTotal = subFee.getExpectedSubscriptionFeeTotal(members);
        ui.printMessage(expectedTotal+ "kr. Kan forventes at indtjenes i kontingent");
        treasurerMenu();
    }

    private void chargeSubscriptionFee() throws FileNotFoundException {
        ui.printMessage("""
                      Vil du:
                        1) Opkræve kontingent for en person
                        2) Opkræve kontingent for ALLE medlemmer""");
        String choice=ui.userInput();
        if(choice.equalsIgnoreCase("1")){
            ui.printMessage("Skriv navnet på medlemmet der skal opkræves");
            String memberName =ui.userInput();
            subFee.makeOneSubscriptionCharge(memberName);
        }
        else if (choice.equalsIgnoreCase("2")){
        subFee.makeSubscriptionChargeForAllMembers();
        ui.printMessage("Oprettet kontingent opkrævninger for alle medlemmer!");}
        else ui.printMessage(choice +" er et gyldigt indput. Vælg 1 eller 2");
    }

    private void sowMissingPayments() throws FileNotFoundException {
        ArrayList<String> missingPayments = subFee.memberMissingPayment();
        for (String member : missingPayments) {
            ui.printMessage(member);
        }
    }
private void markAsPaid() throws FileNotFoundException {
        ui.printMessage("Følgende personer har ubetalte regninger:");
        sowMissingPayments();
        ui.printMessage("Skriv fakturanummer eller navnet på personen der har indbetalt");
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

    public void backTooMainMenu() throws FileNotFoundException {
        maineMenu();
    }
}
