//@ Adam Lasson, Johanne Riis-Weitling, Mikkel Sandell
package Domain;

import Files.FileHandler;

import java.util.ArrayList;

import static java.lang.String.format;

public class Member {
    private String name;
    private String age;
    private boolean isActive; //e.g. Aktivt eller passivt medlem.
    private DisciplineEnum discipline;

    private FileHandler files = new FileHandler();
    private double below18Fee = 1000;
    private double above18Fee = 1600;
    private double passiveFee = 500;
    private double seniorFeeDiscount = 0.75; // der er 25% rabat for medlemmer over 60
    private double subscriptionFee;

    //Der er oprettet en tom constructor for at undgå at have en static klasse, når metoderne skal kaldes fra controllerne
    public Member() {
    }

    public Member(String name, String age, boolean isActive) {
        this.name = name;
        this.age = age;
        this.isActive = isActive;
        this.discipline = null;
    }

    public String getName() {
        return name;
    }

    public DisciplineEnum getDiscipline() {
        return discipline;
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
        int invoiceNumber = getNextInvoiceNumber() + 1;
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

    public String informationToTable() {
        return format("%-20s %15s %-20s %15s %-20s %15s %-20s %15s", getName(), "|", getAge(), "|", getActive(), "|", getDiscipline(), "|");
    }

}