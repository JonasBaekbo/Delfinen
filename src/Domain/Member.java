//@ Adam Lasson, Johanne Riis-Weitling, Mikkel Sandell
package Domain;

import Files.FileHandler;

import java.util.ArrayList;

public class Member {
    private String name;
    private String age;
    private boolean isActive; //e.g. Aktivt eller passivt medlem.

    private FileHandler files = new FileHandler();
    private double below18Fee = 1000;
    private double above18Fee = 1600;
    private double passiveFee = 500;
    private double seniorFeeDiscount = 0.75; // der er 25% rabat for medlemmer over 60
    private double subscriptionFee;

//TODO: er dette korrekt, eller skal man lave Klassen på en anden måde?
    public Member() {
    }

    public Member(String name, String age, boolean isActive) {
        this.name = name;
        this.age = age;
        this.isActive = isActive;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }
    public int getAgeAsInt(Member member) {
        return Integer.parseInt(member.getAge());
    }

    public boolean getActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getStringForSaving() {
        return getName() + ";" + getAge() + ";" + getActive();
    }

    public double getSubscriptionFee(Member member) {
        if (!member.getActive()) {
            subscriptionFee = passiveFee;
        } else {
            subscriptionFee = calculateSubscriptionFee(member);
        }
        return subscriptionFee;
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

        return Math.round(subscriptionFee);
    }

    public double getTotalExpectedIncome(ArrayList<Member> memberArrayList) {
        double totalSubscription = 0;

        for (Member member : memberArrayList) {
            totalSubscription += getSubscriptionFee(member);
        }

        return Math.round(totalSubscription);
    }

    public String makeSubscriptionChargeForOneMember(String memberName) {
        ArrayList<Member> members = files.getAllMembers();
        int invoiceNumber = getNextInvoiceNumber()+1;
        int numCharge = 0;
        for (Member member : members) {
            if (member.getName().equalsIgnoreCase(memberName)) {
                generateAndSaveInvoiceLine(member, invoiceNumber);
                numCharge++;
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

    public String makeSubscriptionChargeForAllMembers() {
        ArrayList<Member> members = files.getAllMembers();
        int invoiceNumber = getNextInvoiceNumber();
        int numCharge = 0;
        for (Member member : members) {
            invoiceNumber++;
            generateAndSaveInvoiceLine(member, invoiceNumber);
            numCharge++;
        }
        if (numCharge == 0) {
            return "Kunne ikke finde nogle medlemmer!";
        } else
            return "Oprettede " + numCharge + " fakturaer";
    }

    public void generateAndSaveInvoiceLine(Member member, int invoiceNumber) {
        double amount = getSubscriptionFee(member);
        String line = invoiceNumber + ";" + member.getInvoiceLine() + ";" + Math.round(amount) + ";" + "ikke betalt";
        files.saveToSubscriptionFile(line);
    }
    public int getNextInvoiceNumber() {
        return files.countLinesInSubscriptionFile();
    }

    public String getInvoiceLine() {
        if (getActive()) {
            return getName() + "; " + getAge() + "; Aktivt medlemskab";
        } else {
            return getName() + "; " + getAge() + "; Passivt medlemskab";
        }
    }


    public String basisMemberToString() {
        return "Medlemsnavn: " + name + '\n' +
                "Alder: " + age + '\n' +
                "Aktiv: " + isActive + '\n';
    }

    @Override
    public String toString() {
        return basisMemberToString() + "----------------------------------------------\n";
    }


}