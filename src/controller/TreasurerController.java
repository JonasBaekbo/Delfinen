//@Jonas Bækbo, Mikkel Sandell

package controller;

import Domain.Member;
import Files.FileHandler;
import Domain.Charge;
import ui.UserInterface;

import java.util.ArrayList;

public class TreasurerController {
    private boolean isRunning = true;
    private FileHandler files = new FileHandler();
    private UserInterface ui = new UserInterface();
    private Member member = new Member();

    public void treasurerMenu(Controller controller) {
        while (isRunning) {
            ui.menuTreasurer();
            switch (ui.userInput()) {
                case "1" -> chargeSubscriptionFee();
                case "2" -> markAsPaid();
                case "3" -> calculateTotalExpectedIncome();
                case "4" -> showMissingPayments();
                case "0" -> controller.backToMainMenu();
                default -> ui.printMessage("Du skal vælge et punkt fra menuen. Prøv venligst igen");
            }
        }
    }

    public void stop() {
        isRunning = false;
    }

    private void calculateTotalExpectedIncome() {
        ArrayList<Member> members = files.getAllMembers();
        double expectedTotal = member.getTotalExpectedIncome(members);
        ui.printMessage(Math.round(expectedTotal) + "kr. kan forventes i kontingent");
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

    private String makeSubscriptionChargeForOneMember(String memberName) {
        //TODO: udskriv alle medlemmer?
        return member.makeSubscriptionChargeForOneMember(memberName);
    }

    private String makeSubscriptionChargeForAllMembers() {
        return member.makeSubscriptionChargeForAllMembers();
    }

    private String updatePaymentStatus(String userInput) {
        return files.updatePaymentStatus(userInput);
    }

    private void showMissingPayments() {
        ArrayList<String> missingPayments = getMembersMissingPayment();
        for (String member : missingPayments) {
            ui.printMessage(member);
        }
    }
    public ArrayList<String> getMembersMissingPayment() {
        ArrayList<String> members = new ArrayList<>();
        ArrayList<Charge> charges = files.readSubFile();

        for (Charge charge : charges) {
            if (charge.getIsPaid().equalsIgnoreCase("ikke betalt")) {
                members.add(charge.toString());
            }
        }

        return members;
    }

    private void markAsPaid() {
        ui.printMessage("Følgende personer har ubetalte regninger:");
        showMissingPayments();
        ui.printMessage("Skriv fakturanummer eller navnet på personen der har indbetalt");
        String userInput = ui.userInput();
        String resultUpdatePayment = updatePaymentStatus(userInput);
        ui.printMessage(resultUpdatePayment);
    }



}