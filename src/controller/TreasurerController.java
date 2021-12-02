package controller;

import Domain.Member;
import Files.FileHandler;
import Files.FilePath;
import accounting.SubscriptionFee;
import ui.UserInterface;

import java.util.ArrayList;

public class TreasurerController {
    boolean isRunning = true;
    //private static String MEMBER_FILE = "data/members.txt";
    FileHandler files = new FileHandler();
    private UserInterface ui = new UserInterface();
    private SubscriptionFee subFee = new SubscriptionFee();
    private final FilePath filePath =new FilePath();

    public void treasurerMenu(Controller controller) {
        while (isRunning) {
            ui.menuTreasurer();
            switch (ui.userInput()) {
                case "1" -> chargeSubscriptionFee();
                case "2" -> markAsPaid();
                case "3" -> calculateExpectedSubFeeTotal();
                case "4" -> sowMissingPayments();
                case "0" -> controller.backTooMainMenu();
                default -> ui.printMessage("Du skal vælge et punkt fra menuen. Prøv venligst igen");

            }
        }
    }

    public void calculateExpectedSubFeeTotal() {
        ArrayList<Member> members = files.getAllMembers(filePath.MEMBER_PATH);
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
}

