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
    //private Member member = new Member();
//TODO: fjern fra diagrammer?


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


    public void calculateTotalExpectedIncome() {
        ArrayList<Member> memberArrayList = files.getAllMembers();
        double totalSubscription = 0;

        for (Member member : memberArrayList) {
            totalSubscription += member.getSubscriptionFee(member);
        }
        ui.printMessage(Math.round(totalSubscription) + "kr. kan forventes i kontingent");
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
            ArrayList<Member> members = files.getAllMembers();
            int invoiceNumber = files.countLinesInSubscriptionFile() + 1;
            int numCharge = 0;
            for (Member member : members) {
                if (member.getName().equalsIgnoreCase(memberName)) {
                    String line = member.generateAndSaveInvoiceLine(member, invoiceNumber);
                    numCharge++;
                    files.saveToSubscriptionFile(line);
                }
            }
            if (numCharge == 0) {
                return "Kunne ikke finde et medlem, der matchede din søgning";
            } else if (numCharge == 1) {
                return "Oprettede faktura nummer " + invoiceNumber + " til " + memberName;
            } else {
                return "Oprettede flere fakturaer";
            }

    }

    private String makeSubscriptionChargeForAllMembers() {
            ArrayList<Member> members = files.getAllMembers();
            int invoiceNumber = files.countLinesInSubscriptionFile();
            int numCharge = 0;
            for (Member member : members) {
                invoiceNumber++;
                String line = member.generateAndSaveInvoiceLine(member, invoiceNumber);
                numCharge++;
                files.saveToSubscriptionFile(line);
            }
            if (numCharge == 0) {
                return "Kunne ikke finde nogle medlemmer!";
            } else
                return "Oprettede " + numCharge + " fakturaer";
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